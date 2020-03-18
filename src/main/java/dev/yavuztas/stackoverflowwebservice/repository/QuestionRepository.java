package dev.yavuztas.stackoverflowwebservice.repository;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // we use distinct here in order to eleminate duplicate records when more than one tag matches
    List<Question> findDistinctByTagsIn(Collection<String> tags);

}
