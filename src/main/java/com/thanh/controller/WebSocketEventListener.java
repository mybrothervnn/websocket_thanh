package com.thanh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.thanh.model.ChatMessage;
import com.thanh.model.ChatMessage.MessageType;

@Component
public class WebSocketEventListener {
	//Logger lắng nghe các sự kiện xãy trong lớp này;
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	//Connected
	@EventListener
	public void handleWebSocketDisconnectListener(SessionConnectedEvent event) {
		//SessionConnectedEvent: Khi bắt đầu kết nối tới Session thì sẽ kích hoạt event này. 
		LOGGER.info("Tìm thấy 1 kết nối websocket mới");
	}
	
	//Disconnect
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String username = (String) accessor.getSessionAttributes().get("username");
		if (username != null) {
			LOGGER.info("User " +username+ "bị disconnect");
			
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(MessageType.LEAVE);
			
			chatMessage.setSender(username);
			
			// Thong bao ra group thang nay bi out
			messagingTemplate.convertAndSend("/topic/public",chatMessage);
		}
	}
	

}
