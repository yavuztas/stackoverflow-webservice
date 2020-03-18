package dev.yavuztas.stackoverflowwebservice.test.mock;

import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import dev.yavuztas.stackoverflowwebservice.view.UserResponseView;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class MockUsers {

    public static final Instant WEEK_BEFORE = Instant.now().minus(7, ChronoUnit.DAYS);

    public static final UserModel userModel1 = new UserModel(1L, null, "user1");
    public static final UserModel userModel2 = new UserModel(2L, null, "user2");
    public static final UserModel userModel3 = new UserModel(3L, WEEK_BEFORE, "user3");

    public static final UserResponseView userResponse1 = new UserResponseView();
    public static final UserResponseView userResponse2 = new UserResponseView();

    static {

        userResponse1.getItems().add(userModel1);

        userResponse2.getItems().add(userModel2);

    }

}
