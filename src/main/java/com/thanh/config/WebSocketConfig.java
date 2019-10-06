package com.thanh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{


	@Override
//	đăng ký một websocket endpoint mà các máy khách
//	sẽ sử dụng để kết nối với máy chủ websocket
	public void registerStompEndpoints(StompEndpointRegistry registry) {
//		Register a STOMP over WebSocket endpoint at the given mapping path.
//		Enable SockJS fallback options
		registry.addEndpoint("/ws").withSockJS();
	}

	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// /app dùng để nhắn tin
		registry.setApplicationDestinationPrefixes("/app");
		// /topic dùng để chat group
		registry.enableSimpleBroker("/topic");
	}
	

}
