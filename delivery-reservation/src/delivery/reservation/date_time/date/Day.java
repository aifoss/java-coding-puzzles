package delivery.reservation.date_time.date;

/**
 * Created by sofia on 4/28/17.
 */
public enum Day {

    SUN("Sun", 0),
    MON("Mon", 1),
    TUE("Tue", 2),
    WED("Wed", 3),
    THU("Thu", 4),
    FRI("Fri", 5),
    SAT("Sat", 6);

    private String label;
    private int code;

    Day(String label, int code) {
        this.label = label;
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return label;
    }

    public static Day fromCode(int code) {
        return values()[code];
    }

}
