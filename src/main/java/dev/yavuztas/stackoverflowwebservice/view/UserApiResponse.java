package dev.yavuztas.stackoverflowwebservice.view;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Model class for Stackoverflow API user response
 *
 * @author Yavuz Tas
 */
public class UserApiResponse {

    private List<UserModel> items = new ArrayList<>();

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserApiResponse.class.getSimpleName() + "[", "]")
                .add("items=" + items)
                .toString();
    }
}
