package igot.integration.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public  UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}
