package com.lld.meetingroomscheduler.repository;

import com.lld.meetingroomscheduler.model.Meeting;
import com.lld.meetingroomscheduler.util.Util;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingRepository {
     // Start time to Meeting map.
    // We will use the end time attribute from meeting to do internal
    // comparisons
    private TreeMap<Long, TreeSet<Meeting>> map;
    private ReentrantLock lock;

    public MeetingRepository() {
        this.map = new TreeMap<>(); // Sorted in ascending order by start time
        this.lock = new ReentrantLock();
    }

    public void save(Meeting meeting) {
        Long startTime = Util.toEpoch(meeting.getDuration().getStart());

        try {
            this.lock.lock();

            this.map.put(startTime, new TreeSet<>((o1, o2) -> Util.compareDuration(o1.getDuration(), o2.getDuration())));
            this.map.get(startTime).add(meeting);
        } finally {
            this.lock.unlock();
        }
    }

    /*
        Fetch all the meetings whose start time >= start and end time <= end
     */
    public List<Meeting> getMeetingsBetween(LocalDateTime start, LocalDateTime end) {
        List<Meeting> result = new ArrayList<>();

        long startEpoch = Util.toEpoch(start);
        long endEpoch = Util.toEpoch(end);

        SortedMap<Long, TreeSet<Meeting>> entries = this.map.tailMap(startEpoch);

        try {
            this.lock.lock();

            for (Map.Entry<Long, TreeSet<Meeting>> entry : entries.entrySet()) {
                for (Meeting aMeeting : entry.getValue()) {
                    if (entry.getKey() >= startEpoch && Util.toEpoch(aMeeting.getDuration().getEnd()) <= endEpoch) {
                        result.add(aMeeting);
                    }
                }
            }
        } finally {
            this.lock.unlock();
        }

        return result;
    }

    public List<Meeting> getMeetingsInDescendingOrderOfStartTime() {
        List<Meeting> result = new ArrayList<>();
        NavigableMap<Long, TreeSet<Meeting>> descendingMeetings = this.map.descendingMap();

        for (Map.Entry<Long, TreeSet<Meeting>> entry: descendingMeetings.entrySet()) {
            result.addAll(entry.getValue());
        }

        return result;
    }

    public boolean removeMeeting(Meeting meeting) {
        Long startTime = Util.toEpoch(meeting.getDuration().getStart());

        try {
            this.lock.lock();

            this.map.get(startTime).remove(meeting);
        } finally {
            this.lock.unlock();
        }
        return true;
    }
}
