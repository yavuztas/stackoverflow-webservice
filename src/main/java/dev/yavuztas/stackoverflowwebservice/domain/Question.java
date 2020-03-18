package dev.yavuztas.stackoverflowwebservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Question {

    @Id
    private Long id;
    private Set<String> tags = new LinkedHashSet<>();
    private Boolean answered;
    private Integer viewCount;
    private Integer answerCount;
    private Instant creationDate;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
