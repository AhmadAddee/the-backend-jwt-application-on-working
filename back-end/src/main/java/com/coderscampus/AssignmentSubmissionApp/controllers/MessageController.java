package com.coderscampus.AssignmentSubmissionApp.controllers;

import com.coderscampus.AssignmentSubmissionApp.dto.Message;
import com.coderscampus.AssignmentSubmissionApp.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {
    @Autowired
    private final IMessageService iMessageService;

    public MessageController(IMessageService postService) {
        this.iMessageService = postService;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendMessage(@RequestBody Message message) {
        return this.iMessageService.sendMessage(message);
    }

    @GetMapping("/get")
    public Message getMessageById(@RequestParam(value = "id", required = false)Long id) {
        return this.iMessageService.getMessageById(id);
    }

    @GetMapping("/inbox")
    public List<Message> getMyInbox(@RequestParam(value = "username", required = false)String username) {
        return this.iMessageService.findMessageByReceiver(username);
    }
}
