package cs555.controller;

import cs555.model.Tree;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    	if (args.length == 0) {
    		args = new String[1];
    		args[0] = "family2.ged";
    	}    		
        Tree tree = new Tree();
        GEDParser gedParser = new GEDParser(tree);
        Arrays.stream(args).filter(s -> s.contains(".ged")).forEach(gedParser::readGED);
        tree.getPeople().stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId())).forEach(System.out::println);
        tree.getFamilies().stream().sorted((f1, f2) -> f1.getId().compareTo(f2.getId())).forEach(System.out::println);
        //Literally can do a map/filter/reduce/etc for every single sprint
//        List<Person> lessThan150 = tree.getPeople().stream().filter(s -> ChronoUnit.YEARS.between(s.getBirth(), LocalDate.now()) < 150).collect(Collectors.toList());
    }

}