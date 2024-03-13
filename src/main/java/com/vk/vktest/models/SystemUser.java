package com.vk.vktest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemUser implements UserDetails, Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Role> roles;
    @Column(unique = true)
    private String login;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public String getUsername() {
        return login;
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

    @Override
    public Serializable getId() {
        return null;
    }
}
