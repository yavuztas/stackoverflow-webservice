package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for {@link Question} web service response.
 * This is a design choice to not expose the domain model outside and also to keep our domain models clean.
 * Certainly, some other solutions might be considered like @{@link com.fasterxml.jackson.annotation.JsonView}
 * However, we prefer not to spread the conversion logic over on domain models.
 *
 * @author Yavuz Tas
 */
public class QuestionView {

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

    public QuestionView(Question question) {
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
