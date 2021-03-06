package com.stackroute.MentorPlatformService.DAO;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Message")
public class Message {

    @Id
    private String messageId;
    private String timeStamp;
    private String userName;
    private String messageContent;

    public Message() {
    }

    public Message(String messageId, String timeStamp, String userName, String messageContent) {
        this.messageId = messageId;
        this.timeStamp = timeStamp;
        this.userName = userName;
        this.messageContent = messageContent;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", userName='" + userName + '\'' +
                ", messageContent='" + messageContent + '\'' +
                '}';
    }
}
