package delivery.reservation.application;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.date.Day;
import delivery.reservation.date_time.time.Time;
import delivery.reservation.date_time.time.TimePeriod;
import delivery.reservation.date_time.time.TimeSlot;
import delivery.reservation.request_response.request.Request;
import delivery.reservation.request_response.request.RequestType;
import delivery.reservation.request_response.response.Response;
import delivery.reservation.reservation.Reservation;
import delivery.reservation.schedule.ScheduleTuple;
import delivery.reservation.system.ReservationSystem;
import delivery.reservation.user.Customer;
import delivery.reservation.user.Supervisor;
import delivery.reservation.user.SupervisorInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sofia on 4/28/17.
 */
public class ReservationSystemClient {

    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        /**
         * Test Supervisor createSchedule()
         * Test ReservationSystem applySchedule()
         */
        SupervisorInterface supervisor = new Supervisor();

        List<ScheduleTuple> scheduleTuples = createSchedules(supervisor);
        for (ScheduleTuple scheduleTuple : scheduleTuples) {
            reservationSystem.applySchedule(scheduleTuple);
        }

        /**
         * Test ReservationSystem getAvailableTimeSlotsForDate()
         */
        CalendarDate date = ReservationSystem.getDate(new CalendarDate(2015, 11, 10, Day.TUE.getCode()));
        List<TimeSlot> availableSlotsForDate = reservationSystem.getAvailableTimeSlotsForDate(date);

        System.out.println("===============================================================================");
        System.out.println("Testing getAvailableTimeSlotsForDate() ========================================");
        System.out.println("===============================================================================");
        System.out.println("Date:");
        System.out.println(date);
        System.out.println("Available slots:");

        for (TimeSlot slot : availableSlotsForDate) {
            System.out.println(slot);
        }

        System.out.println("===============================================================================");
        System.out.println();

