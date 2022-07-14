package org.grumpyf0x48.readable;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Collections.emptyList;
import static java.util.List.of;

@SpringBootTest
public class UserServiceFullBDDStyleTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void retrieveUsersShouldReturnEmptyListWhenNoUsersArePresent() {
        // Given
        BDDMockito.given(userRepository.retrieveUsers()).willReturn(emptyList());

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        BDDAssertions.then(retrievedUsers)
                .as("Should have returned an empty list")
                .isEmpty();
    }

    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = Mockito.mock(User.class);
        var user2 = Mockito.mock(User.class);
        BDDMockito.given(userRepository.retrieveUsers()).willReturn(of(user1, user2));

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        BDDAssertions.then(retrievedUsers)
                .as("Should have returned the list of users")
                .containsExactlyInAnyOrder(user1, user2);
    }
}
