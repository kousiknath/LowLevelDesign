package com.lld.meetingroomscheduler.validator;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;

public class DurationValidator {
    public static void checkDurationValidity(Duration duration) throws MeetingSchedulingException {
        if (duration.getEnd().isBefore(duration.getStart())) {
            throw new MeetingSchedulingException("Invalid duration. " +
                    "Start time must be before end time");
        }
    }
}
