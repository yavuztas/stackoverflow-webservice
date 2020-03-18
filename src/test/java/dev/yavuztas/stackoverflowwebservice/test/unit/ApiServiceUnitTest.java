package dev.yavuztas.stackoverflowwebservice.test.unit;

import dev.yavuztas.stackoverflowwebservice.exception.NoPageSizeException;
import dev.yavuztas.stackoverflowwebservice.exception.UserNotFoundException;
import dev.yavuztas.stackoverflowwebservice.service.IApiService;
import dev.yavuztas.stackoverflowwebservice.service.SOApiService;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.QuestionResponseView;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import dev.yavuztas.stackoverflowwebservice.view.UserResponseView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

import static dev.yavuztas.stackoverflowwebservice.test.mock.MockQuestions.*;
import static dev.yavuztas.stackoverflowwebservice.test.mock.MockUsers.userModel1;
import static dev.yavuztas.stackoverflowwebservice.test.mock.MockUsers.userResponse1;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Unit tests for our {@link dev.yavuztas.stackoverflowwebservice.service.SOApiService}
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ApiServiceUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private IApiService apiService;

    @BeforeEach
    void setup() {
        apiService = new SOApiService(restTemplate);

        when(restTemplate.getForObject(null, QuestionResponseView.class, 2))
                .thenReturn(questionResponse);

        when(restTemplate.getForObject(null, UserResponseView.class, 1L))
                .thenReturn(userResponse1);
    }

    @Test
    void whenPageSizeNotGiven_fetchFeaturedQuestionsThrowsException() {
        assertThrows(NoPageSizeException.class, () -> apiService.fetchFeaturedQuestions(0));
        assertThrows(NoPageSizeException.class, () -> apiService.fetchFeaturedQuestions(-1));
    }

    @Test
    void whenPageSizeGiven_fetchFeaturedQuestionsWorks() {

        List<QuestionModel> questions = apiService.fetchFeaturedQuestions(2);

        assertEquals(2, questions.size());

        QuestionModel model1 = questions.get(0);
        assertEquals(questionModel1.getId(), model1.getId());
        assertIterableEquals(TAGS1, model1.getTags());
        assertEquals(questionModel1.getAnswered(), model1.getAnswered());
        assertEquals(questionModel1.getViewCount(), model1.getViewCount());
        assertEquals(questionModel1.getAnswerCount(), model1.getAnswerCount());
        assertEquals(questionModel1.getCreationDate(), model1.getCreationDate());
        assertEquals(questionModel1.getOwner().getId(), model1.getOwner().getId());
        assertEquals(questionModel1.getOwner().getCreationDate(), model1.getOwner().getCreationDate());
        assertEquals(questionModel1.getOwner().getDisplayName(), model1.getOwner().getDisplayName());

        QuestionModel model2 = questions.get(1);
        assertEquals(questionModel2.getId(), model2.getId());
        assertIterableEquals(TAGS2, model2.getTags());
        assertEquals(questionModel2.getAnswered(), model2.getAnswered());
        assertEquals(questionModel2.getViewCount(), model2.getViewCount());
        assertEquals(questionModel2.getAnswerCount(), model2.getAnswerCount());
        assertEquals(questionModel2.getCreationDate(), model2.getCreationDate());
        assertEquals(questionModel2.getOwner().getId(), model2.getOwner().getId());
        assertEquals(questionModel2.getOwner().getCreationDate(), model2.getOwner().getCreationDate());
        assertEquals(questionModel2.getOwner().getDisplayName(), model2.getOwner().getDisplayName());
    }

    @Test
    void whenUserIdNotGiven_fetchUserThrowsException() {
        assertThrows(UserNotFoundException.class, () -> apiService.fetchUser(null));
        assertThrows(UserNotFoundException.class, () -> apiService.fetchUser(0L));
        assertThrows(UserNotFoundException.class, () -> apiService.fetchUser(-1L));
    }

    @Test
    void whenUserIdGiven_fetchUserWorks() {

        UserModel model = apiService.fetchUser(1L);

        assertNotNull(model);
        assertEquals(userModel1.getId(), model.getId());
        assertEquals(userModel1.getCreationDate(), model.getCreationDate());
        assertEquals(userModel1.getDisplayName(), model.getDisplayName());

        Instant instant = Instant.ofEpochSecond(1584353353L);
        System.out.println(instant);

    }

}
