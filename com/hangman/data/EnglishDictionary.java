package com.hangman.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The EnglishDictionary class serves as an abstraction from the text file that contains
 * words from the English dictionary. 
 * The methods provided in this class will be used in the com.hangman.game package to 
 * parse available English words so that the AI player can guess the word. This class should not contain 
 * any game-play/AI implementation, solely code that serves data from a MySQL server. 
 */
public class EnglishDictionary {
	
	private final String DATA_FILENAME = "input/words.txt";
	private File wordsFile;
	
	public EnglishDictionary() {
		
		wordsFile = new File(DATA_FILENAME);
	}
	
	
	
	/*
	 * returns an ArrayList<String> of all the words that begin with the input string @param start
	 * optional @param wordLength specifies the exact length of the words being searched for.
	 */
	public ArrayList<String> getWordsThatStartWith(String start)  {
		
		return getWordsThatStartWith(start, -1);
				
	}
	
	public ArrayList<String> getWordsThatStartWith(String start, int wordLength) {
		
		start = start.toLowerCase();
		ArrayList<String> words = new ArrayList<String>();
		try {
			FileInputStream in = new FileInputStream(new File(DATA_FILENAME));
			Scanner scan = new Scanner(in);
			while (scan.hasNext()) {
				String word = scan.nextLine();
				word = word.toLowerCase();
				if (word.startsWith(start) && (word.length() == wordLength || wordLength == -1)) {
					words.add(word);
				}
			}
			scan.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
		
	}
	
	/*
	 * returns an ArrayList<String> of all the words that end with the input string @param end
	 * optional @param wordLength specifies the exact length of the words being searched for.
	 */
	public ArrayList<String> getWordsThatEndWith(String end)  {
		
		return getWordsThatEndWith(end, -1);
				
	}
	
	public ArrayList<String> getWordsThatEndWith(String end, int wordLength) {
		
		end = end.toLowerCase();
		ArrayList<String> words = new ArrayList<String>();
		try {
			FileInputStream in = new FileInputStream(new File(DATA_FILENAME));
			Scanner scan = new Scanner(in);
			while (scan.hasNext()) {
				String word = scan.nextLine();
				word = word.toLowerCase();
				if (word.endsWith(end) && (word.length() == wordLength || wordLength == -1)) {
					words.add(word);
				}
			}
			scan.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
		
	}
	
	
	/*
	 * returns an ArrayList<String> of all the words that contain the input string @param contain
	 * optional @param wordLength specifies the exact length of the words being searched for.
	 */
	public ArrayList<String> getWordsThatContain(String contain)  {
		
		return getWordsThatContain(contain, -1);
	}
	
	public ArrayList<String> getWordsThatContain(String contain, int wordLength)  {
		
		contain = contain.toLowerCase();
		ArrayList<String> words = new ArrayList<String>();
		try {
			FileInputStream in = new FileInputStream(wordsFile);
			Scanner scan = new Scanner(in);
			while (scan.hasNext()) {
				String word = scan.nextLine();
				word = word.toLowerCase();
				if (word.contains(contain) && (word.length() == wordLength || wordLength == -1)) {
					words.add(word);
				}
			}
			scan.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return words;
	}
	
	/*
	 * modifies an input ArrayList<String>, removes all elements that don't match the structure of
	 * the input string @param match. Use '_' character for single char wild-cards, use '*' for 
	 * unique to previous character wild-cards 
	 */
	public static void refineListForWordsMatching(ArrayList<String> words, String match) {
		ArrayList<String> deleteList = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			if (word.length() == match.length()) {
				char prev = 0;
				for (int j = 0; j < match.length(); j++) {
					char key = match.charAt(j);
					if (key != '_' && key != '*') {
						if (word.charAt(j) != key) {
							deleteList.add(word);
							break;
						}
					}
					else if (key == '*') {
						if (word.charAt(j) == prev) {
							deleteList.add(word);
							break;
						}
					}
					prev = key;
				}
			}
		}
		for (String s : deleteList) {
			words.remove(s);
		}
	}
	
	public static void refineListForWordsThatDontContain(ArrayList<String> words, String contain) {
		ArrayList<String> deleteList = new ArrayList<String>();
		for (String word : words) {
			if (word.contains(contain)) {
				deleteList.add(word);
			}
		}
		for (String s : deleteList) {
			words.remove(s);
		}
	}

}