        /**
         * Test ReservationSystem getTimeSlotsWithOneAvailableReservation()
         */
        CalendarDate startDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 16, Day.MON.getCode()));
        CalendarDate endDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 20, Day.FRI.getCode()));

        List<List<TimeSlot>> slotsWithOneAvailableReservation
                = reservationSystem.getTimeSlotsWithOneAvailableReservation(startDate, endDate);

        System.out.println("===============================================================================");
        System.out.println("Testing getTimeSlotsWithOneAvailableReservation() =============================");
        System.out.println("===============================================================================");

        date = startDate;
        for (List<TimeSlot> slots : slotsWithOneAvailableReservation) {
            System.out.println("Date:");
            System.out.println(date);
            System.out.println("Slots with one available reservation:");
            for (TimeSlot slot : slots) {
                System.out.println(slot);
            }
            date = reservationSystem.getNextDate(date);
            System.out.println();
        }

        System.out.println("===============================================================================");
        System.out.println();

        /**
         * Test Supervisor removeScheduleForDate()
         */
        System.out.println("===============================================================================");
        System.out.println("Testing (Supervisor) removeScheduleForDate() ==================================");
        System.out.println("===============================================================================");

        date = ReservationSystem.getDate(new CalendarDate(2015, 11, 17, Day.TUE.getCode()));

        System.out.println("Before removing schedule:");
        System.out.println(date);
        System.out.println();

        supervisor.removeScheduleForDate(date);

        System.out.println("After removing schedule:");
        System.out.println(date);
        System.out.println();

        System.out.println("===============================================================================");
        System.out.println();

        /**
         * Test Supervisor modifyScheduleForDateRange()
         */
        modifySchedules(supervisor);

        /**
         * Test ReservationSystem processCustomerRequest()
         */
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            customers.add(new Customer(i));
        }

        List<Request> requests = createRequests(customers);

        System.out.println("===============================================================================");
        System.out.println("Testing processCustomerRequest() ==============================================");
        System.out.println("===============================================================================");

        for (Request request : requests) {
            System.out.println("Request received:");
            System.out.println(request);
            System.out.println();

            System.out.println("Requested service date before request processing:");
            System.out.println(request.getServiceDate());

            Response response = reservationSystem.processCustomerRequest(request);

            System.out.println("Requested service date after request processing:");
            System.out.println(request.getServiceDate());

            System.out.println("Response sent:");
            System.out.println(response);
            System.out.println();
        }

        System.out.println("===============================================================================");
        System.out.println();

        /**
         * Test ReservationSystem getReservations()
         */
        List<Reservation> reservations = reservationSystem.getReservations();

        System.out.println("===============================================================================");
        System.out.println("Testing getReservations() =====================================================");
        System.out.println("===============================================================================");

        System.out.println("All current reservations in the system:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("===============================================================================");
        System.out.println();

        /**
         * Test CalendarDate getReservations()
         */
        date = ReservationSystem.getDate(new CalendarDate(2015, 11, 10, Day.TUE.getCode()));
        reservations = date.getReservations();

        System.out.println("===============================================================================");
        System.out.println("Testing (CalendarDate) getReservations() ======================================");
        System.out.println("===============================================================================");

        System.out.println("Date:");
        System.out.println(date);

        System.out.println("All current reservations for the date:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }

        System.out.println("===============================================================================");
        System.out.println();
    }


    /**
     * Test Supervisor createSchedule()
     */
    private static List<ScheduleTuple> createSchedules(SupervisorInterface supervisor) {
        List<ScheduleTuple> scheduleTuples = new ArrayList<>();

        List<Day> days = new ArrayList<>();
        days.add(Day.MON);
        days.add(Day.WED);
        days.add(Day.FRI);

        List<TimeSlot> slots = new ArrayList<>();

        Time start = new Time(9, TimePeriod.AM);
        Time end = new Time(12, TimePeriod.PM);
        int maxReservations = 1;
        TimeSlot slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(12, TimePeriod.PM);
        end = new Time(5, TimePeriod.PM);
        maxReservations = 3;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(5, TimePeriod.PM);
        end = new Time(8, TimePeriod.PM);
        maxReservations = 2;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        CalendarDate startDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 9, Day.MON.getCode()));
        CalendarDate endDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 30, Day.MON.getCode()));

        ScheduleTuple scheduleTuple = supervisor.createSchedule(days, slots, startDate, endDate);
        scheduleTuples.add(scheduleTuple);

        days = new ArrayList<>();
        days.add(Day.TUE);
        days.add(Day.THU);

        slots = new ArrayList<>();

        start = new Time(9, TimePeriod.AM);
        end = new Time(5, TimePeriod.PM);
        maxReservations = 2;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(5, TimePeriod.PM);
        end = new Time(8, TimePeriod.PM);
        maxReservations = 1;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        scheduleTuple = supervisor.createSchedule(days, slots, startDate, endDate);
        scheduleTuples.add(scheduleTuple);

        days = new ArrayList<>();
        days.add(Day.MON);
        days.add(Day.FRI);

        slots = new ArrayList<>();

        start = new Time(9, TimePeriod.AM);
        end = new Time(11, TimePeriod.AM);
        maxReservations = 2;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(11, TimePeriod.AM);
        end = new Time(3, TimePeriod.PM);
        maxReservations = 3;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(3, TimePeriod.PM);
        end = new Time(6, TimePeriod.PM);
        maxReservations = 1;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        startDate = ReservationSystem.getDate(new CalendarDate(2015, 12, 1, Day.TUE.getCode()));
        endDate = ReservationSystem.getDate(new CalendarDate(2015, 12, 31, Day.THU.getCode()));

        scheduleTuple = supervisor.createSchedule(days, slots, startDate, endDate);
        scheduleTuples.add(scheduleTuple);

        days = new ArrayList<>();
        days.add(Day.TUE);
        days.add(Day.WED);
        days.add(Day.THU);

        slots = new ArrayList<>();

        start = new Time(9, TimePeriod.AM);
        end = new Time(1, TimePeriod.PM);
        maxReservations = 2;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        start = new Time(1, TimePeriod.PM);
        end = new Time(6, TimePeriod.PM);
        maxReservations = 3;
        slot = new TimeSlot(start, end, maxReservations);
        slots.add(slot);

        scheduleTuple = supervisor.createSchedule(days, slots, startDate, endDate);
        scheduleTuples.add(scheduleTuple);

        return scheduleTuples;
    }


    /**
     * Test Customer makeRequest()
     */
    private static List<Request> createRequests(List<Customer> customers) {
        List<Request> requests = new ArrayList<>();

        Customer customer = customers.get(0);
        RequestType requestType = RequestType.DELIVERY;
        CalendarDate serviceDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 10, Day.TUE.getCode()));
        Time serviceTime = new Time(12, TimePeriod.PM);

        Request request = customer.makeRequest(requestType, serviceDate, serviceTime);
        requests.add(request);

        customer = customers.get(1);
        requestType = RequestType.PICKUP;
        serviceDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 17, Day.TUE.getCode()));
        serviceTime = new Time(10, TimePeriod.AM);

        request = customer.makeRequest(requestType, serviceDate, serviceTime);
        requests.add(request);

        customer = customers.get(2);
        requestType = RequestType.DELIVERY;
        serviceDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 25, Day.WED.getCode()));
        serviceTime = new Time(1, TimePeriod.PM);

        request = customer.makeRequest(requestType, serviceDate, serviceTime);
        requests.add(request);

        customer = customers.get(3);
        requestType = RequestType.PICKUP;
        serviceDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 25, Day.WED.getCode()));
        serviceTime = new Time(2, TimePeriod.PM);

        request = customer.makeRequest(requestType, serviceDate, serviceTime);
        requests.add(request);

        customer = customers.get(4);
        requestType = RequestType.DELIVERY;
        serviceDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 25, Day.WED.getCode()));
        serviceTime = new Time(3, TimePeriod.PM);

        request = customer.makeRequest(requestType, serviceDate, serviceTime);
        requests.add(request);

        return requests;
    }


    /**
     * Test Supervisor modifyScheduleForDateRange()
     */
    private static void modifySchedules(SupervisorInterface supervisor) {
        CalendarDate startDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 20, Day.FRI.getCode()));
        CalendarDate endDate = ReservationSystem.getDate(new CalendarDate(2015, 11, 30, Day.MON.getCode()));

        List<Day> days = new ArrayList<>();
        days.add(Day.MON);
        days.add(Day.WED);
        days.add(Day.FRI);

        Time start = new Time(12, TimePeriod.PM);
        Time end = new Time(5, TimePeriod.PM);
        TimeSlot slot = new TimeSlot(start, end);

        int newMaxReservations = 2;

        supervisor.modifyScheduleForDateRange(startDate, endDate, days, slot, newMaxReservations);
    }

}
