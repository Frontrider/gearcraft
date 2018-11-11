package hu.frontrider.gearcraft.analyticengine;

import java.util.regex.Matcher;

public interface Instruction {
    void execute(Matcher matcher);
}