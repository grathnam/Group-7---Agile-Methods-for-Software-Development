package cs555.util;

import cs555.model.Tree;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TreeUtils {
    private final Tree tree;

    public TreeUtils(Tree tree) {
        this.tree = tree;
    }

    //Made these one line for a challenge :) Probably not the clearest way for some of them.
    //need to test
    public void orderSiblingsByAge() {
        printHeader("Ordering Siblings by Age");
        tree.getFamilies().stream().forEach(f -> f.setChild(f.getChild().stream().sorted((p1, p2) -> p1.getBirth().compareTo(p2.getBirth())).collect(Collectors.toList())));
    }

    //need to test
    public void printIncorrectGenderRoles() {
        printHeader("Printing Incorrect Gender Roles");
        tree.getPeople().stream().filter(p -> !p.getFams().stream().filter(f -> (p.getSex().equalsIgnoreCase("M") && f.getWife().getId().equals(p.getId())) ? true : (p.getSex().equalsIgnoreCase("F") && f.getHusband().getId().equals(p.getId()))).collect(Collectors.toList()).isEmpty()).forEach(System.out::println);
    }

    //need to test
    public void listDeceased() {
        printHeader("Printing Deceased");
        tree.getPeople().stream().filter(p -> p.getDeath() != null).forEach(System.out::println);
    }

    //need to test
    public void listUniqueNamesInFamily() {
        printHeader("Printing Unique Names In Each Family");
        tree.getFamilies().forEach(f -> {
            System.out.println(f);
            f.getMembers().stream().collect(Collectors.toMap(p->p.getName(), p->1, Integer::sum)).entrySet().stream().filter(e -> e.getValue() == 1).forEach(e-> System.out.println(e.getValue()));
        });
    }

    //need to test
    public void listNonUniqueNamesAndBirthdays() {
        printHeader("Listing People with Non-Unique Names and Birthdays");
        tree.getPeople().stream().collect(Collectors.toMap(p->p.getName().concat(p.getBirth().toString()),v-> Arrays.asList(v), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e->e.getValue().forEach(System.out::println));
    }

    //need to test
    public void listLivingSingles() {
        printHeader("Listing Living Livings");
        tree.getPeople().stream().filter(p-> (p.getFams().size()==0 || p.getSex().equalsIgnoreCase("M") && p.getFams().stream().map(f->f.getWife().getDeath()!=null || f.getDiv() != null).reduce((a, b) -> a.booleanValue() && b.booleanValue()).get() || p.getSex().equalsIgnoreCase("F") && p.getFams().stream().map(f->f.getHusband().getDeath()!=null).reduce((a, b) -> a.booleanValue() && b.booleanValue()).get())).forEach(System.out::println);
    }

    //need to test
    public void listMultipleBirths() {
        printHeader("Listing People with the Same Birthdays");
        tree.getPeople().stream().collect(Collectors.toMap(p->p.getBirth(), v->Arrays.asList(v), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1).forEach(e -> e.getValue().forEach(System.out::println));
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
