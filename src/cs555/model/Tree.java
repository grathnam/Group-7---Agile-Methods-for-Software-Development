package cs555.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a Loading Family Tree. People and Families are automatically loaded if they do not exist.
 * Just a container for families and people. Makes things cleaner.
 */
public class Tree {
    private Map<String, Family> families;
    private Map<String, Person> people;

    public Tree() {
        families = new HashMap<>();
        people = new HashMap<>();
    }

    public Collection<Family> getFamilies() {
        return families.values();
    }

    public Collection<Person> getPeople() {
        return people.values();
    }

    public Family getFamily(String id) {
        if (families.containsKey(id))
            return families.get(id);
        Family f = new Family(id);
        families.put(id, f);
        return f;
    }

    public Person getPerson(String id) {
        if (people.containsKey(id))
            return people.get(id);
        Person p = new Person(id);
        people.put(id, p);
        return p;
    }
}
