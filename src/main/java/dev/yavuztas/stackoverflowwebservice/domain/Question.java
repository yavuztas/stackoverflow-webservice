package dev.yavuztas.stackoverflowwebservice.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Question {

    @Id
    private Long id;

    // We define index to TAGS column of the collection table
    // in order to optimize our tags IN (...) queries.
    @CollectionTable(indexes = {@Index(columnList = "tags")})
    // Ideally, question/all endpoint should optimized by pagination.
    // So, we prefer @BatchSize instead of jpql join fetch.
    @BatchSize(size = 20)
    @ElementCollection
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
