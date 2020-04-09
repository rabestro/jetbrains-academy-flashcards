package flashcards;

public class Main {
    public static void main(String[] args) {
        final var app = new Application();

        app.parseArgs(args);
        app.importCards();
        app.menu();
        app.exportCards();
    }
}
