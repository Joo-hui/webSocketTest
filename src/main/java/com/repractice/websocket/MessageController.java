package com.repractice.websocket;

import com.repractice.websocket.dto.Message;
import com.repractice.websocket.dto.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/message") //메세지 수신용
    @SendTo("/topic/messages") //응답할 곳 지정(엔드포인트)
    public ResponseMessage getMessage(final Message message) throws InterruptedException{

        Thread.sleep(1000); //대기시간 1초

        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));  //메세지에서 수신될 수 있는 모든 특수문자를 이스케이프 처리
    }

////////////////////////////////////////////////////
    @MessageMapping("/private-message") //메세지 수신용
    @SendToUser("/topic/private-messages") //응답할 곳 지정(엔드포인트)
    public ResponseMessage getPrivateMessage(final Message message,
                                             final Principal principal) throws InterruptedException{

        Thread.sleep(1000); //대기시간 1초

        return new ResponseMessage(HtmlUtils.htmlEscape(
                "Sending private message to user " +principal.getName() + ":"
                        + message.getMessageContent())
        );  //메세지에서 수신될 수 있는 모든 특수문자를 이스케이프 처리
    }
}
