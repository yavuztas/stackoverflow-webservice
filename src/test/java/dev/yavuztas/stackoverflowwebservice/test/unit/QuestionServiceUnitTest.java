package dev.yavuztas.stackoverflowwebservice.test.unit;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.repository.QuestionRepository;
import dev.yavuztas.stackoverflowwebservice.service.IQuestionService;
import dev.yavuztas.stackoverflowwebservice.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static dev.yavuztas.stackoverflowwebservice.test.mock.MockQuestions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Unit tests for our {@link dev.yavuztas.stackoverflowwebservice.service.QuestionService}
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuestionServiceUnitTest {

    @Mock
    private QuestionRepository repository;

    private IQuestionService questionService;

    @BeforeEach
    void setup() {
        questionService = new QuestionService(repository);

        when(repository.findById(1L)).thenReturn(Optional.of(question1));
        when(repository.findById(99L)).thenReturn(Optional.empty());
        when(repository.findAll(Sort.by(Sort.Direction.DESC, "creationDate")))
                .thenReturn(Arrays.asList(question1, question2));

        doNothing().when(repository).delete(question1);

        when(repository.findDistinctByTagsIn(TAGS1)).thenReturn(Collections.singletonList(question1));

    }

    @Test
    void whenNonExistingIdGiven_thenThrowsException() {
        assertThrows(QuestionNotFoundException.class, () -> questionService.getQuestion(99L));
    }

    @Test
    void whenExistingIdGiven_thenQuestionReturns() {

        Question question = questionService.getQuestion(1L);

        assertNotNull(question);
        assertEquals(question.getId(), question1.getId());
        assertIterableEquals(question.getTags(), question1.getTags());
        assertEquals(question.getAnswered(), question1.getAnswered());
        assertEquals(question.getViewCount(), question1.getViewCount());
        assertEquals(question.getAnswerCount(), question1.getAnswerCount());
        assertEquals(question.getCreationDate(), question1.getCreationDate());
        assertEquals(question.getUserId(), question1.getUserId());
    }

    @Test
    void whenGetAllQuestions_thenQuestionListReturns() {

        List<Question> questions = questionService.getAllQuestions();
        assertEquals(2, questions.size());

        Question q1 = questions.get(0);
        assertEquals(q1.getId(), question1.getId());
        assertIterableEquals(q1.getTags(), question1.getTags());
        assertEquals(q1.getAnswered(), question1.getAnswered());
        assertEquals(q1.getViewCount(), question1.getViewCount());
        assertEquals(q1.getAnswerCount(), question1.getAnswerCount());
        assertEquals(q1.getCreationDate(), question1.getCreationDate());
        assertEquals(q1.getUserId(), question1.getUserId());

        Question q2 = questions.get(1);
        assertEquals(q2.getId(), question2.getId());
        assertIterableEquals(q2.getTags(), question2.getTags());
        assertEquals(q2.getAnswered(), question2.getAnswered());
        assertEquals(q2.getViewCount(), question2.getViewCount());
        assertEquals(q2.getAnswerCount(), question2.getAnswerCount());
        assertEquals(q2.getCreationDate(), question2.getCreationDate());
        assertEquals(q2.getUserId(), question2.getUserId());

    }

    @Test
    void whenDeleteExistingIdGiven_thenWorks() {
        questionService.deleteQuestion(1L);
    }

    @Test
    void whenDeleteNonExistingIdGiven_thenWorks() {
        assertThrows(QuestionNotFoundException.class, () -> questionService.deleteQuestion(99L));
    }

    @Test
    void whenTagsGiven_thenQuestionListReturns() {

        List<Question> questions = questionService.getQuestionsByTags(TAGS1);
        assertEquals(1, questions.size());

        Question q1 = questions.get(0);
        assertEquals(q1.getId(), question1.getId());
        assertIterableEquals(q1.getTags(), question1.getTags());
        assertEquals(q1.getAnswered(), question1.getAnswered());
        assertEquals(q1.getViewCount(), question1.getViewCount());
        assertEquals(q1.getAnswerCount(), question1.getAnswerCount());
        assertEquals(q1.getCreationDate(), question1.getCreationDate());
        assertEquals(q1.getUserId(), question1.getUserId());

    }


}
