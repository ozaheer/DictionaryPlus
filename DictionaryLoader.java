import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// --== CS400 File Header Information ==--
// Name: Aidan Shine
// Email: atshine@wisc.edu
// Team: FC
// Role: Data Wrangler
// TA: Abhay
// Lecturer: Gary
// Notes to Grader: :)

public class DictionaryLoader {

  public DictionaryLoader() {
    // TODO Auto-generated constructor stub
  }

  public static Word[] getWords() throws FileNotFoundException {
    File dataFile = new File("Words.txt");
    Scanner sc = new Scanner(dataFile);
    int numWords = Integer.parseInt(sc.nextLine()); // first line has number of words
    Word[] words = new Word[numWords];
    int count = 0;
    while (sc.hasNextLine()) {
      String bookInfo = sc.nextLine();
      String word = bookInfo.substring(0, bookInfo.indexOf(","));
      word = word.trim();
      word = word.toLowerCase();
      bookInfo = bookInfo.substring(bookInfo.indexOf(",") + 1);
      String partString = bookInfo.substring(0, bookInfo.indexOf(","));
      partString = partString.trim();
      partString = partString.toLowerCase();
      PartOfSpeech part;
      if (partString.equals("noun")) {
        part = PartOfSpeech.NOUN;
      } else if (partString.equals("verb")) {
        part = PartOfSpeech.VERB;
      } else if (partString.equals("adjective")) {
        part = PartOfSpeech.ADJECTIVE;
      } else if (partString.equals("preposition")) {
        part = PartOfSpeech.PREPOSITION;
      } else if (partString.equals("adverb")) {
        part = PartOfSpeech.ADVERB;
      } else if (partString.equals("pronoun")) {
        part = PartOfSpeech.PRONOUN;
      } else {
        part = PartOfSpeech.CONJUNCTION;
      }
      String def = bookInfo.substring(bookInfo.indexOf(",") + 1);
      def = def.trim();
      Word newWord = new Word(word, part, def);
      words[count] = newWord;
      count++;
    }
    return words;
  }

}
