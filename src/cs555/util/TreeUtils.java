package cs555.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cs555.model.Family;
import cs555.model.Person;
import cs555.model.Tree;

public class TreeUtils {
	private final Tree tree;

	public TreeUtils(Tree tree) {
		this.tree = tree;
	}

	// US 01
	public void printbdeexceedcurrentdate() {
		printHeader("Birth Date exceeding current date");
		tree.getPeople().stream()
				.filter(p -> p.getBirth().isAfter(LocalDate.now()))
				.forEach(System.out::println);
		tree.getPeople()
				.stream()
				.filter(p -> p.getBirth().isAfter(LocalDate.now()))
				.forEach(
						p -> System.out.println(p.getName()
								+ " has an invalid birthday of: "
								+ p.getBirth()));
		printHeader("Death Date exceeding current date");
		tree.getPeople()
				.stream()
				.filter(p -> p.getDeath() != null
						&& p.getDeath().isAfter(LocalDate.now()))
				.forEach(System.out::println);
		tree.getPeople()
				.stream()
				.filter(p -> p.getDeath() != null
						&& p.getDeath().isAfter(LocalDate.now()))
				.forEach(
						p -> System.out.println(p.getName()
								+ " has an invalid death day of: "
								+ p.getDeath()));

	}

	public void printmardivdateexcurdate() // US 01.2
	{
		printHeader("Divorce date exceeding current date");

		tree.getFamilies()
				.stream()
				.filter(f -> f.getDiv() != null
						&& f.getDiv().isAfter(LocalDate.now()))
				.forEach(
						f -> System.out.println(" " + f.getMembers()
								+ " has an invalid divorse date of: "
								+ f.getDiv()));
		printHeader("Marriage Date exceeding current date");
		tree.getFamilies()
				.stream()
				.filter(f -> f.getMarr() != null
						&& f.getMarr().isAfter(LocalDate.now()))
				.forEach(
						f -> System.out.println(" " + f.getMembers()
								+ " has an invalid marriage date of: "
								+ f.getMarr()));
	}

	// US 05 Marriage should occur before death of either of spouse
	public void printinvalidmaranddivorcedate() // prints marriage date and
												// divorce exceeding current
												// date
	{
		printHeader("US 05");
		printHeader("Marraige Date exceeding death date");

		for (Person p : tree.getPeople()) {
			for (Family f : p.getFams()) {
				if (f.getMarr() != null && p.getDeath() != null) {
					if (f.getMarr().isAfter(p.getDeath())) {
						System.out.println("  " + p.getName()
								+ " has  marriage date: " + f.getMarr()
								+ " and " + " Death date:  " + p.getDeath());

					}

				} // end of outer if stmt
			} // end of outer for stmt
		} // end of inner for stmt
	}

	// Sprint 2 //US 02
	public void printmardatebefbirdate() // US 02 - prints marraige date that is
											// before birth of a person
	{
		printHeader("Sprint 2: US 02: Marraige dates that is  before the birth of a person ");

		for (Person p : tree.getPeople()) { // beginning of outer for loop
			for (Family f : p.getFams()) { // beginning of inner for loop
				if (f.getMarr() != null && p.getBirth() != null) { // beginning
																	// of outer
																	// if stmt
					if (f.getMarr().isBefore(p.getBirth())) { // beginning of
																// inner if stmt

						System.out.println("  " + p.getName()
								+ " has marraige date : " + f.getMarr()
								+ " and " + " Birth date:  " + p.getBirth());

					} // end of outer if stmt

				} // end of outer if stmt

			} // end of inner for loop
		} // end of for outer for loop

	}// end of US 02

	// Sprint 2 //US 06 // prints divorce date is after death date
	public void printdivafterdeathdate() // US 06
	{
		printHeader("Sprint 2: US 06: Divorce date that is after the death date of a person ");

		for (Person p : tree.getPeople()) { // beginning of outer for loop
			for (Family f : p.getFams()) { // beginning of inner for loop
				if (f.getDiv() != null && p.getDeath() != null) { // beginning
																	// of outer
																	// if stmt
					if (f.getDiv().isAfter(p.getDeath())) { // beginning of
															// inner if stmt

						System.out.println(" " + p.getName()
								+ " has  divorce date: " + f.getDiv()
								+ "  and death date: " + p.getDeath());

					} // end of outer if stmt

				} // end of outer if stmt

			} // end of inner for loop
		} // end of outer for loop
	} // end of US 06
		// Sprint 3

	public void printbddateafterdeathdate() {

		printHeader("US 03");
		printHeader("Birth Date exceeding Death date");

		for (Person p : tree.getPeople())

		{
			if (p.getBirth() != null && p.getDeath() != null) {
				if (p.getBirth().isAfter(p.getDeath())) {
					System.out.println("  " + p.getName()
							+ " has  birth date: " + p.getBirth() + " and "
							+ " Death date:  " + p.getDeath());

				} // end of outer if stmt
			} // end of inner if stmt
		} // end for stmt

	} // end of US O3

