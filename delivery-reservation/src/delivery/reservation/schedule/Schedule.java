package delivery.reservation.schedule;

import delivery.reservation.date_time.date.Day;
import delivery.reservation.date_time.time.TimeSlot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sofia on 11/7/15.
 */
public class Schedule {

    private List<Day> days;
    private List<TimeSlot> slots;

    public Schedule() {
        days = new ArrayList<>();
        slots = new ArrayList<>();
    }

    public List<Day> getDays() {
        return days;
    }

    public List<TimeSlot> getSlots() {
        return slots;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setSlots(List<TimeSlot> slots) {
        this.slots = slots;
    }

    public void addDay(Day day) {
        if (days.contains(day)) return;
        days.add(day);
        Collections.sort(days);
    }

    public void addSlot(TimeSlot slot) {
        if (slots.contains(slot)) return;
        slots.add(slot);
        Collections.sort(slots);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Schedule other = (Schedule) o;
        return this.days.equals(other.days) && this.slots.equals(other.slots);
    }

    @Override
    public int hashCode() {
        return days.hashCode() + slots.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SCHEDULE [");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Days: ");
        result.append(days.get(0));
        for (int i = 1; i < days.size(); i++) {
            result.append("/").append(days.get(i));
        }
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Slots:");
        result.append(System.getProperty("line.separator"));
        for (TimeSlot slot : slots) {
            result.append("\t").append(slot);
            result.append(System.getProperty("line.separator"));
        }
        result.append("\t").append("]");
        return result.toString();
    }

}
