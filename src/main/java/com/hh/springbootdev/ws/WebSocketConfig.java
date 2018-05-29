package com.hh.springbootdev.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/5/8
 * Time: 13:19
 */
@Configuration
@EnableWebSocketMessageBroker   //开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册STOMP协议的节点，并指定映射的URL, withSockJS指定使用SockJS协议
        stompEndpointRegistry.addEndpoint("/endpointSang").withSockJS();
    }

    //配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }
}
