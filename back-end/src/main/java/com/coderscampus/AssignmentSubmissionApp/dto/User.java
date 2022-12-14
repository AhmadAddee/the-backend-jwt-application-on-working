package com.coderscampus.AssignmentSubmissionApp.dto;

import java.util.List;

public class User {
    private String username;
    private String fullName;
    private int age;
    private String password;
    private List<Post> posts;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return posts;
    }

    public void setPostList(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", fullName='" + fullName + '\'' +
               ", age=" + age +
               ", password='" + password + '\'' +
               ", posts=" + posts +
               '}';
    }
}
