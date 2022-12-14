package com.coderscampus.AssignmentSubmissionApp.service;

import com.coderscampus.AssignmentSubmissionApp.dto.Message;

import java.util.List;

public interface IMessageService {

    String sendMessage(Message message);
    Message getMessageById(Long id);
    List<Message> findMessageByReceiver(String receiver);
}
