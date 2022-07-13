package org.grumpyf0x48.readable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class UserServiceFullBDDStyleTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void retrieveUsersShouldReturnEmptyListWhenNoUsersArePresent() {
        // Given
        given(userRepository.retrieveUsers()).willReturn(emptyList());

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        then(retrievedUsers)
                .as("Should have returned an empty list")
                .isEmpty();
    }

    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = mock(User.class);
        var user2 = mock(User.class);
        given(userRepository.retrieveUsers()).willReturn(of(user1, user2));

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        then(retrievedUsers)
                .as("Should have returned the list of users")
                .containsExactlyInAnyOrder(user1, user2);
    }
}
