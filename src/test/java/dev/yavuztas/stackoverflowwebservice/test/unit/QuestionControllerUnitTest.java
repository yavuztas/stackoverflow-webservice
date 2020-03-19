package dev.yavuztas.stackoverflowwebservice.test.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yavuztas.stackoverflowwebservice.config.SwaggerConfig;
import dev.yavuztas.stackoverflowwebservice.controller.QuestionController;
import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.test.mock.MockQuestions;
import dev.yavuztas.stackoverflowwebservice.test.unit.config.QuestionControllerUnitTestConfig;
import dev.yavuztas.stackoverflowwebservice.view.ApiErrorView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for our {@link QuestionController}
 *
 * @author Yavuz Tas
 */
@ActiveProfiles("dev")
@Import(QuestionControllerUnitTestConfig.class)
@WebMvcTest(controllers = QuestionController.class, excludeFilters =
@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SwaggerConfig.class)
)
class QuestionControllerUnitTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenAllQuestionsRequested_questionListAsJsonReturns() throws Exception {

        String questionView1 = mapper.writeValueAsString(MockQuestions.questionView1);
        String questionView2 = mapper.writeValueAsString(MockQuestions.questionView2);

        mockMvc.perform(get("/1.0/question/all"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[" + questionView1 + "," + questionView2 + "]")
                );
    }

    @Test
    void whenSingleQuestionRequested_questionAsJsonReturns() throws Exception {

        String questionView1 = mapper.writeValueAsString(MockQuestions.questionView1);

        mockMvc.perform(get("/1.0/question/1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(questionView1)
                );
    }

    @Test
    void whenNonExistingQuestionRequested_errorAsJsonReturns() throws Exception {

        QuestionNotFoundException exception = new QuestionNotFoundException(99L);
        ApiErrorView error = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        String errorJson = mapper.writeValueAsString(error);

        mockMvc.perform(get("/1.0/question/99"))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .json(errorJson)
                );
    }

    @Test
    void whenBadParametersGiven_errorAsJsonReturns() throws Exception {

        String message = "Error in parameters";
        ApiErrorView error = new ApiErrorView(HttpStatus.BAD_REQUEST.getReasonPhrase(), message);
        String errorJson = mapper.writeValueAsString(error);

        mockMvc.perform(get("/1.0/question/bad"))
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .json(errorJson)
                );
    }

    @Test
    void whenDeleteQuestionRequested_HttpStatus200Returns() throws Exception {
        mockMvc.perform(delete("/1.0/question/1"))
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteNonExistingQuestionRequested_HttpStatus404Returns() throws Exception {

        QuestionNotFoundException exception = new QuestionNotFoundException(99L);
        ApiErrorView error = new ApiErrorView(HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage());
        String errorJson = mapper.writeValueAsString(error);

        mockMvc.perform(delete("/1.0/question/99"))
                .andExpect(status().isNotFound())
                .andExpect(content()
                        .json(errorJson)
                );
    }

    @Test
    void whenQuestionsByTagRequested_questionListAsJsonReturns() throws Exception {

        String questionView1 = mapper.writeValueAsString(MockQuestions.questionView1);

        mockMvc.perform(get("/1.0/question/tagged?t=" + String.join(",", MockQuestions.TAGS1)))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[" + questionView1 + "]")
                );
    }

}