	// Sprint 3 //US O7.1 // Death date less than 150 years after birth for dead
	// people
	public void printdddateandbdate() {
		printHeader("Sprint3: US O7.1");
		printHeader("Death date less than 150 years after birth for dead people");
		for (Person p : tree.getPeople()) // beginning of for stmt
		{
			if (p.getBirth() != null && p.getDeath() != null) { // outer if stmt
				if ((p.getBirth().isBefore(LocalDate.now()) && (p.getDeath()
						.isBefore(LocalDate.now())))) {
					if (((p.getDeath().isAfter(p.getBirth())))
							&& (p.getDeath().minusYears(150) != (p.getBirth()))) // checks
																					// if
																					// death
																					// date
																					// is
																					// valid
					{ // inner if stmt begins

						System.out.println(" " + p.getName()
								+ " has  birth date: " + p.getBirth()
								+ " and    " + " Death date:" + p.getDeath());

					} // end of inner if stmt

				} // end middle if

			} // end of outer if stmt

		} // end of for stmt

	} // end of US O7.1

	// Sprint 3 // US 07.2 //

	public void printchkcurdtlesthan150yrsbddate() {
		printHeader("Sprint3: US O7.2");
		printHeader("Current Date is less than 150 years after birth for all living people");
		for (Person p : tree.getPeople()) // beginning of for stmt
		{
			if (p.getBirth() != null && p.getDeath() == null) { // outer if stmt

				if ((p.getBirth().isBefore(LocalDate.now()))
						&& (LocalDate.now().minusYears(150) != (p.getBirth()))) { // inner
																					// if
																					// stmt
																					// begins

					System.out.println(" " + p.getName() + " has  birth date: "
							+ p.getBirth());

				} // end of inner if stmt

			} // end of outer if stmt

		} // end of for stmt

	} // end of US O7.2

