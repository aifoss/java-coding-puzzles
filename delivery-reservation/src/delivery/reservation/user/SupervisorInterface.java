package delivery.reservation.user;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.date.Day;
import delivery.reservation.date_time.time.TimeSlot;
import delivery.reservation.schedule.ScheduleTuple;

import java.util.List;

/**
 * Created by sofia on 4/28/17.
 */
public interface SupervisorInterface {

    ScheduleTuple createSchedule(List<Day> days, List<TimeSlot> slots);

    ScheduleTuple createSchedule(List<Day> days, List<TimeSlot> slots, CalendarDate startDate, CalendarDate endDate);

    void removeScheduleForDate(CalendarDate date);

    void modifyScheduleForDateRange(CalendarDate startDate, CalendarDate endDate, List<Day> days, TimeSlot slot, int newMaxReservations);

}
