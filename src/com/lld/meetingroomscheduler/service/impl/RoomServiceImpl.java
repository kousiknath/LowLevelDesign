package com.lld.meetingroomscheduler.service.impl;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Room;
import com.lld.meetingroomscheduler.model.RoomBooking;
import com.lld.meetingroomscheduler.repository.RoomRepository;
import com.lld.meetingroomscheduler.service.RoomService;
import com.lld.meetingroomscheduler.util.Util;
import com.lld.meetingroomscheduler.validator.DurationValidator;
import com.lld.meetingroomscheduler.validator.RoomValidator;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    public RoomServiceImpl() {
        this.roomRepository = new RoomRepository();
    }

    @Override
    public Room registerRoom(Room room) throws MeetingSchedulingException {
        RoomValidator.checkRoomValidity(room);
        return this.roomRepository.register(room);
    }

    @Override
    public Room getAnAvailableRoomBetween(Duration duration) throws MeetingSchedulingException {
        DurationValidator.checkDurationValidity(duration);

        List<RoomBooking> bookings = this.roomRepository.getAllRoomBookings();

        for (RoomBooking roomBooking: bookings) {
            if (isEligibleRoom(duration, roomBooking)) {
                return roomBooking.getRoom();
            }
        }

        return null;
    }

    @Override
    public boolean addBooking(Room room, Duration duration) throws MeetingSchedulingException {
        RoomValidator.checkRoomValidity(room);
        DurationValidator.checkDurationValidity(duration);
        return this.roomRepository.addDurationToRoom(room, duration);
    }

    @Override
    public boolean removeBooking(Room room, Duration duration) throws MeetingSchedulingException {
        RoomValidator.checkRoomValidity(room);
        DurationValidator.checkDurationValidity(duration);

        return this.roomRepository.removeDurationFromRoom(room, duration);
    }

    private boolean isEligibleRoom(Duration duration, RoomBooking roomBooking) {
        long startEpoch = Util.toEpoch(duration.getStart());
        long endEpoch = Util.toEpoch(duration.getEnd());

        List<Duration> durations = roomBooking.getBookings();
        if (durations.isEmpty()) {
            return true;
        }

        Duration firstBooking = durations.get(0);
        Duration lastBooking = durations.get(durations.size() - 1);

        // If the given duration lies before the existing bookings
        // or after the existing bookings, return true.
        if (startEpoch < Util.toEpoch(firstBooking.getStart())
                && endEpoch <= Util.toEpoch(firstBooking.getStart())) {
            return true;
        }

        if (startEpoch >= Util.toEpoch(lastBooking.getEnd())
                && endEpoch > Util.toEpoch(lastBooking.getEnd())) {
            return true;
        }

        for (int i = 1; i < durations.size(); i++) {
            long lastDurationEnd = Util.toEpoch(durations.get(i - 1).getEnd());
            long currentDurationStart = Util.toEpoch(durations.get(i).getStart());

            if (startEpoch >= lastDurationEnd && endEpoch <= currentDurationStart) {
                return true;
            }
        }

        return false;
    }
}
