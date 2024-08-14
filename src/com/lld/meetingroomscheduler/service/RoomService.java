package com.lld.meetingroomscheduler.service;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Room;

public interface RoomService {
    Room registerRoom(Room room) throws MeetingSchedulingException;
    Room getAnAvailableRoomBetween(Duration duration) throws MeetingSchedulingException;
    boolean addBooking(Room room, Duration duration) throws MeetingSchedulingException;
    boolean removeBooking(Room room, Duration duration) throws MeetingSchedulingException;
}
