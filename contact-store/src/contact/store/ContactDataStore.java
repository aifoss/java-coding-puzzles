package contact.store;

import java.util.List;

/**
 * Created by sofia on 2018-12-01.
 */

/**
 * Code given during a timed online coding test.
 */

/**
 * Interface representing contact store with three declared methods.
 */
public interface ContactDataStore {

    // perform action specified by command.
    // ADD: add a new contact to the contact data store.
    // UPDATE: update a given contact in the contact data store.
    // DELETE: delete a given contact from the contact data store.
    void command(String command, Contact data);

    // return list of contacts whose names contain the given partialName.
    List<Contact> find(String partialName);

    // return list of all contacts.
    List<Contact> all();

}
