package contact.store;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sofia on 2018-12-01.
 */

/**
 * Code written during a timed online coding test.
 */

/**
 * Problem:
 *
 * 1. Implement a comparator for sorting Contact objects by nationalId.
 *    Assign it to variable contactComparator.
 *
 * 2. Implement an in-memory contact store with three methods declared in ContactStore interface.
 *    Assign it to variable inMemoryStore.
 */
public class InMemoryContactDataStoreApp {

    private Comparator<Contact> contactComparator;
    private ContactDataStore inMemoryStore;

    public InMemoryContactDataStoreApp() {
        contactComparator = new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return Long.compare(c1.getNationalId(), c2.getNationalId());
            }
        };

        inMemoryStore = new ContactDataStore() {
            Map<String, Contact> map = new HashMap<>();

            @Override
            public void command(String command, Contact data) {
                switch(command) {
                    case "ADD":
                        map.put(data.getName(), data);
                        break;
                    case "UPDATE":
                        map.put(data.getName(), data);
                        break;
                    case "DELETE":
                        map.remove(data.getName());
                        break;
                }
            }

            @Override
            public List<Contact> find(String partialName) {
                return map.keySet().stream()
                        .filter(k -> k.contains(partialName))
                        .map(k -> map.get(k))
                        .collect(Collectors.toList());
            }

            @Override
            public List<Contact> all() {
                return map.values().stream()
                        .collect(Collectors.toList());
            }
        };
    }

    public Comparator<Contact> getContactComparator() {
        return contactComparator;
    }

    public ContactDataStore getInMemoryStore() {
        return inMemoryStore;
    }





    public static void main(String[] args) {
        InMemoryContactDataStoreApp app = new InMemoryContactDataStoreApp();
        app.getInMemoryStore().command("ADD", new Contact("First", 9919991L));
        app.getInMemoryStore().command("ADD", new Contact("Second", 992991L));
        app.getInMemoryStore().command("UPDATE", new Contact("Second", 992992L));
        app.getInMemoryStore().command("DELETE", new Contact("First", 9919991L));
        app.getInMemoryStore().command("ADD", new Contact("Third", 3L));
        app.getInMemoryStore().command("ADD", new Contact("Fourth", 1L));

        List<Contact> found = app.getInMemoryStore().find("Sec");
        System.out.println(found);

        List<Contact> all = app.getInMemoryStore().all();
        Collections.sort(all, app.getContactComparator());
        System.out.println(all);
    }

}
