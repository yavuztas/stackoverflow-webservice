package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SOApiService implements IApiService {

    private RestTemplate restTemplate;

    public SOApiService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<QuestionModel> fetchFeaturedQuestions(int pageSize) {
        return new ArrayList<>();
    }

    @Override
    public UserModel fetchUser(Long userId) {
        return null;
    }

}
