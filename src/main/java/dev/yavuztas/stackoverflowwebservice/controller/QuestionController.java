package dev.yavuztas.stackoverflowwebservice.controller;

import dev.yavuztas.stackoverflowwebservice.service.IQuestionService;
import dev.yavuztas.stackoverflowwebservice.view.QuestionView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "question", description = "All endpoints about Question")
@RequestMapping("/v/1.0")
@RestController
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @ApiOperation("Get all questions")
    @GetMapping("/question/all")
    public List<QuestionView> userItems(){
        //TODO implement
        return new ArrayList<>();
    }

}
