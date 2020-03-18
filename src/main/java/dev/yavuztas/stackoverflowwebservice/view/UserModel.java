package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.StringJoiner;

/**
 * Model class for user for both consuming and exposing side. Because both formats are identical
 * we don't separate this model unless we need.
 *
 * @author Yavuz Tas
 */
public class UserModel {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("creation_date")
    private Instant creationDate;

    @JsonProperty("display_name")
    private String displayName;

    public UserModel() {
    }

    public UserModel(Long id, Instant creationDate, String displayName) {
        this.id = id;
        this.creationDate = creationDate;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserModel.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("creationDate=" + creationDate)
                .add("displayName='" + displayName + "'")
                .toString();
    }
}
