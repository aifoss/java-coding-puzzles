package delivery.reservation.system;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.time.Time;
import delivery.reservation.date_time.time.TimeSlot;
import delivery.reservation.request_response.request.Request;
import delivery.reservation.request_response.request.RequestType;
import delivery.reservation.request_response.response.Response;
import delivery.reservation.request_response.response.ResponseType;
import delivery.reservation.reservation.Reservation;
import delivery.reservation.schedule.Schedule;
import delivery.reservation.schedule.ScheduleTuple;

import java.util.*;

/**
 * Created by sofia on 11/7/15.
 */
public class ReservationSystem {

    private static final int NUM_MONTHS_PER_YEAR = 12;
    private static final int NUM_DAYS_PER_WEEK = 7;
    private static final int[] NUM_DAYS_PER_MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    private static List<CalendarDate> calendar = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();


    static {
        populateCalendar();
    }

    private static void populateCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.add(new CalendarDate(year, month, day, dayOfWeek));

        boolean monthIncremented = false;

        while (month <= NUM_MONTHS_PER_YEAR) {
            int numDays = NUM_DAYS_PER_MONTH[month-1];
            if (monthIncremented) day = 0;
            while (++day <= numDays) {
                dayOfWeek = (dayOfWeek+1) % NUM_DAYS_PER_WEEK;
                calendar.add(new CalendarDate(year, month, day, dayOfWeek));
            }
            month++;
            monthIncremented = true;
        }
    }


    public void applySchedule(ScheduleTuple scheduleTuple) {
        Schedule schedule = scheduleTuple.getSchedule();
        CalendarDate startDate = scheduleTuple.getStartDate();
        CalendarDate endDate = scheduleTuple.getEndDate();

        int startIdx = (null == startDate) ? 0 : calendar.indexOf(startDate);
        int endIdx = (null == endDate) ? calendar.size()-1 : calendar.indexOf(endDate);

        for (int i = startIdx; i <= endIdx; i++) {
            CalendarDate date = calendar.get(i);
            if (schedule.getDays().contains(date.getDayOfWeek())) {
                date.setSchedule(schedule);
            }
        }
    }

    public List<TimeSlot> getAvailableTimeSlotsForDate(CalendarDate date) {
        List<TimeSlot> result = new ArrayList<>();
        date = getDate(date);
        Schedule schedule = date.getSchedule();
        if (null == schedule) return result;
        for (TimeSlot slot : schedule.getSlots()) {
            int numAvailableReservations = slot.getNumReservationsAvailable();
            if (numAvailableReservations > 0) {
                result.add(slot);
            }
        }
        return result;
    }

    public List<List<TimeSlot>> getTimeSlotsWithOneAvailableReservation(CalendarDate startDate, CalendarDate endDate) {
        List<List<TimeSlot>> result = new ArrayList<>();
        int startIdx = calendar.indexOf(startDate);
        int endIdx = calendar.indexOf(endDate);
        for (int i = startIdx; i <= endIdx; i++) {
            List<TimeSlot> slots = getTimeSlotsWithOneAvailableReservationForDate(calendar.get(i));
            result.add(slots);
        }
        return result;
    }

    private List<TimeSlot> getTimeSlotsWithOneAvailableReservationForDate(CalendarDate date) {
        List<TimeSlot> result = new ArrayList<>();
        date = getDate(date);
        Schedule schedule = date.getSchedule();
        if (null == schedule) return result;
        for (TimeSlot slot : schedule.getSlots()) {
            int numAvailableReservations = slot.getNumReservationsAvailable();
            if (numAvailableReservations == 1) {
                result.add(slot);
            }
        }
        return result;
    }

    public Response processCustomerRequest(Request request) {
        RequestType requestType = request.getRequestType();
        long customerId = request.getCustomerId();
        Date requestDate = request.getRequestDate();
        CalendarDate serviceDate = request.getServiceDate();
        Time serviceTime = request.getServiceTime();

        int dateIdx = calendar.indexOf(serviceDate);
        CalendarDate requestedDate = calendar.get(dateIdx);
        Schedule schedule = requestedDate.getSchedule();
        if (null == schedule) {
            return new Response(ResponseType.FAILURE, customerId);
        }

        List<TimeSlot> slots = schedule.getSlots();
        TimeSlot slot = getApplicableTimeSlot(slots, serviceTime);
        if (null == slot) {
            return new Response(ResponseType.FAILURE, customerId);
        }
        if (!slot.addReservation()) {
            return new Response(ResponseType.FAILURE, customerId);
        }

        Reservation reservation = new Reservation(customerId, requestDate, requestedDate, serviceTime, requestType);
        addReservation(reservation, requestedDate);

        return new Response(ResponseType.SUCCESS, customerId);
    }

    private TimeSlot getApplicableTimeSlot(List<TimeSlot> slots, Time time) {
        for (TimeSlot slot : slots) {
            if (slot.contains(time)) {
                return slot;
            }
        }
        return null;
    }

    private void addReservation(Reservation reservation, CalendarDate reservedDate) {
        reservations.add(reservation);
        reservedDate.addReservation(reservation);
        Collections.sort(reservations);
    }

    public static CalendarDate getDate(CalendarDate date) {
        int idx = calendar.indexOf(date);
        return calendar.get(idx);
    }

    public static CalendarDate getNextDate(CalendarDate date) {
        int idx = calendar.indexOf(date);
        return calendar.get(idx+1);
    }

    public static List<CalendarDate> getCalendar() {
        return calendar;
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }

}
