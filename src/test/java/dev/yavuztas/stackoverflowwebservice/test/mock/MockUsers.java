package dev.yavuztas.stackoverflowwebservice.test.mock;

import dev.yavuztas.stackoverflowwebservice.view.UserApiResponse;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;

public class MockUsers {

    public static final UserModel userModel1 = new UserModel(1L, null, "user1");
    public static final UserModel userModel2 = new UserModel(2L, null, "user2");

    public static final UserApiResponse userApiResponse1 = new UserApiResponse();
    public static final UserApiResponse userApiResponse2 = new UserApiResponse();

    static {

        userApiResponse1.getItems().add(userModel1);

        userApiResponse2.getItems().add(userModel2);

    }

}