	// US28 Order siblings by age
	// List siblings in families by age
	public void listSiblingsOrdedByAge() {
		printHeader("Ordering Siblings by Age");
		tree.getFamilies()
				.stream()
				.collect(
						Collectors.toMap(
								f -> f,
								f -> f.getChild()
										.stream()
										.sorted((p1, p2) -> p1.getBirth()
												.compareTo(p2.getBirth()))
										.collect(Collectors.toList())))
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
				.filter(p -> !p
						.getFams()
						.stream()
						.filter(f -> (p.getSex().equalsIgnoreCase("M") && f
								.getWife().getId().equals(p.getId())) ? true
								: (p.getSex().equalsIgnoreCase("F") && f
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
						Collectors.toMap(
								f -> f,
								f -> f.getMembers()
										.stream()
										.collect(
												Collectors.toMap(
														p -> p.getName().split(
																" ", 2)[0]
																.concat(p
																		.getBirth()
																		.toString()),
														v -> new ArrayList(
																Arrays.asList(v)),
														(v1, v2) -> v1
																.addAll(v2) ? v1
																: v1))
										.entrySet().stream()
										.filter(e -> e.getValue().size() > 1)
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
	 * Sprint1: US10: Marriage after 14 (Marriage should be at least 14 years
	 * after birth of both spouses)
	 */
	public void listMarriageNotAfter14() {
		printHeader("US10: Printing Marriage not at least 14 years after birth");
		tree.getFamilies()
				.stream()
				.filter(f -> AgeUtils.getTimeGap(0, f.getHusband().getBirth(),
						f.getMarr()) < 14
						|| AgeUtils.getTimeGap(0, f.getWife().getBirth(),
								f.getMarr()) < 14).forEach(System.out::println);
	}

	/**
	 * Sprint1: US15: Fewer than 15 siblings (There should be fewer than 15
	 * siblings in a family)
	 */
	public void listFamilyNotFewerThan15Siblings() {
		printHeader("US15: Printing Families have more than 15 Siblings");
		tree.getFamilies().stream().filter(f -> f.getChild().size() >= 15)
				.forEach(System.out::println);
	}

	/**
	 * US09 Birth before death of parents
	 */
	public void listBirthAfterParentsDeath() {
		printHeader("Printing the People born after death of parents");
		tree.getFamilies()
				.forEach(
						f -> f.getChild()
								.stream()
								.filter(c -> (f.getHusband().getDeath() != null && AgeUtils
										.getTimeGap(1, c.getBirth(), f
												.getHusband().getDeath()) > 9)
										|| (f.getWife().getDeath() != null && AgeUtils
												.getTimeGap(1, c.getBirth(), f
														.getWife().getDeath()) > 0))
								.forEach(System.out::println));

	}

	/**
	 * US33 PArents are death and less than 18 years old
	 */
	public void ListOrphans() {
		printHeader("Printing the orphans");
		for (Person p : tree.getPeople()) {
			if (AgeUtils.getAge(p.getBirth()) < 18) {
				for (Family f : p.getFamc()) {
					if ((f.getHusband().getDeath() != null)
							&& (f.getWife().getDeath() != null))
						System.out.println(p);
				}
			}

		}
	}

	/**
	 * Sprint2: US12: Parents not too old (Mother should be less than 60 years
	 * older than her children and father should be less than 80 years older
	 * than his children)
	 */
	public void printParentTooOld() {
		printHeader("US12: Printing Parents too old");
		tree.getFamilies()
				.stream()
				.forEach(
						f -> f.getChild()
								.stream()
								.filter(c -> AgeUtils.getTimeGap(0, f
										.getHusband().getBirth(), c.getBirth()) >= 80
										|| AgeUtils.getTimeGap(0, f.getWife()
												.getBirth(), c.getBirth()) >= 60)
								.forEach(System.out::println));
	}

	/**
	 * Sprint2: US16: All male members of a family should have the same last
	 * name
	 */
	public void printMaleLastName() {
		printHeader("US16: Printing male members don't have the right last name");
		tree.getFamilies()
				.stream()
				.forEach(
						f -> f.getChild()
								.stream()
								.filter(c -> c.getSex().equals("M")
										&& !AgeUtils.getLastName(c.getName())
												.equals(AgeUtils
														.getLastName(f
																.getHusband()
																.getName())))
								.forEach(System.out::println));
	}

	// US30 Listing living married
	public void printLivingMarried() {
		System.out.println();
		System.out.println("Printing living married");
		tree.getFamilies()
				.stream()
				.filter(f -> f.getDiv() == null)
				.map(f -> new ArrayList<Person>(Arrays.asList(f.getWife(),
						f.getHusband())))
				.flatMap(l -> l.stream())
				.filter(p -> p.getDeath() == null
						&& AgeUtils.getAge(p.getBirth()) > 0)
				.forEach(System.out::println);

	}

	// US35 listing recent births
	public void printRecentBirths() {
		printHeader("Printing Recent Births (with last 30 days)");
		tree.getFamilies()
				.stream()
				.forEach(
						f -> f.getChild()
								.stream()
								.filter(c -> AgeUtils.getAgeDays(c.getBirth()) <= 30
										&& AgeUtils.getAgeDays(c.getBirth()) > 0)
								.forEach(System.out::println));

	}

	/**
	 * Sprint3: US13: Birth dates of siblings should be more than 8 months apart
	 * or less than 2 days apart.
	 */
	public void printSiblingSpacingInvalid() {
		printHeader("US13: Printing birth dates of siblings not more than 8 months apart or less than 2 days apart.");
		for (Family f : tree.getFamilies()) {
			ArrayList a = new ArrayList();
			for (Person p : f.getChild()) {
				String s = p.getId() + " " + p.getName();

				for (int i = 0; i < f.getChild().size(); i++) {
					if (!p.getId().equals(f.getChild().get(i).getId())) {
						long day = AgeUtils.getTimeGap(2, p.getBirth(), f
								.getChild().get(i).getBirth());
						if (day * (1 - ((day >>> 31) << 1)) > 2) {
							long month = AgeUtils.getTimeGap(1, p.getBirth(), f
									.getChild().get(i).getBirth());
							if (month * (1 - ((month >>> 31) << 1)) < 8) {
								a.add(s);
							}
						} else {
							a.add(s);
						}
					}
				}
			}
			if (!a.isEmpty())
				System.out.println(f.getId() + " " + a.toString());
		}
	}

	/**
	 * Sprint3: US17: Parents should not marry any of their descendants.
	 */
	public void printMarriagesToDescendants() {
		printHeader("US17: Printing people marry any of his descendants.");
		for (Person p : tree.getPeople()) {
			List<Person> arrSpouse = new ArrayList<Person>();
			List<Person> arrChildren = new ArrayList<Person>();
			if (p.getSex().equals("M")) {
				for (Family f : tree.getFamilies()) {
					if (f.getHusband().getId().equals(p.getId())) {
						arrSpouse.add(f.getWife());
						arrChildren.addAll(f.getChild());
					}
				}
			} else if (p.getSex().equals("F")) {
				for (Family f : tree.getFamilies()) {
					if (f.getWife().getId().equals(p.getId())) {
						arrSpouse.add(f.getHusband());
						arrChildren.addAll(f.getChild());
					}
				}
			}
			for (int i = 0; i < arrSpouse.size(); i++) {
				if (arrChildren.contains(arrSpouse.get(i))) {
					System.out.println("Parent: " + p.toString() + " Child: "
							+ arrSpouse.get(i).toString());
				}
			}
		}

	}
}
