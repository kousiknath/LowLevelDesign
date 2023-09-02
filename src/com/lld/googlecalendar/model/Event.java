package com.lld.googlecalendar.model;

import java.util.Set;
import java.util.UUID;

public class Event {
    private String id;
    private String title;
    private String description;
    private String link;
    private Member organizer;
    private long startTime;
    private long endTime;
    private Set<Participant> participants;

    public static class EventBuilder {
        private String title;
        private String description;
        private String link;
        private Member organizer;
        private long startTime;
        private long endTime;
        private Set<Participant> participants;

        public EventBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EventBuilder description(String desc) {
            this.description = desc;
            return this;
        }

        public EventBuilder link(String link) {
            this.link = link;
            return this;
        }

        public EventBuilder organizer(Member member) {
            this.organizer = member;
            return this;
        }

        public EventBuilder startTime(long startTime) {
            this.startTime = startTime;
            return this;
        }

        public EventBuilder endTime(long endTime) {
            this.endTime = endTime;
            return this;
        }

        public EventBuilder participants(Set<Participant> participants) {
            this.participants = participants;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.id = UUID.randomUUID().toString(); // Note: Ideally, while creating event, this should be set in the repository layer
            event.title = this.title;
            event.description = this.description;
            event.link = this.link;
            event.organizer = this.organizer;
            event.startTime = this.startTime;
            event.endTime = this.endTime;
            event.participants = this.participants;
            return event;
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Member getOrganizer() {
        return organizer;
    }

    public String getLink() {
        return link;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOrganizer(Member organizer) {
        this.organizer = organizer;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Event other = (Event) o;
        return this.id.equals(other.id);
    }
}
