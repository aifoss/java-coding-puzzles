package delivery.reservation.request_response.request;

import delivery.reservation.date_time.date.CalendarDate;
import delivery.reservation.date_time.time.Time;

import java.util.Date;

/**
 * Created by sofia on 11/7/15.
 */
public class Request {

    private RequestType requestType;
    private long customerId;
    private Date requestDate;
    private CalendarDate serviceDate;
    private Time serviceTime;

    public Request(RequestType requestType, long customerId, CalendarDate serviceDate, Time serviceTime) {
        this.requestType = requestType;
        this.customerId = customerId;
        this.requestDate = new Date();
        this.serviceDate = serviceDate;
        this.serviceTime = serviceTime;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public CalendarDate getServiceDate() {
        return serviceDate;
    }

    public Time getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("REQUEST [");
        result.append(System.getProperty("line.separator"));
        result.append("Request Type: ");
        result.append(requestType);
        result.append("; ");
        result.append("Customer ID: ");
        result.append(customerId);
        result.append("; ");
        result.append("Request Date: ");
        result.append(requestDate);
        result.append("; ");
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Service Date: ");
        result.append(serviceDate.toShortString());
        result.append(System.getProperty("line.separator"));
        result.append("\t").append("Service Time: ");
        result.append(serviceTime);
        result.append("]");
        return result.toString();
    }

}
