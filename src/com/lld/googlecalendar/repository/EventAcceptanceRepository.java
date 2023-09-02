package com.lld.googlecalendar.repository;

import com.lld.googlecalendar.constant.AcceptanceStatus;
import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;

import java.util.HashMap;
import java.util.Map;

public class EventAcceptanceRepository {
    Map<Event, Map<Member, AcceptanceStatus>> acceptanceStatus;

    public EventAcceptanceRepository() {
        this.acceptanceStatus = new HashMap<>();
    }

    public void accepted(Event event, Member member) {
        this.acceptanceStatus.putIfAbsent(event, new HashMap<>());
        this.acceptanceStatus.get(event).put(member, AcceptanceStatus.ACCEPTED);
    }

    public void rejected(Event event, Member member) {
        this.acceptanceStatus.putIfAbsent(event, new HashMap<>());
        this.acceptanceStatus.get(event).put(member, AcceptanceStatus.REJECTED);
    }

    public AcceptanceStatus status(Event event, Member member) {
        this.acceptanceStatus.putIfAbsent(event, new HashMap<>());
        return this.acceptanceStatus.get(event).getOrDefault(member, AcceptanceStatus.PENDING);
    }
}
