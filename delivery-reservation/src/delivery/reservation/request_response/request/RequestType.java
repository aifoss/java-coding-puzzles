package delivery.reservation.request_response.request;

/**
 * Created by sofia on 4/28/17.
 */
public enum RequestType {

    DELIVERY("delivery"),
    PICKUP("pickup");

    private String code;

    RequestType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
