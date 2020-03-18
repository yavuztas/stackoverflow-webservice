package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.time.Instant;
import java.util.Set;

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
    private final Set<String> tags;

    @JsonProperty("is_answered")
    private final Boolean answered;

    @JsonProperty("view_count")
    private final Integer viewCount;

    @JsonProperty("answer_count")
    private final Integer answerCount;

    @JsonProperty("creation_date")
    private final Instant creationDate;

    @JsonProperty("user_id")
    private final Long userId;

    public QuestionView(Question question) {
        this(question.getId(), question.getTags(), question.getAnswered(), question.getViewCount(), question.getAnswerCount(), question.getCreationDate(),
                question.getUserId());
    }

    public QuestionView(Long id, Set<String> tags, Boolean answered, Integer viewCount, Integer answerCount, Instant creationDate, Long userId) {
        this.id = id;
        this.tags = tags;
        this.answered = answered;
        this.viewCount = viewCount;
        this.answerCount = answerCount;
        this.creationDate = creationDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Set<String> getTags() {
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

    public Instant getCreationDate() {
        return creationDate;
    }

    public Long getUserId() {
        return userId;
    }
}
