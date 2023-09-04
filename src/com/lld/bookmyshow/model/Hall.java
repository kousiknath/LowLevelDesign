package com.lld.bookmyshow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hall {
    private String id; // Supposed to be a unique number or name per cinema
    private Theatre theatre;
    private Screen screen;
    private int totalSeats;
    private List<Seat> seats;

    public Hall(String id, Theatre theatre, Screen screen, int totalSeats) {
        this.id = theatre.getName() + " - " + id;
        this.theatre = theatre;
        this.screen = screen;
        this.totalSeats = totalSeats;
        this.seats = new ArrayList<>();
    }

    public boolean addSeat(Seat seat) {
        if (seats.size() < totalSeats) {
            this.seats.add(seat);
            return true;
        }

        return false;
    }

    public String getId() {
        return id;
    }

    public Theatre getCinema() {
        return this.theatre;
    }

    public Screen getScreen() {
        return screen;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, theatre.hashCode());
    }
}
