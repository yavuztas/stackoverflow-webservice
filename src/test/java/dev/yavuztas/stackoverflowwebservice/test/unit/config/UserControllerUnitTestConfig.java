package dev.yavuztas.stackoverflowwebservice.test.unit.config;

import dev.yavuztas.stackoverflowwebservice.repository.QuestionRepository;
import dev.yavuztas.stackoverflowwebservice.service.IApiService;
import dev.yavuztas.stackoverflowwebservice.service.SOApiService;
import dev.yavuztas.stackoverflowwebservice.test.mock.MockUsers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserControllerUnitTestConfig {

    @MockBean
    public QuestionRepository questionRepository;

    @Bean
    public IApiService apiService() {
        IApiService apiService = Mockito.mock(SOApiService.class);
        Mockito.when(apiService.fetchUser(3L)).thenReturn(MockUsers.userModel3);
        return apiService;
    }

}
