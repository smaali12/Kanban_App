package com.kanban.domain;

public class UserWithPassword {
    private User user;
    private String password;

    public UserWithPassword() {
    }

    public UserWithPassword(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserWithPassword{" +
                "user=" + user +
                ", password='" + password + '\'' +
                '}';
    }
}
