package cn.zhmj.weblogger.consumer.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	private static final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);

	// 此处可注入自己写的Service

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		// 客户端与服务器端建立连接的点
		stompEndpointRegistry.addEndpoint("/any-socket").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
		// 配置客户端发送信息的路径的前缀
		messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
		messageBrokerRegistry.enableSimpleBroker("/topic");
	}

	@Override
	public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {
		registration.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			@Override
			public WebSocketHandler decorate(final WebSocketHandler handler) {
				return new WebSocketHandlerDecorator(handler) {
					@Override
					public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
						// 客户端与服务器端建立连接后，此处记录谁上线了
						log.info("online: " + session.getId());
						super.afterConnectionEstablished(session);
					}

					@Override
					public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
							throws Exception {
						// 客户端与服务器端断开连接后，此处记录谁下线了
						log.info("offline: " + session.getId());
						super.afterConnectionClosed(session, closeStatus);
					}
				};
			}
		});
	}
}
