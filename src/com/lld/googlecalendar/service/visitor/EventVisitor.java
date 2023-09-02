package com.lld.googlecalendar.service.visitor;

import com.lld.googlecalendar.model.DLList;
import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;

public interface EventVisitor {
    void updateEvent(Member member, Event event);
    void updateEvent(DLList dlList, Event event);
    void deleteEvent(Member member, Event event);
    void deleteEvent(DLList dlList, Event event);
}
