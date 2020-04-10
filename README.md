# About
For foreign language learners, it’s hard to remember new words, which is exactly what flashcards are for. Typically, flashcards show a hint (a task or a picture) on one side and the right answer on the other. Flashcards can be used to remember any sort of data, so if you want to create something useful and enhance your programming skills, this project is just right.

## Learning outcomes
You will learn to work with files and call them from the command-line.

## This project is a part of the following track
Java Developer

## Stage 5/7: Menu, please! 

### Description

Improve the application's interactivity. Ask the user for action and make it.

Support these actions:

 -   add a card: **add**,
 -   remove a card: **remove**,
 -   load cards from file: **import**,
 -   save cards to file: **export**,
 -   ask for a definition of some random cards: **ask**,
 -   exit the program: exit.

When entering the word **export**, the program should request a file name and write all currently available cards into this file.
When entering the word **import**, the program should request a file name and read all the cards written to this file.

You can use any format to save cards to the file. Tests do not check the content of the file, but they do check that all saved cards are loaded correctly.

In this stage, if you try to add a card with an existing term or an existing definition, the application must just reject it by printing an error message (see example 1).

When you load cards from a file, you shouldn't erase the cards that aren't in the file. If the imported card already exists, it should update the old one (look at cards Japan and Moscow in the example 2). It is guaranteed, that there won't be any conflicts with definitions in the tests.

## Stage 6/7: Statistics 

Add some statistics features. We suggest you implement the following:

  -  Action **log** saves the application log to the given file. Save all lines that have been input in or output to the console to the file. You can use a list to store the lines.
  -  Action **hardest card** prints the term of the card that has the most mistakes. You can store the mistake count in a map. If there are no cards with mistakes, you should print There are no cards with errors.. And for multiple hardest cards, you should list them all, like in the example below.
  -  Action **reset stats** erases the mistake counts for all cards.

Also you should update serialization/deserialization to store sets of three items (term, definition, mistakes) instead of pairs (term, definition).

## Stage 7/7: IMPORTant 

Users often use files to save their progress and restore it the next time they run the program. It's tedious to print the actions manually. Sometimes you can just forget to do it! So let's add run arguments that define which file to read at the start and which file to save at the exit.

To read an initial cards set from an external file, you should pass the argument **-import** and follow it with the file name. If the argument is present, the first line of your program output should be _10 cards have been loaded._ (hereinafter, replace 10 with the number of cards). If such argument is not set, the set of cards should be initially empty.

If the **-export** argument is set and it is followed by the file name, you should write all the cards that are in the program memory into this file after the user has entered **exit**, and the last line of your program should be _10 cards have been saved._.

### Run arguments examples
```
java Flashcards -import derivatives.txt

java Flashcards -export animals.txt

java Flashcards -import words13june.txt -export words14june.txt

java Flashcards -export vocab.txt -import vocab.txt
```
