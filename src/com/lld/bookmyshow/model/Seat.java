package com.lld.bookmyshow.model;

import java.util.Objects;

public class Seat {
    private String row;
    private String seatNo;

    public Seat(String row, String no) {
        this.row = row;
        this.seatNo = no;
    }

    public String getRow() {
        return row;
    }

    public String getSeatNo() {
        return seatNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.seatNo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Seat other = (Seat) o;
        return this.row.equals(other.row) && this.seatNo.equals(other.seatNo);
    }

    @Override
    public String toString() {
        return "Row = " + row + ", Seat No = " + seatNo;
    }
}
