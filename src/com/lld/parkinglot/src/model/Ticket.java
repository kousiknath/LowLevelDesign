package com.lld.parkinglot.src.model;

import com.lld.parkinglot.src.constant.TicketStatus;
import com.lld.parkinglot.src.model.slot.Slot;
import com.lld.parkinglot.src.model.vehicle.Vehicle;

import java.util.List;
import java.util.UUID;

public class Ticket {
    private String ticketNo;
    private User user;
    private Vehicle vehicle;
    private TicketStatus ticketStatus;
    private Building building;
    private Entry entry;
    private Exit exit;
    private List<Slot> slots;
    private Fee fee;
    private Payment payment;
    private long checkInTimeMillis;
    private long checkOutTimeMillis;

    public Ticket() {
        this.ticketNo = UUID.randomUUID().toString();
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public long getCheckInTimeMillis() {
        return checkInTimeMillis;
    }

    public void setCheckInTimeMillis(long checkInTimeMillis) {
        this.checkInTimeMillis = checkInTimeMillis;
    }

    public long getCheckOutTimeMillis() {
        return checkOutTimeMillis;
    }

    public void setCheckOutTimeMillis(long checkOutTimeMillis) {
        this.checkOutTimeMillis = checkOutTimeMillis;
    }

    @Override
    public String toString() {
        return "[Ticket] = " + " ticket no: " + this.ticketNo + ", user: " + this.user.getName() + ", vehicle: " + this.vehicle.getRegistrationNo()
                + ", ticket status: " + this.ticketStatus + ", building: " + building.getName()
                + ", level: " + this.slots.get(0).getLevel().getFloor()
                + ", starting slot: " + this.slots.get(0).getIndex() +
                ", total slots allocated: " + this.slots.size()
                + ", check-in time: " + this.checkInTimeMillis
                + ", check-out time: " + this.checkOutTimeMillis;

    }
}
