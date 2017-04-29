package delivery.reservation.reservation;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.time.Time;
import delivery.reservation.request_response.request.RequestType;

import java.util.Date;

/**
 * Created by sofia on 4/28/17.
 */
public class Reservation implements Comparable<Reservation> {

    private long customerId;
    private Date reservationDate;
    private CalendarDate date;
    private Time time;
    private RequestType type;

    public Reservation(long customerId, Date reservationDate, CalendarDate date, Time time, RequestType type) {
        this.customerId = customerId;
        this.reservationDate = reservationDate;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(Reservation other) {
        int cmp = this.date.compareTo(other.date);
        if (cmp != 0) return cmp;
        cmp = this.time.compareTo(other.time);
        if (cmp != 0) return cmp;
        cmp = this.type.compareTo(other.type);
        if (cmp != 0) return cmp;
        return Long.compare(this.customerId, other.customerId);
    }

    public long getCustomerId() {
        return customerId;
    }

    public CalendarDate getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("RESERVATION [");
        result.append("Date: ");
        result.append(date.toShortString());
        result.append("; ");
        result.append("Time: ");
        result.append(time);
        result.append("; ");
        result.append("Type: ");
        result.append(type);
        result.append("; ");
        result.append("Customer: ");
        result.append(customerId);
        result.append("]");
        return result.toString();
    }

}
