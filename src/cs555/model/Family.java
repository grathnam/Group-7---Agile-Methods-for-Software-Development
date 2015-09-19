package cs555.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class Family {
    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseLenient().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter();
    private String id;
    private Person husband;
    private Person wife;
    private List<Person> child;
    private LocalDate marr;
    private LocalDate div;

    public Family(String id) {
        this.child = new ArrayList<>();
        this.id = id;
    }

    public Person getHusband() {
        return husband;
    }

    public void setHusband(Person husband) {
        this.husband = husband;
    }

    public Person getWife() {
        return wife;
    }

    public void setWife(Person wife) {
        this.wife = wife;
    }

    public LocalDate getMarr() {
        return marr;
    }

    public void setMarr(String marr) {
        this.marr = LocalDate.parse(marr, dateTimeFormatter);
    }

    public LocalDate getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = LocalDate.parse(div, dateTimeFormatter);
    }

    public String getId() {
        return id;
    }

    public void addChild(Person person) {
        child.add(person);
    }

    @Override
    public String toString() {
        return "["+id+"]\t"+husband.getName()+"\t+\t"+wife.getName();
    }
}
