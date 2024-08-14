package com.lld.meetingroomscheduler.validator;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Room;

public class RoomValidator {
    public static void checkRoomValidity(Room room) throws MeetingSchedulingException {
        if (room == null) {
            throw new MeetingSchedulingException("Room can not be null!");
        }
    }
}
