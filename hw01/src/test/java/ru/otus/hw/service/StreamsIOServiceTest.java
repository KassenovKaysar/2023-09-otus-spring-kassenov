package ru.otus.hw.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

@DisplayName("Сервис печати")
public class StreamsIOServiceTest {
    private final static String TEST_MOCK_STRING = "TEST_MOCK_STRING";
    private final static String TEST_MOCK_STRING2 = "TEST_MOCK_STRING with param = %d";
    private final static int KILOBYTE = 1024;
    @DisplayName("должен вызывать printStream.println")
    @Test
    void shouldPrintLine() {
        var printStream = mock(PrintStream.class);
        var  ioService = new StreamsIOService(printStream);
        ioService.printLine(TEST_MOCK_STRING);
        verify(printStream).println(TEST_MOCK_STRING);
    }

    @DisplayName("должен вызывать printStream.printf с правильными параметрами и больше ничего")
    @Test
    void shouldPrintFormattedLine() {
        var printStream = mock(PrintStream.class);
        var  ioService = new StreamsIOService(printStream);
        ioService.printFormattedLine(TEST_MOCK_STRING2, KILOBYTE);

        verify(printStream).printf(TEST_MOCK_STRING2+"%n", KILOBYTE);

        verifyNoMoreInteractions(printStream);
    }

}
