package delivery.reservation.user;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.date.Day;
import delivery.reservation.date_time.time.TimeSlot;
import delivery.reservation.schedule.Schedule;
import delivery.reservation.schedule.ScheduleTuple;
import delivery.reservation.system.ReservationSystem;

import java.util.List;

/**
 * Created by sofia on 11/7/15.
 */
public class Supervisor implements SupervisorInterface {

    @Override
    public ScheduleTuple createSchedule(List<Day> days, List<TimeSlot> slots) {
        return createSchedule(days, slots, null, null);
    }

    @Override
    public ScheduleTuple createSchedule(List<Day> days, List<TimeSlot> slots, CalendarDate startDate, CalendarDate endDate) {
        Schedule schedule = new Schedule();
        for (Day day : days) {
            schedule.addDay(day);
        }
        for (TimeSlot slot : slots) {
            schedule.addSlot(slot);
        }
        ScheduleTuple scheduleTuple = new ScheduleTuple(schedule, startDate, endDate);
        return scheduleTuple;
    }

    @Override
    public void removeScheduleForDate(CalendarDate date) {
        date.setSchedule(null);
    }

    @Override
    public void modifyScheduleForDateRange(CalendarDate startDate, CalendarDate endDate, List<Day> days, TimeSlot slot, int newMaxReservations) {
        for (CalendarDate date = startDate; date.compareTo(endDate) <= 0; date = ReservationSystem.getNextDate(date)) {
            Schedule schedule = date.getSchedule();
            if (null == schedule) continue;
            List<Day> daysInSchedule = schedule.getDays();
            if (daysInSchedule.equals(days)) {
                List<TimeSlot> timeSlots = schedule.getSlots();
                for (TimeSlot timeSlot : timeSlots) {
                    if (timeSlot.equals(slot)) {
                        timeSlot.setMaxReservations(newMaxReservations);
                        break;
                    }
                }
            }
        }
    }

}
