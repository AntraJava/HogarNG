package com.hogarcontrols.hogarcloud.clientservice.websocket.controller;

import com.hogarcontrols.hogarcloud.common.feign.HomeConfigFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class HiController {

    public static final Logger log = LoggerFactory.getLogger(HiController.class);

    @Autowired
    HomeConfigFeignClient homeConfigFeignClient;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/{dest}")
//    @SendTo("/topic/greetings.dawei.234")
    public void greeting(@Payload HelloMessage message, @DestinationVariable String dest) throws Exception {
//        log.info(homeConfigFeignClient.getHomeDummy().toString());
        log.info(dest);
        simpMessagingTemplate.convertAndSend("/topic/" +dest + "1", new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
        // default it will reply to /topic/{dest}
       // return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
