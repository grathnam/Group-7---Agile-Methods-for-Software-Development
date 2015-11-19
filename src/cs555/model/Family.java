package cs555.model;

import cs555.util.AgeUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Family {
    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseLenient().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter();
    private String id;
    private Person husband;
    private Person wife;
    private List<Person> members;
    private List<Person> child;
    private LocalDate marr;
    private LocalDate div;

    public Family(String id) {
        this.child = new ArrayList<>();
        this.members = new ArrayList<>();
        this.id = id;
    }

    public Person getHusband() {
        return husband;
    }

    public void setHusband(Person husband) {
        if (this.husband != null)
            members.remove(this.husband);
        this.husband = husband;
        members.add(husband);
    }

    public Person getWife() {
        return wife;
    }

    public void setWife(Person wife) {
        if (this.wife != null)
            members.remove(this.wife);
        this.wife = wife;
        members.add(wife);
    }

    public LocalDate getMarr() {
        return marr;
    }

    public void setMarr(String marr) {
        if (AgeUtils.isBadDate(marr))
            System.out.println(marr + " is a bad date!");
        try {
            this.marr = LocalDate.parse(marr, dateTimeFormatter);
        } catch (DateTimeParseException e){
            System.out.println(marr + " is a bad date!");
        }
    }

    public LocalDate getDiv() {
        return div;
    }

    public void setDiv(String div) {
        if (AgeUtils.isBadDate(div))
            System.out.println(div + " is a bad date!");
        try {
            this.div = LocalDate.parse(div, dateTimeFormatter);
        } catch (DateTimeParseException e){
            System.out.println(div + " is a bad date!");
        }
    }

    public String getId() {
        return id;
    }

    public void addChild(Person person) {
        members.add(person);
        child.add(person);
    }

    public void setChild(List<Person> child) {
        this.child = child;
    }

    public List<Person> getChild() {
        return child;
    }

    public List<Person> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "["+id+"]\t"+husband.getName()+"\t+\t"+wife.getName();
    }
}
