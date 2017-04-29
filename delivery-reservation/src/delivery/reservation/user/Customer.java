package delivery.reservation.user;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.time.Time;
import delivery.reservation.request_response.request.Request;
import delivery.reservation.request_response.request.RequestType;

/**
 * Created by sofia on 4/28/17.
 */
public class Customer {

    private long id;

    public Customer(long id) {
        this.id = id;
    }

    public Request makeRequest(RequestType requestType, CalendarDate serviceDate, Time serviceTime) {
        return new Request(requestType, id, serviceDate, serviceTime);
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer " + String.valueOf(id);
    }

}
