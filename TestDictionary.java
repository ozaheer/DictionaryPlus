// --== CS400 File Header Information ==--
// Name: Oneeb Zaheer
// Email: ozaheer@wisc.edu
// Team: FC
// Role: Testing Engineer
// TA: Abhay
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import org.junit.Test;
import static org.junit.Assert.*;


public class TestDictionary {
    /**
   * tests the Word class
   */
    @Test
    public void testWord() {
        // Creates a word and tests all get methods of Word class
        Word testWord1 = new Word("run", PartOfSpeech.VERB, "to walk quickly");
        assertEquals("run", testWord1.getWordName());
        assertEquals(PartOfSpeech.VERB, testWord1.getPartOfSpeech());
        assertEquals("to walk quickly", testWord1.getDefinition());
    }
    /**
   * tests the getSize() method of the Dictionary class
   */
    @Test
    public void testGetSize() {
        // Creates a Dictionary and checks that the initial size is 0
        Dictionary testDictionary = new Dictionary();
        assertEquals(0, testDictionary.getSize());
        // Adds multiple Words to the dictionary and checks size each time
        testDictionary.addWord(new Word("run", PartOfSpeech.VERB, "to walk quickly"));
        assertEquals(1, testDictionary.getSize());
        testDictionary.addWord(new Word("water", PartOfSpeech.NOUN, "a liquid that flows"));
        assertEquals(2, testDictionary.getSize());
        testDictionary.addWord(new Word("fire", PartOfSpeech.NOUN, "very hot substance"));
        assertEquals(3, testDictionary.getSize());
    }
    /**
   * Tests functionality of the addWord method of the DictionaryClass
   */
    @Test
    public void testAddWord() { 
        // creates a Dictionary and tests that it's size is 0
        Dictionary testDictionary = new Dictionary();
        assertEquals(0, testDictionary.getSize());
        // Adds a word, tests that Dictionary size is 1
        Word testWord1 = new Word("run", PartOfSpeech.VERB, "to walk quickly");
        testDictionary.addWord(testWord1);
        assertEquals(1, testDictionary.getSize());
        // Tries to add a duplicate word and tests that Dictionary size is still 1
        Word testWord2 = new Word("run", PartOfSpeech.VERB, "to walk quickly");
        testDictionary.addWord(testWord2);
        assertEquals(1, testDictionary.getSize());
    }
     /**
   * Tests the contains method of the Dictionary class
   */
    @Test
    public void testContainsWord() {
        //creates a Dictionary and adds Words into it
        Dictionary testDictionary = new Dictionary();
        testDictionary.addWord(new Word("run", PartOfSpeech.VERB, "to walk quickly"));
        testDictionary.addWord(new Word("water", PartOfSpeech.NOUN, "a liquid that flows"));
        // tests if Dictionary.contains functions properly
        assertEquals(true, testDictionary.contains("run"));
        assertEquals(true, testDictionary.contains("water"));
        assertEquals(false, testDictionary.contains("ice"));
    }
     /**
   * Tests the clear method of the Dictionary class
   */
    @Test
    public void testClear() {
        // creates a Dictionary and adds multiple Words to it
        Dictionary testDictionary = new Dictionary();
        testDictionary.addWord(new Word("run", PartOfSpeech.VERB, "to walk quickly"));
        testDictionary.addWord(new Word("water", PartOfSpeech.NOUN, "a liquid that flows"));
        testDictionary.addWord(new Word("fire", PartOfSpeech.NOUN, "very hot substance"));
        // Checks size, then clears the dictionary
        assertEquals(3, testDictionary.getSize());
        testDictionary.clear();
        // Checks size again, and tries to access Words that have been removed
        assertEquals(0, testDictionary.getSize());
        assertEquals(false, testDictionary.contains("water"));
        assertEquals(false, testDictionary.contains("fire"));
    }
     /**
   * Tests funuctionality of the getPart() method of the Dictionary class
   */
    @Test
    public void testGetPart() {
        // creates a Dictionary and adds a few Words to it
        Dictionary testDictionary = new Dictionary();
        testDictionary.addWord(new Word("run", PartOfSpeech.VERB, "to walk quickly"));
        testDictionary.addWord(new Word("water", PartOfSpeech.NOUN, "a liquid that flows"));
        // tests functionality of Dictionary.getPart on each of these Words
        assertEquals(PartOfSpeech.VERB, testDictionary.getPart("run"));
        assertEquals(PartOfSpeech.NOUN ,testDictionary.getPart("water"));
    }
     /**
   * Tests functionality of the getDefinition() method of the Dictionary class
   */
    @Test
    public void testGetDefinition() {
        // creates a Dictionary and adds a few Words to it
        Dictionary testDictionary = new Dictionary();
        testDictionary.addWord(new Word("run", PartOfSpeech.VERB, "to walk quickly"));
        testDictionary.addWord(new Word("water", PartOfSpeech.NOUN, "a liquid that flows"));
        // tests functionality of Dictionary.getDefintion on each of these Words
        assertEquals("to walk quickly", testDictionary.getDefinition("run"));
        assertEquals("a liquid that flows", testDictionary.getDefinition("water"));
    }
     
}
