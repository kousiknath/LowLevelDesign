package com.lld.googlecalendar.service.impl;

import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;
import com.lld.googlecalendar.model.Participant;
import com.lld.googlecalendar.repository.EventAcceptanceRepository;
import com.lld.googlecalendar.repository.EventRepository;
import com.lld.googlecalendar.service.EventService;
import com.lld.googlecalendar.service.visitor.EventVisitor;
import com.lld.googlecalendar.service.visitor.impl.EventVisitorImpl;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private EventAcceptanceRepository eventAcceptanceRepository;

    public EventServiceImpl() {
        this.eventRepository = new EventRepository();
        this.eventAcceptanceRepository = new EventAcceptanceRepository();
    }

    @Override
    public List<Event> events(Member member, Long start, Long end) {
        Objects.requireNonNull(member);
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        return this.eventRepository.getEventsInTimeRangeByStartTime(member, start, end);
    }

    @Override
    public Event createEvent(Event event) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(event.getOrganizer());

        this.eventRepository.addEvent(event.getOrganizer(), event);
        return event;
    }

    @Override
    public void updateEvent(Event event) {
        Objects.requireNonNull(event);

        Set<Participant> participants = event.getParticipants();
        EventVisitor eventVisitor = new EventVisitorImpl(this.eventRepository);

        for (Participant participant: participants) {
            participant.updateEvent(event, eventVisitor);
        }
    }

    @Override
    public void deleteEvent(Event event) {
        Objects.requireNonNull(event);

        Set<Participant> participants = event.getParticipants();
        EventVisitor eventVisitor = new EventVisitorImpl(this.eventRepository);

        for (Participant participant: participants) {
            participant.deleteEvent(event, eventVisitor);
        }
    }

    @Override
    public void acceptEvent(Event event, Member member) {
        this.eventAcceptanceRepository.accepted(event, member);
    }

    @Override
    public void rejectEvent(Event event, Member member) {
        this.eventAcceptanceRepository.rejected(event, member);
    }
}
