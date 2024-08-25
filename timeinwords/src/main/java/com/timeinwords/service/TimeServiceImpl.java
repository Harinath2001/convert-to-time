package com.timeinwords.service;

import com.timeinwords.exception.InvalidTimeException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service

public class TimeServiceImpl implements TimeService {

    @Override
    public String convertTimeToWords(String time) throws InvalidTimeException {
        LocalTime localTime;

        if (time == null) {
            localTime = LocalTime.now();
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                localTime = LocalTime.parse(time, formatter);
            } catch (Exception e) {
                throw new InvalidTimeException("Invalid time format. Please provide time in 'HH:mm' format.");
            }
        }

        int hour = localTime.getHour();
        int minute = localTime.getMinute();

        if (hour == 0 && minute == 0) {
            return "It's Midnight";
        } else if (hour == 12 && minute == 0) {
            return "It's Midday";
        }

        String hourInWords = convertNumberToWords(hour % 12 == 0 ? 12 : hour % 12);
        String minuteInWords = convertNumberToWords(minute);

        if (minute == 0) {
            return "It's " + hourInWords + " o'clock";
        } else {
            return "It's " + hourInWords + " " + minuteInWords;
        }
    }

    private String convertNumberToWords(int number) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", 
                          "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tensWords = {"", "", "twenty", "thirty", "forty", "fifty"};

        if (number < 20) {
            return words[number];
        } else {
            return tensWords[number / 10] + (number % 10 != 0 ? " " + words[number % 10] : "");
        }
    }

}
