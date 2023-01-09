package com.coderscampus.AssignmentSubmissionApp.db.dbo;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class UserDb implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    private String password;

    private String full_name;

    private String image_url;

    private String profile;

    private int age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //To give users appropriate roles
    @Override
    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this == null || getClass() != o.getClass()) return false;
        UserDb userDb = (UserDb) o;
        return username.equals(userDb.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
