package dev.yavuztas.stackoverflowwebservice.test.mock.builders;

import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.UserModel;

import java.time.Instant;
import java.util.Set;

public final class QuestionModelBuilder {
    private QuestionModel questionModel;

    private QuestionModelBuilder() {
        questionModel = new QuestionModel();
    }

    public static QuestionModelBuilder aQuestionModel() {
        return new QuestionModelBuilder();
    }

    public QuestionModelBuilder withId(Long id) {
        questionModel.setId(id);
        return this;
    }

    public QuestionModelBuilder withTags(Set<String> tags) {
        questionModel.setTags(tags);
        return this;
    }

    public QuestionModelBuilder withAnswered(Boolean answered) {
        questionModel.setAnswered(answered);
        return this;
    }

    public QuestionModelBuilder withViewCount(Integer viewCount) {
        questionModel.setViewCount(viewCount);
        return this;
    }

    public QuestionModelBuilder withAnswerCount(Integer answerCount) {
        questionModel.setAnswerCount(answerCount);
        return this;
    }

    public QuestionModelBuilder withCreationDate(Instant creationDate) {
        questionModel.setCreationDate(creationDate);
        return this;
    }

    public QuestionModelBuilder withOwner(UserModel owner) {
        questionModel.setOwner(owner);
        return this;
    }

    public QuestionModel build() {
        return questionModel;
    }
}
