package com.xuegao.xuegaocallbot.domain;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 16:58
 */
public class GroupDTO {

    /**
     * 群号
     */
    private String chatId;
    /**
     * 群名称
     */
    private String chatName;
    /**
     * 群主的id，如果不指定，系统会随机从userlist中选一人作为群主
     */
    private String owner;
    /**
     * 群成员的id
     */
    private String userList;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }
}