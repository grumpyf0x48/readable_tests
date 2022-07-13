package org.grumpyf0x48.readable;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> retrieveUsers() {
        return userRepository.retrieveUsers();
    }
}
