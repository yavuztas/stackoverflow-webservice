package dev.yavuztas.stackoverflowwebservice.test.unit;

import dev.yavuztas.stackoverflowwebservice.service.IQuestionService;
import dev.yavuztas.stackoverflowwebservice.service.QuestionService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class QuestionControllerUnitTestConfig {

    @Bean
    public IQuestionService questionService() {
        //mock bean
        return Mockito.mock(QuestionService.class);
    }

}
