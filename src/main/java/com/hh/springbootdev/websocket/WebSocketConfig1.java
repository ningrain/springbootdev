package com.hh.springbootdev.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/5/8
 * Time: 10:30
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig1 implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/webSocketServer")
                .addInterceptors(myWebSocketInterceptor()).setAllowedOrigins("*");
    }

    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }

    @Bean
    public MyWebSocketInterceptor myWebSocketInterceptor() {
        return new MyWebSocketInterceptor();
    }
}
