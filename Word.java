// --== CS400 File Header Information ==--
// Name: Aidan Shine
// Email: atshine@wisc.edu
// Team: FC
// Role: Data Wrangler
// TA: Abhay
// Lecturer: Gary
// Notes to Grader: :)

public class Word implements Comparable<Word> {

  private String wordName;
  private PartOfSpeech partOfSpeech;
  private String definition;


  /**
   * Creates a new Word with the specified name, part of speech, and definition
   * 
   * @param wordName     - the word's name
   * @param partOfSpeech - the part of speech of the word
   * @param definition   - the word's definition
   */
  public Word(String wordName, PartOfSpeech partOfSpeech, String definition) {
    this.wordName = wordName;
    this.partOfSpeech = partOfSpeech;
    this.definition = definition;
  }

  /**
   * Retrieves the name of the word
   * 
   * @return - the word's name
   */
  public String getWordName() {
    return wordName;
  }

  /**
   * Retrieves the part of speech of the word
   * 
   * @return - the part of speech
   */
  public PartOfSpeech getPartOfSpeech() {
    return partOfSpeech;
  }

  /**
   * Retrieves the definition of the word
   * 
   * @return - the definition
   */
  public String getDefinition() {
    return definition;
  }


  /**
   * Compares a word's name to another word.
   * 
   * @return - the difference between the words, if return is negative, o is "bigger" if return is
   *         positive o is "smaller." If return is 0, words are the same
   */
  @Override
  public int compareTo(Word o) {
    return wordName.compareTo(o.getWordName());
  }


}
