package edu.services;

import edu.daos.jpa.UserJpaDao;
import edu.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserJpaDao userJpaDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaDao.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username);
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_User"));
        UserPrincipal userPrincipal = new UserPrincipal(user, auths);
        return userPrincipal;
    }
}
