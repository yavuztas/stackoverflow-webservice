package dev.yavuztas.stackoverflowwebservice.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.yavuztas.stackoverflowwebservice.domain.Question;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for User web service response.
 *
 * @author Yavuz Tas
 */
public class UserModel {

    @JsonProperty("user_id")
    private Long id;

    // Should be (datetime in ISO8601 format as String)
    @JsonProperty("creation_date")
    private LocalDate creationDate;

    @JsonProperty("display_name")
    private String displayName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
