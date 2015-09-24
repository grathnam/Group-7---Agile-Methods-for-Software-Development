package cs555.controller;

import cs555.model.Tree;
import cs555.util.TreeUtils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    	if (args.length == 0) {
            System.out.println("You should provide an argument for which file you want to read. Defaulting to test.ged");
            args = new String[1];
    		args[0] = "test.ged";
    	}

        Tree tree = new Tree();
        GEDParser gedParser = new GEDParser(tree);
        Arrays.stream(args).filter(s -> s.contains(".ged")).forEach(gedParser::readGED);

        TreeUtils treeUtils = new TreeUtils(tree);
        treeUtils.printPeople();
        treeUtils.printFamilies();
//        treeUtils.printIncorrectGenderRoles();
//        treeUtils.orderSiblingsByAge();
//        treeUtils.printFamiliesWithChildren();

    }
}