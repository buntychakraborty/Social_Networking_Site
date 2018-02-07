
package com.sns.model;

public class PrivateChatBean {
    private String chatId;
    private String userId;
    private String rId;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }
    private String message;
    private String date;
     private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
