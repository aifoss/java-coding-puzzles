package delivery.reservation.schedule;

import delivery.reservation.date_time.date.CalendarDate;

/**
 * Created by sofia on 4/28/17.
 */
public class ScheduleTuple {

    private Schedule schedule;
    private CalendarDate startDate;
    private CalendarDate endDate;

    public ScheduleTuple(Schedule schedule) {
        this(schedule, null, null);
    }

    public ScheduleTuple(Schedule schedule, CalendarDate startDate, CalendarDate endDate) {
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public CalendarDate getStartDate() {
        return startDate;
    }

    public CalendarDate getEndDate() {
        return endDate;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setStartDate(CalendarDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(CalendarDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (null == o) return false;
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        ScheduleTuple other = (ScheduleTuple) o;
        return this.schedule.equals(other.schedule) && this.startDate.equals(other.startDate) && this.endDate.equals(other.endDate);
    }

    @Override
    public int hashCode() {
        return schedule.hashCode() + startDate.hashCode() + endDate.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("SCHEDULE TUPLE [");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Start Date: ");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append(startDate.toShortString());
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("End Date: ");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append(endDate.toShortString());
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Schedule: ");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append(schedule);
        result.append(System.getProperty("line.separator"));
        result.append("]");
        return result.toString();
    }

}
