package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;

import java.util.List;
import java.util.Optional;

public interface IApiService {

    List<QuestionModel> fetchFeaturedQuestions(int pageSize);

    Optional<UserModel> fetchUser(Long userId);

}
