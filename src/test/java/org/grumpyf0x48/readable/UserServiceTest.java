package org.grumpyf0x48.readable;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Collections.emptyList;
import static java.util.List.of;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void retrieveUsersShouldReturnEmptyListWhenNoUsersArePresent() {
        // Given
        Mockito.when(userRepository.retrieveUsers()).thenReturn(emptyList());

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        Assertions.assertThat(retrievedUsers).isEmpty();
    }

    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = Mockito.mock(User.class);
        var user2 = Mockito.mock(User.class);
        Mockito.when(userRepository.retrieveUsers()).thenReturn(of(user1, user2));

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        Assertions.assertThat(retrievedUsers).containsExactlyInAnyOrder(user1, user2);
    }
}
