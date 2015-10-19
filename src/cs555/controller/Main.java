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

		// Sprint 1
		treeUtils.printbdeexceedcurrentdate(); // US 01
		treeUtils.printinvalidmaranddivorcedate(); // US 05
		treeUtils.listSiblingsOrdedByAge(); // US 28
		treeUtils.printIncorrectGenderRoles(); // US 21
		treeUtils.listMarriageNotAfter14(); // US 10
		treeUtils.listFamilyNotFewerThan15Siblings(); // US 15
		treeUtils.ListOrphans(); // US 33
		treeUtils.listBirthAfterParentsDeath(); // US 09

		// Sprint 2
		// treeUtils.listNonUniqueNamesAndBirthdays();
		// treeUtils.listDeceased();
		treeUtils.printmardatebefbirdate(); // US 02
		treeUtils.printdivafterdeathdate(); // US 06
		treeUtils.printParentTooOld(); // US 12
		treeUtils.printMaleLastName();// US 16
		
		// Sprint 3
		// treeUtils.listLivingSingles();
		// treeUtils.listNonUniqueNamesInFamily();

		// Sprint 4
		// treeUtils.listMultipleBirths();
	}
}
