package igot.integration.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import igot.integration.user.UserModel;
import igot.integration.user.UserService;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UUID id = UUID.fromString(username);
            UserModel user = userService.getUserById(id).get();

            if (user != null) {
                return new User(user.getId().toString(), user.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }
}