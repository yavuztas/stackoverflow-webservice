package dev.yavuztas.stackoverflowwebservice.exception;

/**
 * Sample api business logic error when we cannot find an user by id
 *
 * @author Yavuz Tas
 */
public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long userId) {
        this(userId, "User not found id: " + userId);
    }

    public UserNotFoundException(Long userId, String message) {
        super(message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
