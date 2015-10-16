package cs555.util;

import cs555.model.Family;
import cs555.model.Person;
import cs555.model.Tree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TreeUtils {
    private final Tree tree;

    public TreeUtils(Tree tree) {
        this.tree = tree;
    }

    // US 01
    public void printbdeexceedcurrentdate() {
        printHeader("Birth Date exceeding current date");
        tree.getPeople().stream().filter(p -> p.getBirth().isAfter(LocalDate.now())).forEach(System.out::println);
        tree.getPeople().stream().filter(p -> p.getBirth().isAfter(LocalDate.now())).forEach(p -> System.out.println(p.getName() + " has an invalid birthday of: " + p.getBirth()));
        printHeader("Death Date exceeding current date");
        tree.getPeople().stream().filter(p -> p.getDeath() != null && p.getDeath().isAfter(LocalDate.now())).forEach(System.out::println);
        tree.getPeople().stream().filter(p -> p.getDeath() != null && p.getDeath().isAfter(LocalDate.now())).forEach(p -> System.out.println(p.getName() + " has an invalid death day of: " + p.getDeath()));


    }

    // US 05 Marriage should occur before death of either of spouse
    public void printinvalidmaranddivorcedate() // prints marriage date and divorce exceeding current date
    {
        printHeader("Marriage Date exceeding current date");
        tree.getFamilies().stream().filter(f -> f.getDiv() != null && f.getDiv().isAfter(LocalDate.now())).forEach(f -> System.out.println("Family " + f.getId() + " has an invalid divorse date of: " + f.getDiv()));
        printHeader("Divorce Date exceeding current date");
        tree.getFamilies().stream().filter(f -> f.getMarr() != null && f.getMarr().isAfter(LocalDate.now())).forEach(f -> System.out.println("Family " + f.getId() + " has an invalid marriage date of: " + f.getMarr()));

    }
    
    //Sprint 2  //US 02
   public void printmardatebefbirdate() // US 02 - prints marraige date that is before birth of a person
    {     printHeader(" Sprint 2:  US 02 ");
	     printHeader(" Marraige dates that is  before the birth of a person ");
    	
    	 for( Person p : tree.getPeople()) 
    	 { //beginning of outer for loop
    	 for(Family f:p.getFams())
    	 { //beginning of inner for loop
    	   if(f.getMarr()!=null && p.getBirth()!=null)
    	   { // beginning of outer if stmt
    		   if(f.getMarr().isBefore(p.getBirth()))
    	     { //beginning of inner if stmt
    	        
    	        System.out.println("  " +p.getName()+" has marraige date : " +f.getMarr()+  " and " + " Birth date:  " +p.getBirth() );
    	        
    	     
    	     } //end of outer if stmt
    	   
    	   } // end of outer if stmt
    	   
    	 } // end of inner for loop
    	 } //end of for outer for loop
    	 
    }//end of US 02

    // US28 Order siblings by age
    // List siblings in families by age
    public void listSiblingsOrdedByAge() {
        printHeader("Ordering Siblings by Age");
        tree.getFamilies()
                .stream()
                .collect(
                        Collectors.toMap(f -> f,
                                f -> f.getChild().stream().sorted((p1, p2) -> p1.getBirth().compareTo(p2.getBirth())).collect(Collectors.toList())))
                .entrySet().stream().filter(e -> !e.getValue().isEmpty())
                .forEach(e -> {
                    System.out.println(e.getKey());
                    e.getValue().forEach(p -> System.out.println(" >\t" + p));
                });
    }

    // US21 Correct gender for role
    // Husband in family should be male and wife in family should be female
    public void printIncorrectGenderRoles() {
        printHeader("Printing Incorrect Gender Roles");
        tree.getPeople()
                .stream()
                .filter(p -> !p.getFams().stream().filter(f -> (p.getSex().equalsIgnoreCase("M") && f
                        .getWife().getId().equals(p.getId())) ? true : (p.getSex().equalsIgnoreCase("F") && f
                        .getHusband().getId().equals(p.getId())))
                        .collect(Collectors.toList()).isEmpty())
                .forEach(System.out::println);
    }

    // US23 Unique name and birth date
    // No more than one individual with the same name and birth date should
    // appear in a GEDCOM file
    public void listNonUniqueNamesAndBirthdays() {
        printHeader("Listing People with Non-Unique Names and Birthdays");
        tree.getPeople()
                .stream()
                .collect(
                        Collectors.toMap(
                                p -> p.getName()
                                        .concat(p.getBirth().toString()),
                                v -> new ArrayList(Arrays.asList(v)),
                                (v1, v2) -> v1.addAll(v2) ? v1 : v1))
                .entrySet().stream().filter(e -> e.getValue().size() > 1)
                .forEach(e -> System.out.println(e.getValue()));
    }

    // US29 List deceased
    // List all deceased individuals in a GEDCOM file
    public void listDeceased() {
        printHeader("Printing Deceased");
        tree.getPeople().stream().filter(p -> p.getDeath() != null)
                .forEach(System.out::println);
    }

    // US25 Unique first names in families
    // No more than one child with the same name and birth date should appear in
    // a family
    public void listNonUniqueNamesInFamily() {
        printHeader("Printing Non-Unique First Names and Birth Dates In Each Family");
        tree.getFamilies()
                .stream()
                .collect(
                    Collectors.toMap(f -> f, f -> f.getMembers().stream()
                        .collect(Collectors.toMap(p -> p.getName().split(" ", 2)[0].concat(p.getBirth().toString()),
                                v -> new ArrayList(Arrays.asList(v)), (v1, v2) -> v1.addAll(v2) ? v1 : v1)).entrySet().stream().filter(e -> e.getValue().size() > 1)
                        .map(e -> e.getValue())
                        .collect(Collectors.toList())))
                .entrySet().stream().filter(e -> !e.getValue().isEmpty())
                .forEach(e -> {
                    System.out.println(e.getKey());
                    e.getValue().forEach(p -> System.out.println(" >\t" + p));
                });
    }

    // US31 List living single
    // List all living people over 30 who have never been married in a GEDCOM
    // file
    public void listLivingSingles() {
        printHeader("Listing Living Singles");
        tree.getPeople()
                .stream()
                .filter(p -> (p.getFams().size() == 0)
                        && AgeUtils.getAge(p.getBirth()) > 30)
                .forEach(System.out::println);
    }

    // US32 List multiple births
    // List all multiple births in a GEDCOM file
    public void listMultipleBirths() {
        printHeader("Listing People with the Same Birthdays");
        tree.getPeople()
                .stream()
                .collect(
                        Collectors.toMap(p -> p.getBirth(), v -> new ArrayList(
                                        Arrays.asList(v)),
                                (v1, v2) -> v1.addAll(v2) ? v1 : v1))
                .entrySet().stream().filter(e -> e.getValue().size() > 1)
                .forEach(e -> System.out.println(e.getValue()));
    }

    public void printPeople() {
        printHeader("Printing People");
        tree.getPeople().stream()
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .forEach(System.out::println);
    }

    public void printFamilies() {
        printHeader("Printing Families");
        tree.getFamilies().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
                .forEach(System.out::println);
    }

    public void printFamiliesWithChildren() {
        printHeader("Printing Families with Children");
        tree.getFamilies().stream()
                .sorted((f1, f2) -> f1.getId().compareTo(f2.getId()))
                .forEach(f -> {
                    System.out.println(f);
                    f.getChild().forEach(c -> System.out.println(" >\t" + c));
                });
    }

    private void printHeader(String h) {
        System.out.printf("\n------------\n%s\n\n", h);
    }

    /**
     * US10 Marriage after 14 (Marriage should be at least 14 years after birth
     * of both spouses)
     */
    public void listMarriageNotAfter14() {
        printHeader("Printing Marriage not at least 14 years after birth");
        tree.getFamilies()
                .stream()
                .filter(f -> AgeUtils.getYearGap(f.getHusband().getBirth(),
                        f.getMarr()) < 14
                        || AgeUtils.getYearGap(f.getWife().getBirth(),
                        f.getMarr()) < 14).forEach(System.out::println);
    }

    /**
     * US15 Fewer than 15 siblings (There should be fewer than 15 siblings in a
     * family)
     */
    public void listFamilyNotFewerThan15Siblings() {
        printHeader("Printing Families have more than 15 Siblings");
        tree.getFamilies().stream().filter(f -> f.getChild().size() >= 15)
                .forEach(System.out::println);
    }

    /**
     * US09 Birth before death of parents
     */
    public void listBirthAfterParentsDeath() {
        printHeader("Printing the People born after death of parents");
        tree.getFamilies().forEach(f -> f.getChild().stream()
                .filter(c -> (f.getHusband().getDeath() != null && AgeUtils.getMonthGap(
                        c.getBirth(), f.getHusband().getDeath()) > 9)
                        || (f.getWife().getDeath() != null && AgeUtils.getMonthGap(c.getBirth(), f.getWife().getDeath()) > 0)).forEach(System.out::println));


    }

    /**
     * US33 PArents are death and less than 18 years old
     */
    public void ListOrphans() {
        printHeader("Printing the orphans");
        for (Person p : tree.getPeople()) {
            if (AgeUtils.getAge(p.getBirth()) < 18) {
                for (Family f : p.getFamc()) {
                    if ((f.getHusband().getDeath() != null) && (f.getWife().getDeath() != null))
                        System.out.println(p);
                }
            }

        }
    }


}

