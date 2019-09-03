package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {

    private MessageType messageType;
    private int onlineUsers;
    private String username;
    private String content;

    public enum MessageType {
        JOIN,
        CHAT,
        LEAVE
    }

    public Message() { }

    public Message(MessageType messageType, int onlineUsers, String username, String content) {
        this.messageType = messageType;
        this.onlineUsers = onlineUsers;
        this.username = username;
        this.content = content;
    }

     public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public int getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(int onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
