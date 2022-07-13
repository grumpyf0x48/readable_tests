# A sample project showing that tests written with BDDMockito are more readable

## Usual tests using `when`

Only `Mockito.when` keyword is used, but it is used in the "GIVEN" section, which is puzzling :-(

```
    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = mock(User.class);
        var user2 = mock(User.class);
        when(userRepository.retrieveUsers()).thenReturn(of(user1, user2));

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        assertThat(retrievedUsers).containsExactlyInAnyOrder(user1, user2);
    }
```

## Readable tests using `given` and `then`

Both `Mockito.given` and `Mockito.then` keywords are used, respectively in the "GIVEN" and "THEN" section, which makes
more sense and is easier to read :-)

```
    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = mock(User.class);
        var user2 = mock(User.class);
        given(userRepository.retrieveUsers()).willReturn(of(user1, user2));

        // When
        var retrievedUsers = userService.retrieveUsers();

        // Then
        then(userRepository).should().retrieveUsers();
        assertThat(retrievedUsers).containsExactlyInAnyOrder(user1, user2);
    }
```
