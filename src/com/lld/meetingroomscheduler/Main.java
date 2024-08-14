package com.lld.meetingroomscheduler;

import com.lld.meetingroomscheduler.constant.RoomType;
import com.lld.meetingroomscheduler.exception.MeetingSchedulingException;
import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Meeting;
import com.lld.meetingroomscheduler.model.Room;
import com.lld.meetingroomscheduler.model.User;
import com.lld.meetingroomscheduler.service.MeetingService;
import com.lld.meetingroomscheduler.service.RoomService;
import com.lld.meetingroomscheduler.service.impl.MeetingServiceImpl;
import com.lld.meetingroomscheduler.service.impl.RoomServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static void test1() throws MeetingSchedulingException {
        System.out.println("Test 1 ----------------");

        List<Room> rooms = Arrays.asList(
                new Room("Room - 1", 10, RoomType.NORMAL)
        );

        RoomService roomService = new RoomServiceImpl();
        for (Room room: rooms) {
            roomService.registerRoom(room);
        }

        MeetingService meetingService = new MeetingServiceImpl(roomService);
        User host = new User("Test User - 1", "test1@test.com");
        Meeting meeting1 = meetingService.create("Test Meeting - 1",
                new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                host, Collections.emptyList());

        System.out.println(meeting1);
    }

    private static void test2SameTimeSlotBooking() throws MeetingSchedulingException {
        System.out.println("\nTest 2 ----------------");

        List<Room> rooms = Arrays.asList(
                new Room("Room - 1", 10, RoomType.NORMAL)
        );

        RoomService roomService = new RoomServiceImpl();
        for (Room room: rooms) {
            roomService.registerRoom(room);
        }

        MeetingService meetingService = new MeetingServiceImpl(roomService);
        User host = new User("Test User - 1", "test1@test.com");
        Meeting meeting1 = meetingService.create("Test Meeting - 1",
                new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                host, Collections.emptyList());
        System.out.println(meeting1);

        try {
            Meeting meeting2 = meetingService.create("Test Meeting - 2",
                    new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                    null, Collections.emptyList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void test3BookingTheSameSlotAfterCancellingTheSlot() throws MeetingSchedulingException {
        System.out.println("\nTest 3 ----------------");

        List<Room> rooms = Arrays.asList(
                new Room("Room - 1", 10, RoomType.NORMAL)
        );

        RoomService roomService = new RoomServiceImpl();
        for (Room room: rooms) {
            roomService.registerRoom(room);
        }

        MeetingService meetingService = new MeetingServiceImpl(roomService);
        User host = new User("Test User - 1", "test1@test.com");
        Meeting meeting1 = meetingService.create("Test Meeting - 1",
                new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                host, Collections.emptyList());
        System.out.println(meeting1);

        System.out.println("Cancelled the meeting: " + meetingService.cancel(meeting1));

        Meeting meeting2 = meetingService.create("Test Meeting - 2",
                new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                host, Collections.emptyList());
        System.out.println(meeting2);
    }

    private static void test4MultipleMeetingsByTheSameUser() throws MeetingSchedulingException {
        System.out.println("\nTest 4 ----------------");

        List<Room> rooms = Arrays.asList(
                new Room("Room - 1", 10, RoomType.NORMAL),
                new Room("Room - 2", 10, RoomType.NORMAL)
        );

        RoomService roomService = new RoomServiceImpl();
        for (Room room: rooms) {
            roomService.registerRoom(room);
        }

        MeetingService meetingService = new MeetingServiceImpl(roomService);
        User host1 = new User("Test User - 1", "test1@test.com");
        Meeting meeting1 = meetingService.create("Test Meeting - 1",
                new Duration(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(8)),
                host1, Collections.emptyList());

        User host2 = new User("Test User - 2", "test2@test.com");
        Meeting meeting2 = meetingService.create("Test Meeting - 2",
                new Duration(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(9)),
                host2, Collections.emptyList());

        Meeting meeting3 = meetingService.create("Test Meeting - 3",
                new Duration(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(9)),
                host1, Collections.emptyList());

        List<Meeting> lastNMeetings = meetingService.lastNMeetings(host1, 10); // Only 2 meetings should be fetched
        for (Meeting m: lastNMeetings) {
            System.out.println(m);
        }
    }

    public static void main(String[] args) throws MeetingSchedulingException {
        test1();
        test2SameTimeSlotBooking();
        test3BookingTheSameSlotAfterCancellingTheSlot();
        test4MultipleMeetingsByTheSameUser();
    }
}
