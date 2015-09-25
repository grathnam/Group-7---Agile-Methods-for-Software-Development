package cs555.util;

import cs555.model.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TreeUtils {
    private final Tree tree;

    public TreeUtils(Tree tree) {
        this.tree = tree;
    }

    //Only used streams for a challenge :) Probably not the clearest way for some of them.

    //US28	Order siblings by age
    //List siblings in families by age
    public void listSiblingsOrdedByAge() {
        printHeader("Ordering Siblings by Age");
        tree.getFamilies().stream().forEach(f -> f.setChild(f.getChild().stream().sorted((p1, p2) -> p1.getBirth().compareTo(p2.getBirth())).collect(Collectors.toList())));
        printFamiliesWithChildren();
    }

    //US21	Correct gender for role
    //Husband in family should be male and wife in family should be female
    public void printIncorrectGenderRoles() {
        printHeader("Printing Incorrect Gender Roles");
        tree.getPeople().stream().filter(p -> !p.getFams().stream().filter(f -> (p.getSex().equalsIgnoreCase("M") && f.getWife().getId().equals(p.getId())) ? true : (p.getSex().equalsIgnoreCase("F") && f.getHusband().getId().equals(p.getId()))).collect(Collectors.toList()).isEmpty()).forEach(System.out::println);
    }

    //US23	Unique name and birth date
    //No more than one individual with the same name and birth date should appear in a GEDCOM file
    public void listNonUniqueNamesAndBirthdays() {
        printHeader("Listing People with Non-Unique Names and Birthdays");
        tree.getPeople().stream().collect(Collectors.toMap(p -> p.getName().concat(p.getBirth().toString()),v-> new ArrayList(Arrays.asList(v)), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e -> System.out.println(e.getValue()));
    }

    //US29	List deceased
    //List all deceased individuals in a GEDCOM file
    public void listDeceased() {
        printHeader("Printing Deceased");
        tree.getPeople().stream().filter(p -> p.getDeath() != null).forEach(System.out::println);
    }

    //US25	Unique first names in families
    //No more than one child with the same name and birth date should appear in a family
    public void listNonUniqueNamesInFamily() {
        printHeader("Printing Non-Unique First Names and Birth Dates In Each Family");
        tree.getFamilies().stream().collect(Collectors.toMap(f -> f, f -> f.getMembers().stream().collect(Collectors.toMap(p -> p.getName().split(" ", 2)[0].concat(p.getBirth().toString()), v -> new ArrayList(Arrays.asList(v)), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1).map(e -> e.getValue()).collect(Collectors.toList()))).entrySet().stream().filter(e -> !e.getValue().isEmpty()).forEach(e -> {
            System.out.println(e.getKey());
            e.getValue().forEach(p -> System.out.println(" >\t" + p));
        });
    }

    //US31	List living single
    //List all living people over 30 who have never been married in a GEDCOM file
    public void listLivingSingles() {
        printHeader("Listing Living Singles");
        tree.getPeople().stream().filter(p -> (p.getFams().size() == 0) && AgeUtils.getAge(p.getBirth()) > 30).forEach(System.out::println);
    }

    //US32	List multiple births
    //List all multiple births in a GEDCOM file
    public void listMultipleBirths() {
        printHeader("Listing People with the Same Birthdays");
        tree.getPeople().stream().collect(Collectors.toMap(p -> p.getBirth(), v -> new ArrayList(Arrays.asList(v)), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e -> System.out.println(e.getValue()));
    }

    public void printPeople() {
        printHeader("Printing People");
        tree.getPeople().stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId())).forEach(System.out::println);
    }

    public void printFamilies() {
        printHeader("Printing Families");
        tree.getFamilies().stream().sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).forEach(System.out::println);
    }

    public void printFamiliesWithChildren() {
        printHeader("Printing Families with Children");
        tree.getFamilies().stream().sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).forEach(f->{
            System.out.println(f);
            f.getChild().forEach(c -> System.out.println(" >\t" + c));
        });
    }

    private void printHeader(String h) {
        System.out.printf("\n------------\n%s\n\n", h);
    }

}
