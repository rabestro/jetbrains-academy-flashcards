package flashcards;

class Card {
    private final String term;
    private final String definition;
    private int mistakes;

    Card(final String term, final String definition, final int mistakes) {
        this.term = term;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    void resetStats() {
        mistakes = 0;
    }

    void increaseMistakes() {
        ++mistakes;
    }

    String getTerm() {
        return term;
    }

    String getDefinition() {
        return definition;
    }

    int getMistakes() {
        return mistakes;
    }
}
