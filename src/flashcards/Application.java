package flashcards;

import static java.util.Objects.nonNull;

public class Application {
    private static final String ACTION_ADD = "add";
    private static final String ACTION_REMOVE = "remove";
    private static final String ACTION_IMPORT = "import";
    private static final String ACTION_EXPORT = "export";
    private static final String ACTION_ASK = "ask";
    private static final String ACTION_LOG = "log";
    private static final String ACTION_HARDEST_CARD = "hardest card";
    private static final String ACTION_RESET_STATS = "reset stats";
    private static final String ACTION_EXIT = "exit";

    private static final String CLI_IMPORT = "-import";
    private static final String CLI_EXPORT = "-export";

    private final ConsoleUI ui;
    private final CardsRepository repository;
    private String importFileName;
    private String exportFileName;

    Application() {
        ui = new ConsoleUI();
        repository = new CardsRepository(ui);
    }

    void menu() {
        do {
            ui.println("Input the action (add, remove, import, export, ask, exit):");
            final var action = ui.nextLine().toLowerCase();

            switch (action) {
                case ACTION_ADD:
                    repository.add();
                    break;
                case ACTION_REMOVE:
                    repository.remove();
                    break;
                case ACTION_IMPORT:
                    repository.cardsImport();
                    break;
                case ACTION_EXPORT:
                    repository.cardsExport();
                    break;
                case ACTION_ASK:
                    repository.ask();
                    break;
                case ACTION_LOG:
                    ui.saveLog();
                    break;
                case ACTION_HARDEST_CARD:
                    repository.hardestCard();
                    break;
                case ACTION_RESET_STATS:
                    repository.resetStats();
                    break;
                case ACTION_EXIT:
                    ui.println("Bye bye!");
                    return;
                default:
                    ui.println("The action is not recognized.");
            }
        } while (true);
    }

    void parseArgs(String... args) {
        for (int i = 0; i < args.length; i += 2) {
            if (CLI_IMPORT.equals(args[i])) {
                importFileName = args[i + 1];
            }
            if (CLI_EXPORT.equals(args[i])) {
                exportFileName = args[i + 1];
            }
        }
    }

    void importCards() {
        if (nonNull(importFileName)) {
            repository.cardsImport(importFileName);
        }
    }

    void exportCards() {
        if (nonNull(exportFileName)) {
            repository.cardsExport(exportFileName);
        }
    }
}
