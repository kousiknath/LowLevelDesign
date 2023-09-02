package com.lld.googlecalendar;

import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;
import com.lld.googlecalendar.model.Organization;
import com.lld.googlecalendar.service.EventService;
import com.lld.googlecalendar.service.impl.EventServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainApplication {
    private static void test1() {
        EventService eventService = new EventServiceImpl();
        Event newEvent = buildEvent();
        List<Event> events = eventService.events(newEvent.getOrganizer(), 1693671271L, 1693678472L);

        System.out.println("Event count for the organizer: " + events.size()); // 0
    }

    private static void test2() {
        EventService eventService = new EventServiceImpl();
        Event event = eventService.createEvent(buildEvent());
        List<Event> events = eventService.events(event.getOrganizer(), 1693671271L, 1693678472L);

        System.out.println("Event count for the organizer: " + events.size()); // 1
    }

    private static void test3() {
        EventService eventService = new EventServiceImpl();
        Event event = eventService.createEvent(buildEvent());
        List<Event> events = eventService.events((Member) new ArrayList<>(event.getParticipants()).get(3),
                1693671271L, 1693678472L);

        System.out.println("Event count for the organizer: " + events.size()); // 1
    }

    private static Event buildEvent() {
        Organization organization = new Organization("my org");
        Member m1 = new Member("a", "a@org.com", organization);
        Member m2 = new Member("b", "b@org.com", organization);
        Member m3 = new Member("c", "c@org.com", organization);
        Member m4 = new Member("d", "d@org.com", organization);
        Member m5 = new Member("e", "e@org.com", organization);


        return new Event.EventBuilder()
                .title("my event")
                .description("random event")
                .link("https://random.com")
                .organizer(m1)
                .startTime(1693671272)
                .endTime(1693678472)
                .participants(new HashSet<>(Arrays.asList(m1, m2, m3, m4, m5)))
                .build();
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
