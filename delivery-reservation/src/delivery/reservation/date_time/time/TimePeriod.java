package delivery.reservation.date_time.time;

/**
 * Created by sofia on 11/7/15.
 */
public enum TimePeriod {

    AM("am"),
    PM("pm");

    private String label;

    TimePeriod(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}
