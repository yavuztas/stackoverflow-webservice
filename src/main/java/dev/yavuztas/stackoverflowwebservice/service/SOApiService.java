package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.exception.NoPageSizeException;
import dev.yavuztas.stackoverflowwebservice.exception.UserNotFoundException;
import dev.yavuztas.stackoverflowwebservice.view.QuestionApiResponse;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserApiResponse;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * A client for Stackoverflow API. We prefer constructor injection here. We have two reasons for that.
 * The first one is we can mock the {@link RestTemplate} dependency so that we make our unit tests much more easier on this class.
 * The second reason is it's very unlikly to need another dependency for this class when we consider its responsibility.
 */
@Service
public class SOApiService implements IApiService {

    @Value("${api.remote.so.questions.url}")
    private String questionApi;

    @Value("${api.remote.so.users.url}")
    private String userApi;

    private RestTemplate restTemplate;

    public SOApiService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<QuestionModel> fetchFeaturedQuestions(int pageSize) {

        if (pageSize <= 0) {
            throw new NoPageSizeException();
        }
        // fetching api response with url parameter pageSize populated
        QuestionApiResponse response = restTemplate.getForObject(questionApi, QuestionApiResponse.class, pageSize);
        return response.getItems();
    }

    @Override
    public UserModel fetchUser(Long userId) {

        if (userId == null || userId <= 0) {
            throw new UserNotFoundException(userId);
        }

        UserApiResponse response = restTemplate.getForObject(userApi, UserApiResponse.class, userId);
        if (response.getItems().size() == 0) {
            throw new UserNotFoundException(userId);
        }

        return response.getItems().get(0);
    }

}
