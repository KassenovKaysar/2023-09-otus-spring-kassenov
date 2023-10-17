package ru.otus.hw.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Репозиторий вопросов теста")
public class CsvQuestionDaoTest {

    @DisplayName("должен вернуть вопросы-ответы")
    @Test
    void shouldReturnSampleQuestion() {
        var fileNameProvider = mock(TestFileNameProvider.class);
        when(fileNameProvider.getTestFileName()).thenReturn("/questions_test.csv");

        var questionDao = new CsvQuestionDao(fileNameProvider);
        final List<Question> allQuestion = questionDao.findAll();

        Assertions.assertEquals(3, allQuestion.size());
    }

    @DisplayName("должен сгенерировать исключение QuestionReadException")

    @Test
    void shouldThrowException() {
        var fileNameProvider = mock(TestFileNameProvider.class);
        when(fileNameProvider.getTestFileName()).thenReturn("/question_no_exists.csv");

        var questionDao = new CsvQuestionDao(fileNameProvider);

        assertThatCode(() -> questionDao.findAll())
                .isInstanceOf(QuestionReadException.class);
    }


}
