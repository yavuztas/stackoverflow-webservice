package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.util.Collection;
import java.util.List;

public interface IQuestionService {

    Question getQuestion(Long id);

    List<Question> getAllQuestions();

    void deleteQuestion(Long id);

    List<Question> getQuestionsByTags(Collection<String> tags);

}
