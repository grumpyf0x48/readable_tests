package org.grumpyf0x48.readable;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository {

    List<User> retrieveUsers();
}
