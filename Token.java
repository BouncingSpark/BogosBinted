package application;

/**
 * This class represents a word and maintains a count of how many times that particular word has occurred. 
 * It will implement the Comparable interface and override the equals method.
 * 
 * @author sarah
 */

/**
 * This class represents a word and maintains a count of how many times that particular word has occurred. 
 * Implements the Comparable interface for sorting and comparison based on the count.
 */
public class Token implements Comparable<Token> {
	
	// Container for string being processed.
	private String word;	
	// Count for particular word.
    private int count;	

    /**
     * Constructor for the Token class.
     * Initializes a new Token with the specified word and a count of 0.
     * 
     * @param input The word that this Token represents.
     */
    public Token(String input) {
        this.word = input;
        this.count = 0; 
        
    }
    
    
    /**
     * This method determines whether this Token object is equal to another object.
     * Equality between two Token objects is based solely on the word they represent.
     * This method overrides the default 'equals' method to provide this specific comparison logic.
     * 
     * Steps:
     * 1. Check if the other object is the same as this instance (memory address comparison).
     * 2. Check if the other object is null.
     * 3. If the other object is not null and is an instance of Token, compare the words.
     * 
     * @param other The object to compare with this Token.
     * @return true if the other object is a Token with the same word, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        // Check if the other object is the same instance as this one.
        // If yes, they are definitely equal.
        if (other == this)
            return true;
        // Check if the other object is null.
        // If yes, it cannot be equal to this non-null Token instance.
        if (other == null)
            return false;
        // Check if the other object is an instance of the Token class.
        // This prevents a ClassCastException when casting 'other' to a Token.
        if (!(other instanceof Token))
            return false;
        // Cast the other object to a Token and compare the words.
        // If the words are the same, then the two Token instances are considered equal.
        Token token = (Token) other;
        return this.getWord().equals(token.getWord());
    }
    
    /**
     * This method increments the count of this Token by 1.
     */
	public void incrementCount() {
        count++;
		
	}
	
	
    /**
     * This accessor (getter) method gets the word of this Token.
     * 
     * @return The word this Token represents.
     */
    public String getWord() {
        return this.word;
    }

    
    /**
     * This mutator (setter) method sets the word of this Token.
     * 
     * @param input The new word for this Token.
     */
    public void setWord(String input) {
        this.word = input;
    }

    
    /**
     * This accessor (getter) method gets the count of how many times the word has occurred.
     * 
     * @return The count of this Token.
     */
    public int getCount() {
        return this.count;
    }

    
    /**
     * This mutator (setter) method sets the count for this Token.
     * 
     * @param input The new count for this Token.
     */
    public void setCount(int input) {
        this.count = input;
    }

    
    /**
     * This method compares this Token with another Token based on the count.
     * It is used for sorting Tokens in a collection.
     * 
     * @param otherToken The other Token to compare against.
     * @return A negative integer, zero, or a positive integer as this Token's count is less than, equal to, or greater than the specified Token's count.
     */
    @Override
    public int compareTo(Token other) {
        // If two words have the same number of occurrences, we compare the words by their alphabetical order.
        if (this.count == other.count) {
            return this.getWord().compareTo(other.getWord());
        }
        return this.getCount() - other.getCount();
    }
    
    /**
     * This method provides a string representation of this Token, combining word and count.
     * 
     * @return A string in the format "word : count".
     */
    @Override
    public String toString() {
        return this.word + " : " + this.count;
    }

}
