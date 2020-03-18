package dev.yavuztas.stackoverflowwebservice.view;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Model class for Stackoverflow API question response
 *
 * @author Yavuz Tas
 */
public class QuestionResponseView {

    private List<QuestionModel> items = new ArrayList<>();

    public List<QuestionModel> getItems() {
        return items;
    }

    public void setItems(List<QuestionModel> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QuestionResponseView.class.getSimpleName() + "[", "]")
                .add("items=" + items)
                .toString();
    }
}
