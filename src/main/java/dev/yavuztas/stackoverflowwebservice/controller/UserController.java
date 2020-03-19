package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.exception.UserNotFoundException;
import dev.yavuztas.stackoverflowwebservice.service.IApiService;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "user", description = "All endpoints about User")
@RequestMapping("/1.0")
@RestController
public class UserController {

    @Autowired
    private IApiService apiService;

    @ApiOperation("Get user info by id")
    @GetMapping("/user/{userId}")
    public UserModel user(@PathVariable Long userId) {
        return apiService.fetchUser(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

}
