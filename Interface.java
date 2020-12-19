// --== CS400 File Header Information ==--
// Name: Amara Karri
// Email: nkarri@wisc.edu
// Team: FC
// Role: Front-end Developer
// TA: Abhay
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that the user interacts with to add, search and populate words in a dictionary
 */
public class Interface {
  Dictionary dictionary;
  final String invalid = "Invalid input";

  /**
   * Initializes the Dictionary object
   * 
   * @param dictionary the dictionary that the interface gets its information from
   */
  public Interface(Dictionary dictionary) {
    this.dictionary = dictionary;
  }

  /**
   * Calls the populate dictionary method
   */
  public void promptPopulate() {
    try {
      populateDictionary(dictionary);
    } catch (FileNotFoundException e) {
      System.out.println("File was not found");
    }
  }

  /**
   * Populates the dictionary with values from a data file
   * 
   * @param dictionary the dictionary to put the values into
   * @throws FileNotFoundException if name of file not found
   */
  public static void populateDictionary(Dictionary dictionary) throws FileNotFoundException {
    Word[] words = DictionaryLoader.getWords();

    for (int i = 0; i < words.length; i++) {
      dictionary.addWord(words[i]);
    }
    System.out.println("Dictionary populated");
  }

  /**
   * Called if the user wants to add a word to the dictionary and a adds it; includes error handling
   */
  public void promptAdd() {
    Scanner scan = new Scanner(System.in);
    String wordName = null;
    PartOfSpeech partOfSpeech = null;
    String definition = null;

    // checks that user does not leave word name blank
    boolean isValid = false;
    while (!isValid) {
      try {
        System.out.println("Enter the Word to add");
        wordName = scan.nextLine();
        if (!wordName.isEmpty() && wordName != null) {
          isValid = true;
        }
      } catch (Exception e) {
        System.out.println(invalid);
        continue;
      }
    }

    // checks that user doesn't leave par of speech blank
    isValid = false;
    while (!isValid) {
      try {
        System.out.println("Enter Part Of Speech for word: ");
        partOfSpeech = stringToPart(scan.nextLine().toUpperCase());
        for (PartOfSpeech part : PartOfSpeech.values())
          if (part.equals(partOfSpeech)) {
            isValid = true;
          }
      } catch (Exception e) {
        System.out.println(invalid);
      }
    }

    isValid = false;
    // checks that user doesn't leave definition blank
    while (!isValid) {
      try {
        System.out.println("Enter Definition for word: ");
        definition = scan.nextLine();
        if (!definition.isEmpty() && definition != null) {
          isValid = true;
        }
      } catch (Exception e) {
        System.out.println(invalid);
      }
    }

    // checks if word can be added to dictionary
    if (dictionary.addWord(new Word(wordName.toLowerCase(), partOfSpeech, definition.toLowerCase()))) {
      System.out.println("Word successfully added");
    } else {
      System.out.println("Word add was unsuccessful, it is already in the dictionary");
    }
  }


  /**
   * Called if user wants to check if word exist in the dictionary
   */
  public void promptContains() {
    Scanner scan = new Scanner(System.in);
    boolean isValid = false;
    String wordName = null;
    // checks that user does not leave word blank
    while (!isValid) {
      try {
        System.out.println("Enter the word name to check");
        wordName = scan.nextLine().toLowerCase();
        if (!wordName.isEmpty() && wordName != null) {
          isValid = true;
        }
      } catch (Exception e) {
        System.out.println(invalid);
      }
    }

    // checks if the work is in the dictionary
    isValid = false;
    try {
      if (dictionary.contains(wordName) == true) {
        System.out.println("Word is in the dictionary");
      } else {
        System.out.println("Word is not in the dictionary");
      }

    } catch (NoSuchElementException e1) {
      System.out.println("Contains Exception");
    }
  }

  private static PartOfSpeech stringToPart(String s) {
    for (PartOfSpeech part : PartOfSpeech.values())
      if (part.toString().equals(s.toUpperCase()))
        return part;
    return null;
  }

  /**
   * Called if a user wants to look up a Word from the dictionary and includes error handling
   */
  public void promptSearch() {
    Scanner scan = new Scanner(System.in);
    String wordName = null;
    Boolean isValid = false;
    Word word;

    // loop to repeat until user enters valid word
    while (!isValid) {
      try {
        System.out.println("Enter the Word Name search: ");
        wordName = scan.nextLine().toLowerCase();
        if (!wordName.isEmpty() && wordName != null) {
          isValid = true;
        }

        System.out.println("Word Name: " + wordName);
        System.out.println("Part of Speech: " + dictionary.getPart(wordName));
        System.out.println("Definition: " + dictionary.getDefinition(wordName));
        // exceptions caught if the user enters invalid input or word not in
        // dictionary
      } catch (NoSuchElementException e) {
        System.out.println("The word entered is not in the dictionary");
      } catch (Exception e) {
        System.out.println(invalid);
      }
    }
  }

  /**
   * Called if a user wants to get the size of the dictionary Prints out the size
   */
  public void promptSize() {
    System.out.println("The current size of the dictionary: " + dictionary.getSize());
  }

  /**
   * Called if user wants to clear the dictionary
   */
  public void promptClear() {
    dictionary.clear();
    System.out.println("Dictionary is cleared: " + dictionary.getSize());
  }

  /**
   * Main method that interacts with the user Asks to enter a command and calls the correct method
   */
  public void userPrompt() {
    Scanner scan = new Scanner(System.in);
    boolean isValid = false;
    boolean repeat = true;

    // repeats until the user says they don't want to enter another command
    while (repeat) {
      try {
        // prints out all the possible commands
        System.out.println("Enter a letter for command");
        System.out.println(
            "Commands:  p - Populate Dictionary | a - Add Word | c - Contains Word? | s - Search Word | z - Get Size | r - clear");

        // calls the correct print statement based on the command
        String userInput = scan.nextLine();
        if (userInput.trim().equalsIgnoreCase("a")) {
          promptAdd();
        } else if (userInput.trim().equalsIgnoreCase("c")) {
          promptContains();
        } else if (userInput.trim().equalsIgnoreCase("s")) {
          promptSearch();
        } else if (userInput.trim().equalsIgnoreCase("z")) {
          promptSize();
        } else if (userInput.trim().equalsIgnoreCase("r")) {
          promptClear();
        } else if (userInput.trim().equalsIgnoreCase("p")) {
          promptPopulate();
        } else {
          // throw an exception if the user entered a value that is
          // not in the list
          throw new InputMismatchException();
        }

        String repeatInput;
        isValid = false;
        // checks if user wants to enter another command
        while (!isValid) {
          System.out.println("Would you like to enter another command? (y/n)");
          repeatInput = scan.nextLine();
          if (repeatInput.trim().equalsIgnoreCase("y")) {
            repeat = true;
            isValid = true;
          } else if (repeatInput.trim().equalsIgnoreCase("n")) {
            System.out.println("Thank you, Bye!");
            scan.close();
            repeat = false;
            isValid = true;
          } else {
            System.out.println("Please enter a valid command");
          }
        }
      } catch (InputMismatchException e) {
        System.out.println("Please enter a valid command");
      }
    }
    scan.close();
  }

  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    Interface frontEnd = new Interface(dictionary);
    frontEnd.userPrompt();
  }
}



