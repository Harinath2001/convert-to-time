package com.timeinwords.controller;
import com.timeinwords.service.TimeService;
import com.timeinwords.exception.InvalidTimeException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")

public class TimeController {
	private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/current")
    public String getCurrentTimeInWords() {
        return timeService.convertTimeToWords(null);
    }

    @GetMapping("/convert")
    public String convertTime(@RequestParam String time) {
        try {
            return timeService.convertTimeToWords(time);
        } catch (InvalidTimeException e) {
            return e.getMessage();
        }
    }
}
