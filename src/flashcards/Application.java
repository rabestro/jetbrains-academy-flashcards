package flashcards;

import static java.util.Objects.isNull;

public class Application {
    private final ConsoleUI ui;
    private final CardsRepository repository;
    private String importFileName;
    private String exportFileName;

    Application() {
        ui = new ConsoleUI();
        repository = new CardsRepository(ui);
    }

    void menu() {
        String action;

        do {
            ui.println("Input the action (add, remove, import, export, ask, exit):");
            action = ui.nextLine();

            switch (action) {
                case "add":
                    repository.add();
                    break;
                case "remove":
                    repository.remove();
                    break;
                case "import":
                    repository.cardsImport();
                    break;
                case "export":
                    repository.cardsExport();
                    break;
                case "ask":
                    repository.ask();
                    break;
                case "log":
                    ui.saveLog();
                    break;
                case "hardest card":
                    repository.hardestCard();
                    break;
                case "reset stats":
                    repository.resetStats();
                    break;
                case "exit":
                    ui.println("Bye bye!");
            }
        } while (!"exit".equals(action));
    }

    void parseArgs(String... args) {
        for (int i = 0; i < args.length; i += 2) {
            if ("-import".equals(args[i])) {
                importFileName = args[i + 1];
            }
            if ("-export".equals(args[i])) {
                exportFileName = args[i + 1];
            }
        }
    }

    void importCards() {
        if (isNull(importFileName)) return;
        repository.cardsImport(importFileName);
    }

    void exportCards() {
        if (isNull(exportFileName)) return;
        repository.cardsExport(exportFileName);
    }

}
