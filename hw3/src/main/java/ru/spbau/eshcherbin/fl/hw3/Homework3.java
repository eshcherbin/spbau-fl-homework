package ru.spbau.eshcherbin.fl.hw3;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

import java.io.IOException;

public class Homework3 {
    public static void main(String[] args) throws IOException {
        printDotTask1();
        printDotTask2a();
        printDotTask2b();
    }
    
    private static void printDotTask1() {
        printAutomatonFromRegex("(a|b)*");
    }
    
    private static void printDotTask2a() {
        String number = "(0|-?[1-9][0-9]*)";
        String item_l = "( *" + number + " *)";
        String list = "\\[(" + item_l + ";)*" + item_l + "\\]|\\[ *\\]";

        printAutomatonFromRegex(list);
    }

    private static void printDotTask2b() {
        String number = "(0|-?[1-9][0-9]*)";
        String item_l = "( *" + number + " *)";
        String list = "\\[(" + item_l + ";)*" + item_l + "\\]|\\[ *\\]";
        String identifier = "([_a-z][_0-9a-z]*)";
        String item_t = "( *(" + number + "|" + identifier + "|" + list + ") *)";
        String tuple = "\\((" + item_t + ";)*" + item_t + "\\)|\\( *\\)";
                
        printAutomatonFromRegex(tuple);
    }
    
    private static void printAutomatonFromRegex(String regexString) {
        Automaton automaton = new RegExp(regexString).toAutomaton();
        automaton.minimize();
        System.out.println(automaton.toDot().replace("\\u0020", "\\s"));
    }
}
