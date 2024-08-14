package com.lld.meetingroomscheduler.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Duration {
    private LocalDateTime start;
    private LocalDateTime end;

    public Duration(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duration duration)) return false;
        return Objects.equals(getStart(), duration.getStart()) && Objects.equals(getEnd(), duration.getEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd());
    }

    @Override
    public String toString() {
        return "Duration{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
