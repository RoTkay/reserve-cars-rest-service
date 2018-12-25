package edu.services;

import edu.entities.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;


public class UserPrincipal extends org.springframework.security.core.userdetails.User {
    public UserPrincipal(User user, List<SimpleGrantedAuthority> simpleGrantedAuthorityList) {
        super(user.getUsername(), user.getPassword(), simpleGrantedAuthorityList);
    }
}
