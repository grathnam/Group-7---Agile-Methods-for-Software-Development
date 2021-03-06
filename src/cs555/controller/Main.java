package cs555.controller;

import cs555.model.Tree;
import cs555.util.TreeUtils;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out
                    .println("You should provide an argument for which file you want to read. Defaulting to test.ged");
            args = new String[1];
            args[0] = "input/test.ged";
        }

        Tree tree = new Tree();
        GEDParser gedParser = new GEDParser(tree);
        Arrays.stream(args).filter(s -> s.contains(".ged"))
                .forEach(gedParser::readGED);

        TreeUtils treeUtils = new TreeUtils(tree);

        treeUtils.printPeople();
        treeUtils.printFamilies();

//         Sprint 1
        treeUtils.printbdeexceedcurrentdate(); // US 01.1
        treeUtils.printmardivdateexcurdate(); // US 01.2
        treeUtils.printinvalidmaranddivorcedate(); // US 05
        treeUtils.listSiblingsOrdedByAge(); // US 28
        treeUtils.printIncorrectGenderRoles(); // US 21
        treeUtils.listMarriageNotAfter14(); // US 10
        treeUtils.listFamilyNotFewerThan15Siblings(); // US 15
        treeUtils.ListOrphans(); // US 33
        treeUtils.listBirthAfterParentsDeath(); // US 09

//         Sprint 2
        treeUtils.listNonUniqueNamesAndBirthdays();// US23
        treeUtils.listDeceased();// US29
        treeUtils.printmardatebefbirdate(); // US 02
        treeUtils.printdivafterdeathdate(); // US 06
        treeUtils.printParentTooOld(); // US 12
        treeUtils.printMaleLastName();// US 16
        treeUtils.printLivingMarried();// US30
        treeUtils.printRecentBirths();// US35

//         Sprint 3
        treeUtils.listLivingSingles();
        treeUtils.listNonUniqueNamesInFamily();
        treeUtils.printbddateafterdeathdate(); // US 03
        treeUtils.printdddateandbdate();// US 07.1
        treeUtils.printchkcurdtlesthan150yrsbddate();// US 07.2
        treeUtils.printSiblingSpacingInvalid(); // US 13
        treeUtils.printMarriagesToDescendants(); // US 17
        treeUtils.printRecentDeaths();// US 36
        treeUtils.printUpcommingBirthdays();// US 38

        // Sprint 4
        treeUtils.listMultipleBirths();
        treeUtils.printPeople();
        treeUtils.printMulBirthMoreThanFive(); // US 14
        treeUtils.printSiblingsMarriage(); // US 18
        treeUtils.marrdtbefdivdt(); //US 04
        treeUtils.chdbdafptmardate(); //US 08.1
        treeUtils.chdbdbfptdivdate();//US 08.2
        treeUtils.printUpcommingAniversaries();//US39
    }
}