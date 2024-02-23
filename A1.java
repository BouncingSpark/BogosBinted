package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class A1 {

    // An ArrayList to store words and their counts.
    private final ArrayList<Token> wordList = new ArrayList<Token>();

    // An Array of 'stop words' to be excluded from the count.
    private final String[] stopWords = {"a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been", "but", "by",
            "can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had", "has", "have",
            "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like", "more", "me", "my",
            "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says", "she", "so", "some", "than", "that",
            "the", "their", "them", "then", "there", "these", "they", "this", "to", "too", "us", "upon", "was", "we", "were",
            "what", "with", "when", "where", "which", "while", "who", "whom", "why", "will", "you", "your"};

    // Total words as an integer.
    private int totalWordCount = 0;
    // Total 'stop words' as an integer.
    private int stopWordCount = 0;
    // Scanner object called 'input'.
    private final Scanner input = new Scanner(System.in);

    /**
     * The Main method to run the application.
     * Initializes an instance of A1 and calls the run method.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.run();
    }

    /**
     * This method executes the program.
     * It calls methods 'readFile()' and 'printResults()'.
     */
    public void run() {
        readFile();
        printOutput();
    }
    
    /**
     * This method reads and processes input from the user.
     * Processes each word by normalizing it and then checking against stop words and counting unique occurrences.
     */
    private void readFile() {
        while (input.hasNext()) {
            String word = input.next().toLowerCase().replaceAll("[^a-z]", "").trim();

            if (word.isEmpty())
                continue;

            totalWordCount++; // Increment the total word count.
            if (isStopWord(word)) {
                stopWordCount++;
                continue;
            }

            Token newToken = new Token(word);
            int index = wordList.indexOf(newToken);

            if (index != -1) {
                // Word already exists in the list.
                Token existingToken = wordList.get(index);
                existingToken.incrementCount();
                }
            else {
                // New word, add to wordList and consider it as unique.
                newToken.incrementCount(); // Sets the count to 1.
                wordList.add(newToken);
            }
        }
        input.close();
    }
    
    /**
     * The Comparator Method to sort word Tokens by count in descending order.
     * In case of a tie, sort them alphabetically in ascending order.
     */
    private static class wordCountAscending implements Comparator<Token> {
        @Override
        public int compare(Token o1, Token o2) {
            if (o1.getCount() == o2.getCount())
                return o1.getWord().compareTo(o2.getWord());
            return o1.getCount() - o2.getCount();
        }
    }

    /**
     * The Comparator Method to sort word Tokens by count in ascending order.
     * In case of a tie, sort them alphabetically in ascending order.
     */
    private static class wordCountDescending implements Comparator<Token> {
        @Override
        public int compare(Token o1, Token o2) {
            if (o1.getCount() == o2.getCount())
                return o1.getWord().compareTo(o2.getWord());
            return o2.getCount() - o1.getCount();
        }
    }
    
    /**
     * This method checks if a given word is a 'stop word'
     * .
     *
     * @param word The word to check.
     * @return true if it's a 'stop word', false otherwise.
     */
    public boolean isStopWord(String word) {
        for (String stopword : stopWords) {
            if (stopword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method prints the output of the word processing.
     * It includes total word count, unique words, stop word count, 
     * and lists of most and least frequent words.
     */
    public void printOutput() {
        // Print the total count of words processed.
        System.out.println("Total Words: " + totalWordCount);
        // The unique words count is missing in implementation, only the label is printed.
        System.out.println(" ");
        System.out.println("Unique Words: " + wordList.size());
        // Print the count of stop words found in the input.
        System.out.println(" ");
        System.out.println("Stop Words: " + stopWordCount);
        /**
         * Print the 10 most frequent words.
         * Sort the wordList by word frequency in descending order.
         * Print the top 10 words or fewer if there are fewer words in the list.
         */
        System.out.println(" ");
        System.out.println("10 Most Frequent: ");
        Collections.sort(wordList, new wordCountDescending());
        for (int i = 0; i < Math.min(wordList.size(), 10); i++) {
            System.out.println(wordList.get(i).toString());
        }
        /**
         * Print the 10 least frequent words.
         * Sort the wordList by word frequency in ascending order.
         * Print the bottom 10 words or fewer if there are fewer words in the list.
         */
        System.out.println(" ");
        System.out.println("10 Least Frequent:");
        Collections.sort(wordList, new wordCountAscending());
        for (int i = 0; i < Math.min(wordList.size(), 10); i++) {
            System.out.println(wordList.get(i).toString());
        }
        /**
         * Print all words in alphabetical order.
         * Sort the wordList alphabetically.
         * Print each word in the sorted list.
         */
        System.out.println(" ");
        System.out.println("All Words(alphabetically): ");
        Collections.sort(wordList, Comparator.comparing(Token::getWord));
        for (Token word : wordList) {
            System.out.println(word.toString());
        }
    }
}