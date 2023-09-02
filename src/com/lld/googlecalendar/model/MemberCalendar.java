package com.lld.googlecalendar.model;

import java.util.List;

public class MemberCalendar {
    private Member member;
    private List<Event> events;

    public MemberCalendar(Member member, List<Event> events) {
        this.member = member;
        this.events = events;
    }
}
