package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;

import java.util.List;

public interface IApiService {

    List<QuestionModel> fetchFeaturedQuestions(int pageSize);

    UserModel fetchUser(Long userId);

}
