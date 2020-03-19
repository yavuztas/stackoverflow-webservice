package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.service.IQuestionService;
import dev.yavuztas.stackoverflowwebservice.view.QuestionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "question", description = "All endpoints about Question")
@RequestMapping("/1.0")
@RestController
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("Get all questions")
    @GetMapping("/question/all")
    public List<QuestionView> questions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.stream().map(QuestionView::new).collect(Collectors.toList());
    }

    @ApiOperation("Get a question by id")
    @GetMapping("/question/{questionId}")
    public QuestionView question(@PathVariable Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return new QuestionView(question);
    }

    /**
     * We don't return any response body for delete. If the response status is 200
     * then it means we successfuly removed the record. Otherwise it returns 404.
     */
    @ApiOperation("Remove a question by id")
    @DeleteMapping("/question/{questionId}")
    public void delete(@PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }

    @ApiOperation("Get questions by given tags")
    @GetMapping("/question/tagged")
    public List<QuestionView> questionsByTag(@RequestParam("t") Collection<String> tags) {
        List<Question> questions = questionService.getQuestionsByTags(tags);
        return questions.stream().map(QuestionView::new).collect(Collectors.toList());
    }

}
