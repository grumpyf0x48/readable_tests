# A sample project showing that tests written with BDDMockito and AssertJ are more readable

Most of the time Unit tests are written following the BDD style (Given, When, Then), and use `Mockito` framework and
`AssertJ` assertions, which is shown in section 1.

In section 2, we show how `BDDMockito` can help to write more readable tests replacing `Mockito.when` keyword by
`BDDMockito.given` in the "Given" section.

Section 3 improves the code of section 2 using `BDDAssertions.then` assertions from AssertJ.

## 1. Usual tests using `Mockito.when` and `Assertions.assertThat` from AssertJ

Only `Mockito.when` keyword is used, but it is used in the "Given" section, which is puzzling :-(

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

See [source code](src/test/java/org/grumpyf0x48/readable/UserServiceTest.java)

## 2. Readable tests using `BDDMockito.given` and `Assertions.assertThat` from AssertJ

`BDDMockito.given` keyword is used in the "Given" section, which makes more sense and is easier to read :-)

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
        assertThat(retrievedUsers).containsExactlyInAnyOrder(user1, user2);
    }
```

See [source code](src/test/java/org/grumpyf0x48/readable/UserServiceBDDStyleTest.java)

## 3. Even more readable tests using `BDDMockito.given`, `Assertions.assertThat` and `BDDAssertions.then` from AssertJ

"Then" sections uses `BDDAssertions.then` assertions.

Our test now fully complies with BDD style, except of course the lack of `when` keyword (which is implicit) in the
"When" section.

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
        then(retrievedUsers)
                .as("Should have returned the list of users")
                .containsExactlyInAnyOrder(user1, user2);
    }
```

We could even merge the "When" and "Then" sections like this:

```
    @Test
    public void retrieveUsersShouldReturnExistingUsersWhenUsersArePresent() {
        // Given
        var user1 = mock(User.class);
        var user2 = mock(User.class);
        given(userRepository.retrieveUsers()).willReturn(of(user1, user2));

        // When Then
        then(userService.retrieveUsers())
                .as("Should have returned the list of users")
                .containsExactlyInAnyOrder(user1, user2);
    }
```

See [source code](src/test/java/org/grumpyf0x48/readable/UserServiceFullBDDStyleTest.java)
