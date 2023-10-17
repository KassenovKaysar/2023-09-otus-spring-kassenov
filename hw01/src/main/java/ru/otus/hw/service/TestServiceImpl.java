package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов

        final List<Question> allQuestions = questionDao.findAll();

        allQuestions
                .forEach(question -> {
                    ioService.printFormattedLine("%s", question.text());
                    ioService.printFormattedLine("%s", StringUtils
                            .joinWith(System.lineSeparator(),
                                    question.answers()
                                            .stream()
                                            .map(answer -> "\t" + answer.text()).toArray()));
                });

    }
}
