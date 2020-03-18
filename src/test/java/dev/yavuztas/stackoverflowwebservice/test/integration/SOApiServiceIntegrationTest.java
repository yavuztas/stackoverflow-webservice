package dev.yavuztas.stackoverflowwebservice.test.integration;

import dev.yavuztas.stackoverflowwebservice.service.IApiService;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration test for our {@link IApiService}
 */
@ActiveProfiles("dev")
@SpringBootTest
class SOApiServiceIntegrationTest {

    @Autowired
    private IApiService apiService;

    @Test
    void whenPageSizeGiven_fetchFeaturedQuestionsWorks() {

        List<QuestionModel> questions = apiService.fetchFeaturedQuestions(2);

        assertEquals(2, questions.size());
    }

    @Test
    void whenUserIdGiven_fetchUserWorks() {

        UserModel user = apiService.fetchUser(7794994L);

        assertNotNull(user);
        assertEquals("2017-03-31T00:03:03Z", user.getCreationDate().toString());
        assertEquals("Yavuz Tas", user.getDisplayName());
    }

}
