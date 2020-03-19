package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.exception.QuestionNotFoundException;
import dev.yavuztas.stackoverflowwebservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService implements IQuestionService {

    private QuestionRepository questionRepository;

    public QuestionService(@Autowired QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestion(Long id) {
        Optional<Question> hasQuestion = questionRepository.findById(id);
        return hasQuestion.orElseThrow(() -> new QuestionNotFoundException(id));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = getQuestion(id);
        questionRepository.delete(question);
    }

    @Override
    public List<Question> getQuestionsByTags(Collection<String> tags) {
        return questionRepository.findDistinctByTagsIn(tags);
    }

}
