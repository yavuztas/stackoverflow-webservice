package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.exception.NoPageSizeException;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.QuestionResponseView;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import dev.yavuztas.stackoverflowwebservice.view.UserResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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
        QuestionResponseView response = restTemplate.getForObject(questionApi, QuestionResponseView.class, pageSize);
        return response.getItems();
    }

    /**
     * We return {@link Optional} here instead of throwing exception because we want to cache also null users
     *
     * @return
     */
    @Override
    @Cacheable(value = "usercache")
    public Optional<UserModel> fetchUser(Long userId) {

        if (userId == null || userId <= 0) {
            return Optional.empty();
        }

        UserResponseView response = restTemplate.getForObject(userApi, UserResponseView.class, userId);
        if (response.getItems().size() == 0) {
            return Optional.empty();
        }

        return Optional.of(response.getItems().get(0));
    }

}
