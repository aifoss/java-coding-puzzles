package delivery.reservation.request_response.response;

/**
 * Created by sofia on 11/7/15.
 */
public enum ResponseType {

    SUCCESS("Success"),
    FAILURE("Failure");

    private String code;

    ResponseType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}
