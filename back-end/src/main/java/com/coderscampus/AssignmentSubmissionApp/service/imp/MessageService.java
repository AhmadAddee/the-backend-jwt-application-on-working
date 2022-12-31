package com.coderscampus.AssignmentSubmissionApp.service.imp;

import com.coderscampus.AssignmentSubmissionApp.db.dbo.MessageDb;
import com.coderscampus.AssignmentSubmissionApp.db.dbo.UserDb;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.MessageRepository;
import com.coderscampus.AssignmentSubmissionApp.db.repositories.UserRepository;
import com.coderscampus.AssignmentSubmissionApp.dto.Message;
import com.coderscampus.AssignmentSubmissionApp.service.IMessageService;
import com.coderscampus.AssignmentSubmissionApp.util.DateUtils;
import com.coderscampus.AssignmentSubmissionApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String sendMessage(Message message, String jwt) {
        String senderFromJwt = jwtUtil.getUsernameFromToken(jwt.split(" ")[1].trim().replaceAll("^\"|\"$", ""));
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        Optional<UserDb> sender = userRepository.findByUsername(senderFromJwt);
        Optional<UserDb> receiver = userRepository.findByUsername(message.getReceiver());
        MessageDb messageDb = new MessageDb();
        messageDb.setSentDate(timestamp);
        messageDb.setContent(message.getContent());
        messageDb.setReceiver(receiver.get());
        messageDb.setSender(sender.get());
        messageRepository.save(messageDb);
        return "Successfully added!";
    }

    @Override
    public Message getMessageById(Long id) {
        MessageDb messageDb = messageRepository.findById(id);
        Message message = new Message();
        message.setId(messageDb.getId());
        message.setSentDate(new Date(messageDb.getSentDate().getTime()));
        message.setTimeAgo(DateUtils.calculateTimeAgo(message.getSentDate()));
        message.setContent(messageDb.getContent());
        message.setReceiver(messageDb.getReceiver().getUsername());
        message.setSender(messageDb.getSender().getUsername());
        return message;
    }

    @Override
    public List<Message> findMessageByReceiver(String jwt) {
        String receiver = jwtUtil.getUsernameFromToken(jwt.split(" ")[1].trim().replaceAll("^\"|\"$", ""));
        Optional<UserDb> receiverDb = userRepository.findByUsername(receiver);
        List<Message> messages = new ArrayList<>();
        Iterable<MessageDb> messageDbs = messageRepository.findMessageByReceiver(receiverDb.get());
        messageDbs.forEach(messageDb -> {
            Message message = new Message();
            message.setId(messageDb.getId());
            message.setSentDate(new Date(messageDb.getSentDate().getTime()));
            message.setTimeAgo(DateUtils.calculateTimeAgo(message.getSentDate()));
            message.setContent(messageDb.getContent());
            message.setReceiver(messageDb.getReceiver().getUsername());
            message.setSender(messageDb.getSender().getUsername());
            messages.add(message);
        });
        return messages;
    }
}
