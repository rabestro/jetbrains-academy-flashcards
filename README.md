# About
For foreign language learners, it’s hard to remember new words, which is exactly what flashcards are for. Typically, flashcards show a hint (a task or a picture) on one side and the right answer on the other. Flashcards can be used to remember any sort of data, so if you want to create something useful and enhance your programming skills, this project is just right.

## Learning outcomes
You will learn to work with files and call them from the command-line.

## This project is a part of the following track
Java Developer

## Work on project. Stage 5/7: Menu, please! 

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
