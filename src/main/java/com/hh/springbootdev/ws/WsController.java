package com.hh.springbootdev.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Desc:
 * User: jiangningning
 * Date: 2018/5/8
 * Time: 13:29
 */
@Controller
public class WsController {

    @RequestMapping(value = "/ws", method = RequestMethod.GET)
    public String ws() {
        return "ws";
    }

    @MessageMapping("/welcome")  // 类似于@RequestMapping注解
    @SendTo("/topic/getResponse")  //表示当服务器有消息需要推送的时候，会对订阅了@SendTo中路径的浏览器发送消息
    public ResponseMessage say(RequestMessage requestMessage) {
        System.out.println(requestMessage.getName());
        return new ResponseMessage("welcome," + requestMessage.getName() + "!");
    }

}
