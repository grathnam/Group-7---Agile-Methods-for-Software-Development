package cs555.controller;

import cs555.model.Family;
import cs555.model.Person;
import cs555.model.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

public class GEDParser {
    private Tree tree;

    public GEDParser(Tree tree) {
        this.tree = tree;
    }

    public void readGED(String file){
        try (Stream<String> s = Files.lines(Paths.get(file))) {
            Iterator<String> iter = s.iterator();
            String[] record = nextRecord(iter);
            while (record != null) {
                if (isPerson(record)) {
                    Person p = tree.getPerson(record[1]);
                    record = parsePerson(p, iter);
                } else if (isFamily(record)) {
                    Family f = tree.getFamily(record[1]);
                    record = parseFamily(f, iter);
                } else { // skip an invalid record
                    record = nextRecord(iter);
                }
            }
        } catch (IOException e) {
            System.out.printf("Error reading file %s\n", file);
        }
    }

    private boolean isPerson(String[] record) {
        return (record.length == 3 && "INDI".equals(record[2]));
    }

    private boolean isFamily(String[] record) {
        return (record.length == 3 && "FAM".equals(record[2]));
    }

    private String[] nextRecord(Iterator<String> iter) {
        String[] ret = iter.hasNext() ? iter.next().trim().split(" ", 3) : null;
        if (ret != null && ret.length > 1 && ret[1].equals("NOTE")) //Skip note tag
            ret = iter.hasNext() ? iter.next().trim().split(" ", 3) : null;
        return ret;
    }

    private String[] parseFamily(Family f, Iterator<String> iter) {
        String[] record = nextRecord(iter);
        while (record != null && !"0".equals(record[0])) {
            if (record.length > 1)
                setFamilyInfo(f, record[1], record.length > 2 ? record[2] : null, iter);
            record = nextRecord(iter);
        }
        return record;
    }

    private String[] parsePerson(Person p, Iterator<String> iter) {
        String[] record = nextRecord(iter);
        while (record != null && !"0".equals(record[0])) {
            if (record.length > 1)
                setPersonInfo(p, record[1], record.length > 2 ? record[2] : null, iter);
            record = nextRecord(iter);
        }
        return record;
    }

    private void setFamilyInfo(Family f, String identifier, String value, Iterator<String> iter) {
        switch (identifier) {
            case "MARR":
                f.setMarr(iter.next().trim().split(" ", 3)[2]); // The next record should be a date if the data is valid.
                break;
            case "HUSB":
                f.setHusband(tree.getPerson(value));
                break;
            case "WIFE":
                f.setWife(tree.getPerson(value));
                break;
            case "CHIL":
                f.addChild(tree.getPerson(value));
                break;
            case "DIV":
                f.setDiv(iter.next().trim().split(" ", 3)[2]); // The next record should be a date if the data is valid.
                break;
        }
    }

    private void setPersonInfo(Person p, String identifier, String value, Iterator<String> iter) {
        switch (identifier) {
            case "NAME":
                p.setName(value);
                break;
            case "SEX":
                p.setSex(value);
                break;
            case "BIRT":
                p.setBirth(iter.next().trim().split(" ", 3)[2]); // The next record should be a date if the data is valid.
                break;
            case "DEAT":
                p.setDeath(iter.next().trim().split(" ", 3)[2]); // The next record should be a date if the data is valid.
                break;
            case "FAMC":
                p.addFamc(tree.getFamily(value));
                break;
            case "FAMS":
                p.addFams(tree.getFamily(value));
                break;
        }
    }

}
