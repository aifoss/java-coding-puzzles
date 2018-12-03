package contact.store;

/**
 * Created by sofia on 2018-12-01.
 */

/**
 * Code given during a timed online coding test.
 */

/**
 * Contact class representing contact info.
 */
public class Contact {

    private String name;
    private long nationalId;

    public Contact(String name, long nationalId) {
        this.name = name;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public long getNationalId() {
        return nationalId;
    }

    @Override
    public String toString() {
        return "["+name+":"+nationalId+"]";
    }

}
