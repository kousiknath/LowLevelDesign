package com.lld.googlecalendar.repository;

import com.lld.googlecalendar.model.DLList;
import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;

import java.util.*;

public class EventRepository {
    private Map<Member, TreeMap<Long, List<Event>>> memberEvents; // conflicts of events allowed

    public EventRepository() {
        this.memberEvents = new HashMap<>();
    }

    public void addEvent(Member member, Event event) {
        // TODO: Duplicate event check
        this.memberEvents.putIfAbsent(member, new TreeMap<>());
        this.memberEvents.get(member).putIfAbsent(event.getStartTime(), new ArrayList<>());
        this.memberEvents.get(member).get(event.getStartTime()).add(event);
    }

    public List<Event> getEventsInTimeRangeByStartTime(Member member, Long start, Long end) {
        Collection<List<Event>> events = (this.memberEvents.getOrDefault(member, new TreeMap<>()).subMap(start, end).values());
        List<Event> result = new ArrayList<>();
        for(List<Event> subEvents: events) {
            result.addAll(subEvents);
        }

        return result;
    }

    public void updateEvent(Member member, Event event) {
        TreeMap<Long, List<Event>> events = memberEvents.getOrDefault(member, new TreeMap<>());
        List<Event> subEvents = events.get(event.getStartTime());

        if (subEvents != null) {
            subEvents.removeIf(e -> e.equals(event));
            subEvents.add(event);
        }
    }

    public void updateEvent(DLList dlList, Event event) {
        for (Member member: dlList.getMembers()) {
            updateEvent(member, event);
        }
    }

    public void deleteEvent(Member member, Event event) {
        TreeMap<Long, List<Event>> events = memberEvents.getOrDefault(member, new TreeMap<>());
        List<Event> subEvents = events.get(event.getStartTime());

        if (subEvents != null) {
            subEvents.removeIf(e -> e.equals(event));
        }
    }

    public void deleteEvent(DLList dlList, Event event) {
        for (Member member: dlList.getMembers()) {
            deleteEvent(member, event);
        }
    }
}
