package delivery.reservation.date_time.time;

/**
 * Created by sofia on 4/28/17.
 */
public class TimeSlot implements Comparable<TimeSlot> {

    private Time start;
    private Time end;
    private int duration;

    private int maxReservations;
    private int numReservations;

    public TimeSlot(Time start, Time end) {
        this(start, end, 0);
    }

    public TimeSlot(Time start, Time end, int maxReservations) {
        if (end.compareTo(start) <= 0) throw new IllegalArgumentException();
        this.start = start;
        this.end = end;
        this.duration = computeDuration();
        this.maxReservations = maxReservations;
        this.numReservations = 0;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public int getDuration() {
        return duration;
    }

    public int getMaxReservations() {
        return maxReservations;
    }

    public int getNumReservations() {
        return numReservations;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setMaxReservations(int maxReservations) {
        this.maxReservations = maxReservations;
    }

    public void setNumReservations(int numReservations) {
        this.numReservations = numReservations;
    }

    public boolean contains(Time time) {
        return start.compareTo(time) <= 0 && end.compareTo(time) > 0;
    }

    public boolean addReservation() {
        if (numReservations == maxReservations) return false;
        numReservations++;
        return true;
    }

    public int getNumReservationsAvailable() {
        return maxReservations - numReservations;
    }

    private int computeDuration() {
        int endHourValue = end.getHourValue();
        int startHourValue = start.getHourValue();
        return endHourValue - startHourValue;
    }

    @Override
    public int compareTo(TimeSlot other) {
        int cmp = this.start.compareTo(other.start);
        if (cmp != 0) return cmp;
        return this.end.compareTo(other.end);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        TimeSlot other = (TimeSlot) o;
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    @Override
    public int hashCode() {
        return start.hashCode() + end.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("TIME SLOT [");
        result.append("Start: ");
        result.append(start);
        result.append("; ");
        result.append("End: ");
        result.append(end);
        result.append("; ");
        result.append("Duration: ");
        result.append(duration);
        result.append(" hours; ");
        result.append("Max Reservations: ");
        result.append(maxReservations);
        result.append("; ");
        result.append("Current Reservations: ");
        result.append(numReservations);
        result.append("]");
        return result.toString();
    }

}
