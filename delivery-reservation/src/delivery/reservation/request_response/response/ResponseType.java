package delivery.reservation.request_response.response;

/**
 * Created by sofia on 4/28/17.
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
