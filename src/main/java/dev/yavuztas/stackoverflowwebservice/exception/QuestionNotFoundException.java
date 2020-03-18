package dev.yavuztas.stackoverflowwebservice.exception;

/**
 * Sample api business logic error when we cannot find a question by id
 *
 * @author Yavuz Tas
 */
public class QuestionNotFoundException extends RuntimeException {

    private final Long questionId;

    public QuestionNotFoundException(Long questionId) {
        this(questionId, "Question not found id: " + questionId);
    }

    public QuestionNotFoundException(Long questionId, String message) {
        super(message);
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }
}
