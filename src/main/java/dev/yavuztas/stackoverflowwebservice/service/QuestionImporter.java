package dev.yavuztas.stackoverflowwebservice.service;

import dev.yavuztas.stackoverflowwebservice.domain.Question;
import dev.yavuztas.stackoverflowwebservice.repository.QuestionRepository;
import dev.yavuztas.stackoverflowwebservice.view.QuestionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class to import questions on application startup. Import size customizable by the property
 * api.remote.so.questions.importsize in application.properties file.
 * However, we should notice that higher import sizes can cause memory issues. If we need to import
 * higher amount records (like > 1000) then we should implement importFeaturedQuestions method
 * to save our entities and flush in a batch of smaller sizes.
 * Also, this class is configured to run only in production profile so that we can disable question importing
 * when our automated tests running.
 */
@Profile("prod")
@Service
public class QuestionImporter implements IQuestionImporter {

    private Logger logger = LoggerFactory.getLogger(QuestionImporter.class);

    @Value("${api.remote.so.questions.importsize}")
    private Integer importSize;

    @Autowired
    private IApiService apiService;

    @Autowired
    private QuestionRepository questionRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Starting question importer for " + importSize + " records...");
        importFeaturedQuestions();
        logger.info(importSize + " records imported successfuly.");
    }

    @Override
    public void importFeaturedQuestions() {
        List<QuestionModel> models = apiService.fetchFeaturedQuestions(importSize);
        List<Question> domains = models.stream().map(QuestionModel::toQuestion).collect(Collectors.toList());
        questionRepository.saveAll(domains);
    }

}


