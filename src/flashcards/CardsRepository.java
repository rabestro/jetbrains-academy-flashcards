package flashcards;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CardsRepository {
    private final Set<Card> cards;
    private final ConsoleUI ui;
    private final Random rnd;

    CardsRepository(ConsoleUI ui) {
        cards = new LinkedHashSet<>();
        rnd = new Random();
        this.ui = ui;
    }

    void cardsImport() {
        ui.println("File name:");
        final var fileName = ui.nextLine();
        cardsImport(fileName);
    }

    void cardsImport(final String fileName) {
        try (final var sc = new Scanner(new File(fileName))) {
            int count = 0;
            while (sc.hasNext()) {
                final var term = sc.nextLine();
                final var definition = sc.nextLine();
                final var mistakes = Integer.parseInt(sc.nextLine());
                cards.add(new Card(term, definition, mistakes));
                ++count;
            }
            ui.printf("%d cards have been loaded.%n", count);
        } catch (FileNotFoundException e) {
            ui.println("File not found.");
        }
    }

    void cardsExport() {
        ui.println("File name:");
        final var fileName = ui.nextLine();
        cardsExport(fileName);
    }

    void cardsExport(final String fileName) {
        try (final var out = new PrintWriter(new FileWriter(fileName))) {
            cards.forEach(card -> {
                out.println(card.getTerm());
                out.println(card.getDefinition());
                out.println(card.getMistakes());
            });
        } catch (IOException e) {
            ui.println(e.getMessage());
        }
        ui.printf("%d cards have been saved.%n", cards.size());
    }

    void remove() {
        ui.println("The card:");
        final var term = ui.nextLine();

        cards.stream()
                .filter(c -> c.getTerm().equals(term))
                .findFirst()
                .ifPresentOrElse(this::remove,
                        () -> ui.printf("Can't remove \"%s\": there is no such card.%n", term));
    }

    private void remove(Card card) {
        cards.remove(card);
        ui.println("The card has been removed.");
    }

    void add() {
        ui.println("The card:");
        final var term = ui.nextLine();

        if (cards.stream().anyMatch(card -> card.getTerm().equals(term))) {
            ui.printf("The card \"%s\" already exists.%n", term);
            return;
        }

        ui.println("The definition of the card:");
        final var definition = ui.nextLine();

        if (cards.stream().anyMatch(card -> card.getDefinition().equals(definition))) {
            ui.printf("The definition \"%s\" already exists.%n", definition);
            return;
        }

        cards.add(new Card(term, definition, 0));
        ui.printf("The pair (\"%s\":\"%s\") has been added.%n", term, definition);
    }

    void ask() {
        ui.println("How many times to ask?");
        final int n = Integer.parseInt(ui.nextLine());
        final var questions = cards.toArray(new Card[1]);
        Stream.generate(() -> questions[rnd.nextInt(cards.size())]).limit(n).forEach(this::askCard);
    }

    private void askCard(Card card) {

        ui.printf("Print the definition of \"%s\":%n", card.getTerm());
        final var answer = ui.nextLine();

        if (card.getDefinition().equals(answer)) {
            ui.println("Correct answer.");
            return;
        }

        card.increaseMistakes();

        cards.stream()
                .filter(x -> x.getDefinition().equals(answer))
                .findFirst()
                .ifPresentOrElse(
                        otherCard -> ui.printf(
                                "Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\".%n",
                                card.getDefinition(), otherCard.getTerm()),
                        () -> ui.printf("Wrong answer. The correct one is \"%s\".%n", card.getDefinition())
                );
    }

    void resetStats() {
        cards.forEach(Card::resetStats);
        ui.println("Card statistics has been reset.");
    }

    void hardestCard() {
        cards.stream()
                .mapToInt(Card::getMistakes)
                .filter(mistakes -> mistakes > 0)
                .max()
                .ifPresentOrElse(this::printHardestCards,
                        () -> ui.println("There are no cards with errors."));
    }

    private void printHardestCards(int maxMistakes) {
        ui.printf("The hardest card%s %s. You have %d error%s answering them.",
                cards.stream()
                        .filter(card -> card.getMistakes() == maxMistakes)
                        .count() == 1 ? " is" : "s are",
                cards.stream()
                        .filter(card -> card.getMistakes() == maxMistakes)
                        .map(card -> String.format("\"%s\"", card.getTerm()))
                        .collect(Collectors.joining(", ")),
                maxMistakes, maxMistakes > 1 ? "s" : "");
    }
}