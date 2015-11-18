package cs555.model;

import cs555.util.AgeUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().parseLenient().parseCaseInsensitive().appendPattern("dd MMM yyyy").toFormatter();
    private List<Family> famc;
    private List<Family> fams;
    private String id;
    private String name;
    private String sex;
    private LocalDate birth;
    private LocalDate death;

    public Person(String id) {
        this.famc = new ArrayList<>();
        this.fams = new ArrayList<>();
        this.id = id;
    }

    public List<Family> getFamc() {
        return famc;
    }

    public List<Family> getFams() {
        return fams;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = LocalDate.parse(birth, dateTimeFormatter);
    }

    public LocalDate getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = LocalDate.parse(death, dateTimeFormatter);
    }

    public void addFamc(Family family) {
        famc.add(family);
    }

    public void addFams(Family family) {
        fams.add(family);
    }

    //US27	Include individual ages
    //Include person's current age when listing individuals
    @Override
    public String toString() {
        return "["+id+"] ("+ birth +") "+name+" (Age:"+ AgeUtils.getAge(birth)+")";
    }
}
