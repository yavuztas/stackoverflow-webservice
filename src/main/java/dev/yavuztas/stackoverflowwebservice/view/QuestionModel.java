package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Model class for {@link Question} remote service response.
 * We prefer to separate model classes for remote consuming and exposing (see {@link QuestionView}) even though they have same fields in our case.
 * However, this is a design choice to keep them loose so that we can handle different consumer and exposer formats easily and in a clean way.
 *
 * @author Yavuz Tas
 */
public class QuestionModel {

    @JsonProperty("question_id")
    private Long id;

    private Set<String> tags = new LinkedHashSet<>();

    @JsonProperty("is_answered")
    private Boolean answered;

    @JsonProperty("view_count")
    private Integer viewCount;

    @JsonProperty("answer_count")
    private Integer answerCount;

    // Should be (datetime in ISO8601 format as String)
    @JsonProperty("creation_date")
    private Instant creationDate;

    private UserModel owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QuestionModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("tags=" + tags)
                .add("answered=" + answered)
                .add("viewCount=" + viewCount)
                .add("answerCount=" + answerCount)
                .add("creationDate=" + creationDate)
                .add("owner=" + owner)
                .toString();
    }
}
