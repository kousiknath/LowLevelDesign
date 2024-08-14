package com.lld.meetingroomscheduler.service;

import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Meeting;
import com.lld.meetingroomscheduler.model.User;

import java.util.List;

public interface MeetingService {
    Meeting create(String title, Duration duration, User host, List<User> participants) throws MeetingSchedulingException;
    boolean cancel(Meeting meeting) throws MeetingSchedulingException;
    List<Meeting> lastNMeetings(User host, int N) throws MeetingSchedulingException;
}
