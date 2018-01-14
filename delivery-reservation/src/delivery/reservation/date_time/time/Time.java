package delivery.reservation.date_time.time;

/**
 * Created by sofia on 11/7/15.
 */
public class Time implements Comparable<Time> {

    private static int NUM_HOURS_IN_PERIOD = 12;

    private int hour;
    private TimePeriod period;

    public Time(int hour, TimePeriod period) {
        this.hour = hour;
        this.period = period;
    }

    public int getHour() {
        return hour;
    }

    public TimePeriod getPeriod() {
        return period;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setPeriod(TimePeriod period) {
        this.period = period;
    }

    public int getHourValue() {
        if (TimePeriod.AM == period) {
            return (hour == 12) ? 0 : hour;
        } else {
            return (hour == 12) ? hour : hour + NUM_HOURS_IN_PERIOD;
        }
    }

    @Override
    public int compareTo(Time other) {
        int thisHourValue = getHourValue();
        int otherHourValue = other.getHourValue();
        return Integer.compare(thisHourValue, otherHourValue);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Time other = (Time) o;
        return this.hour == other.hour && this.period == other.period;
    }

    @Override
    public int hashCode() {
        return ((Integer)hour).hashCode() + period.hashCode();
    }

    @Override
    public String toString() {
        return hour + " " + period;
    }

}
