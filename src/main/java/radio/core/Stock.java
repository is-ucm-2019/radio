package radio.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Stock {
    private TreeSet<Program> programs;

    Stock() {
        this.programs = new TreeSet<>(Comparator.comparing(Program::getId));
    }

    void addProgram(Program p) {
        this.programs.add(p);
    }

    boolean programExists(Program p) {
        return this.programs.contains(p);
    }

    ArrayList<Program> programAsList() {
        return new ArrayList<>(this.programs);
    }
}
