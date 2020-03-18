package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for {@link Question} remote service request.
 * We prefer to separate model classes for request and response (see {@link QuestionView}) even though they are same in our case.
 * However, this is a design choice to keep them loose so that we can handle different request and response formats easily and in a clean way.
 *
 * @author Yavuz Tas
 */
public class QuestionModel {

    private final Long id;
    private final List<String> tags = new ArrayList<>();

    @JsonProperty("is_answered")
    private final Boolean answered;

    @JsonProperty("view_count")
    private final Integer viewCount;

    @JsonProperty("answer_count")
    private final Integer answerCount;

    // Should be (datetime in ISO8601 format as String)
    @JsonProperty("creation_date")
    private final LocalDate creationDate;

    @JsonProperty("user_id")
    private final Long userId;

    public QuestionModel(Question question) {
        this.id = question.getId();
        this.answered = question.getAnswered();
        this.viewCount = question.getViewCount();
        this.answerCount = question.getAnswerCount();
        this.creationDate = question.getCreationDate();
        this.userId = question.getUserId();
    }

    public Long getId() {
        return id;
    }

    public List<String> getTags() {
        return tags;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getUserId() {
        return userId;
    }
}
