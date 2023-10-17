package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        final String testFileName = fileNameProvider.getTestFileName();
        try (InputStream in = getClass().getResourceAsStream(testFileName)) {
            List<QuestionDto> questions = new CsvToBeanBuilder(new InputStreamReader(in))
                    .withType(QuestionDto.class)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .build()
                    .parse();
            return questions.stream()
                    .map(QuestionDto::toDomainObject)
                    .toList();
        } catch (RuntimeException ex) {
            throw new QuestionReadException("questions couldn't be read, impossible to read from source", ex);
        } catch (IOException e) {
            throw new QuestionReadException("questions reading have been completed with errors", e);
        }
    }
}
