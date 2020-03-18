package dev.yavuztas.stackoverflowwebservice.test.mock;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.test.mock.builders.QuestionModelBuilder;
import dev.yavuztas.stackoverflowwebservice.view.QuestionApiResponse;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import dev.yavuztas.stackoverflowwebservice.view.QuestionView;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockQuestions {

    public static final Instant TODAY = Instant.now();
    public static final Instant YESTERDAY = Instant.now().minus(1, ChronoUnit.DAYS);

    public static final Set<String> TAGS1 = Stream.of("tag1", "tag2").collect(Collectors.toSet());
    public static final Set<String> TAGS2 = Stream.of("tag3").collect(Collectors.toSet());

    public static final QuestionView questionView1 = new QuestionView(1L, TAGS1, false, 12, 2, YESTERDAY, 1L);
    public static final QuestionView questionView2 = new QuestionView(2L, TAGS2, true, 15, 1, TODAY, 2L);

    public static final QuestionModel questionModel1 = QuestionModelBuilder.aQuestionModel()
            .withId(1L).withTags(TAGS1).withAnswered(false).withViewCount(12)
            .withAnswerCount(2).withCreationDate(YESTERDAY).withOwner(MockUsers.userModel1).build();

    public static final QuestionModel questionModel2 = QuestionModelBuilder.aQuestionModel()
            .withId(2L).withTags(TAGS2).withAnswered(true).withViewCount(15)
            .withAnswerCount(1).withCreationDate(TODAY).withOwner(MockUsers.userModel2).build();

    public static final QuestionApiResponse questionApiResponse = new QuestionApiResponse();

    public static final Question question1 = questionModel1.toQuestion();
    public static final Question question2 = questionModel2.toQuestion();

    static {

        List<QuestionModel> items = new ArrayList<>();
        items.add(questionModel1);
        items.add(questionModel2);
        questionApiResponse.setItems(items);

    }

}
