package flashcards;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ConsoleUI {
    private final Scanner sc = new Scanner(System.in);
    private final List<String> log = new ArrayList<>();

    void printf(String format, Object... args) {
        final var line = String.format(format, args);
        println(line);
    }

    void println(String line) {
        System.out.println(line);
        log.add(line);
    }

    String nextLine() {
        final var line = sc.nextLine();
        log.add(line);
        return line;
    }

    void saveLog() {
        println("File name:");
        try (final var out = new PrintWriter(new FileWriter(nextLine()))) {
            log.forEach(out::println);
        } catch (IOException e) {
            println(e.getMessage());
        }
        println("The log has been saved.");
    }
}