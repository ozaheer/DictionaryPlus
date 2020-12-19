// --== CS400 File Header Information ==--
// Name: Yash Ramchandani
// Email: yramchandani@wisc.edu
// Team: FC
// TA: Abhay
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * The class that instantiates the red black tree and stores all the words in the dictionary
 */
public class Dictionary {
    RedBlackTree<Word> dictionary;
    int size;

    public Dictionary() {
        dictionary = new RedBlackTree<Word>();
        size = 0;
    }

    /**
     * Method to add a word to the dictionary
     * 
     * @param word the word object to be added to the tree
     * @return true if successfully added, false if not
     */
    public boolean addWord(Word word) {
        try{
            dictionary.insert(word);
        } catch (IllegalArgumentException e1) {
            return false;
        } catch (NullPointerException e2) {
            return false;
        }
        size++;
        return true;
    }

    /**
     * Method to get the definition of a word from the dictionary
     * 
     * @param wordName the name of the word to be searched for
     * @return the definition of the searched word
     */
    public String getDefinition(String wordName) {
        if (wordName == null) return null;
        if (dictionary.root == null) return null;
        Word word = getHelper(wordName, dictionary.root);
        if (word == null) return null;
        return word.getDefinition();
    }

    /**
     * Private helper method that returns a word object to be used by getDefinition and getPart
     * 
     * @param wordName the word to be searched for
     * @param node the root node that is used to search through the tree
     * @return the word object that corresponds to the name inputted
     */
    private Word getHelper(String wordName, RedBlackTree.Node<Word> node) {
        int compare;

        if (node == null) return null;

        compare = wordName.compareTo(node.data.getWordName());
        if (compare == 0) return node.data;

        else if (compare < 0) return getHelper(wordName, node.leftChild);

        else return getHelper(wordName, node.rightChild);
    }

    /**
     * Checks if a word is already in the dictionary
     * 
     * @param wordName name of the word to be searched
     * @return true if word is in dictionary, false if not
     */
    public boolean contains(String wordName) {
        return !(getHelper(wordName, dictionary.root) == null);
    }

    /**
     * Returns the number of words stored in the dicionary
     * 
     * @return the size of the dictionary as an int
     */
    public int getSize() {return size;}

    /**
     * Method that returns the part of speech of a word
     * 
     * @param wordName the name of the word to be searched
     * @return the part of speech of the word to be searched
     */
    public PartOfSpeech getPart(String wordName) {
        if (wordName == null) return null;
        if (dictionary.root == null) return null;
        Word word = getHelper(wordName, dictionary.root);
        return word.getPartOfSpeech();
    }

    /**
     * Removes all values from the dictionary and sets its size to 0
     */
    public void clear() {
        dictionary = new RedBlackTree<Word>();
        size = 0;
    }
    
}
