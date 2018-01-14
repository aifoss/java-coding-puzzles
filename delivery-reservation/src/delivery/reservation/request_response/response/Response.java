package delivery.reservation.request_response.response;

/**
 * Created by sofia on 11/7/15.
 */
public class Response {

    private ResponseType responseType;
    private long customerId;

    public Response(ResponseType responseType, long customerId) {
        this.responseType = responseType;
        this.customerId = customerId;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public long getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("RESPONSE [");
        result.append("Response Type: ");
        result.append(responseType);
        result.append("; ");
        result.append("Customer ID: ");
        result.append(customerId);
        result.append("]");
        return result.toString();
    }

}
