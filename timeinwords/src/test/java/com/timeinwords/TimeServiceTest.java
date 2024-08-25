package com.timeinwords;
import com.timeinwords.exception.InvalidTimeException;
import com.timeinwords.service.TimeServiceImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TimeServiceTest {
	private final TimeServiceImpl timeService = new TimeServiceImpl();

    @Test
    public void testConvertTimeToWords_Midnight() {
        assertEquals("It's Midnight", timeService.convertTimeToWords("00:00"));
    }

    @Test
    public void testConvertTimeToWords_Midday() {
        assertEquals("It's Midday", timeService.convertTimeToWords("12:00"));
    }

    @Test
    public void testConvertTimeToWords_FullHour() {
        assertEquals("It's two o'clock", timeService.convertTimeToWords("02:00"));
    }

    @Test
    public void testConvertTimeToWords_WithMinutes() {
        assertEquals("It's eight thirty four", timeService.convertTimeToWords("08:34"));
    }

    @Test
    public void testConvertTimeToWords_InvalidFormat() {
        Exception exception = assertThrows(InvalidTimeException.class, () -> {
            timeService.convertTimeToWords("25:61");
        });

        assertEquals("Invalid time format. Please provide time in 'HH:mm' format.", exception.getMessage());
    }

}
