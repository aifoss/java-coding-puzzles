package delivery.reservation.date_time.time;

/**
 * Created by sofia on 4/28/17.
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
