package delivery.reservation.date_time.date;

import delivery.reservation.reservation.Reservation;
import delivery.reservation.schedule.Schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sofia on 4/28/17.
 */
public class CalendarDate implements Comparable<CalendarDate> {

    private int year;
    private int month;
    private int day;
    private Day dayOfWeek;
    private Schedule schedule;
    private List<Reservation> reservations;

    public CalendarDate(int year, int month, int day, int dayOfWeek) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = Day.fromCode(dayOfWeek);
        this.schedule = null;
        this.reservations = new ArrayList<>();
    }

    @Override
    public int compareTo(CalendarDate other) {
        int cmp = Integer.compare(this.year, other.year);
        if (cmp != 0) return cmp;
        cmp = Integer.compare(this.month, other.month);
        if (cmp != 0) return cmp;
        return Integer.compare(this.day, other.day);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        Collections.sort(reservations);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        CalendarDate other = (CalendarDate) o;
        return this.year == other.year && this.month == other.month && this.day == other.day;
    }

    @Override
    public int hashCode() {
        return ((Integer)year).hashCode() + ((Integer)month).hashCode() + ((Integer)day).hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("DATE [");
        result.append(System.getProperty("line.separator"));
        result.append("\t");
        result.append(year);
        result.append("-");
        if (month < 10) result.append("0");
        result.append(month);
        result.append("-");
        if (day < 10) result.append("0");
        result.append(day);
        result.append(" (");
        result.append(dayOfWeek);
        result.append(") ");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Applicable schedule:");
        result.append(System.getProperty("line.separator"));
        if (null == schedule) {
            result.append("\t").append("none");
        } else {
            result.append("\t").append(schedule);
        }
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Reservations:");
        result.append(System.getProperty("line.separator"));
        if (reservations.isEmpty()) {
            result.append("\t").append("none");
        } else {
            for (Reservation reservation : reservations) {
                result.append("\t").append(reservation);
                result.append(System.getProperty("line.separator"));
            }
        }
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("]");
        result.append(System.getProperty("line.separator"));
        return result.toString();
    }

    public String toShortString() {
        StringBuilder result = new StringBuilder();
        result.append("DATE [");
        result.append(year);
        result.append("-");
        if (month < 10) result.append("0");
        result.append(month);
        result.append("-");
        if (day < 10) result.append("0");
        result.append(day);
        result.append(" (");
        result.append(dayOfWeek);
        result.append(")");
        result.append("]");
        return result.toString();
    }

}
