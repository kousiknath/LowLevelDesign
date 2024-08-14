package com.lld.meetingroomscheduler.service.impl;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Meeting;
import com.lld.meetingroomscheduler.model.Room;
import com.lld.meetingroomscheduler.model.User;
import com.lld.meetingroomscheduler.repository.MeetingRepository;
import com.lld.meetingroomscheduler.service.MeetingService;
import com.lld.meetingroomscheduler.service.RoomService;
import com.lld.meetingroomscheduler.validator.DurationValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingServiceImpl implements MeetingService {
    private RoomService roomService;
    private MeetingRepository meetingRepository;
    private ReentrantLock lock;

    public MeetingServiceImpl(RoomService roomService) {
        this.roomService = roomService;
        this.meetingRepository = new MeetingRepository();
        this.lock = new ReentrantLock();
    }

    @Override
    public Meeting create(String title, Duration duration, User host, List<User> participants) throws MeetingSchedulingException {
        if (title == null) {
            throw new MeetingSchedulingException("Title can not be empty");
        }

        if (host == null) {
            throw new MeetingSchedulingException("Host can not be null");
        }

        DurationValidator.checkDurationValidity(duration);

        Room room = this.roomService.getAnAvailableRoomBetween(duration);
        if (room == null) {
            throw new RuntimeException("No room could be assigned!");
        }

        Meeting meeting = new Meeting(title, duration, host, participants);
        meeting.assignRoom(room);

        try {
            this.lock.lock();

            this.roomService.addBooking(room, duration);
            this.meetingRepository.save(meeting);
        } finally {
            this.lock.unlock();
        }

        return meeting;
    }

    @Override
    public boolean cancel(Meeting meeting) throws MeetingSchedulingException {
        if (meeting == null) {
            throw new MeetingSchedulingException("Incorrect meeting");
        }

        try {
            this.lock.lock();

            this.meetingRepository.removeMeeting(meeting);
            this.roomService.removeBooking(meeting.getRoom(), meeting.getDuration());
        } finally {
            this.lock.unlock();
        }
        return true;
    }

    @Override
    public List<Meeting> lastNMeetings(User host, int N) throws MeetingSchedulingException {
        if (host == null || N < 1) {
            throw new MeetingSchedulingException("Invalid parameters !");
        }

        List<Meeting> result = new ArrayList<>();
        List<Meeting> meetings = this.meetingRepository.getMeetingsInDescendingOrderOfStartTime();

        for (Meeting meeting: meetings) {
            if (N == 0) {
                break;
            }

            if (meeting.getHost().equals(host)) {
                result.add(meeting);
                N--;
            }
        }
        return result;
    }
}
