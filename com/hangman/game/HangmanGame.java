package com.hangman.game;

import java.util.ArrayList;

import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) {
		EnglishDictionary dict = new EnglishDictionary();
		ArrayList<String> words = new ArrayList<String>();
		String lookup = "c";
		int wordLength = 5;
		
		words = dict.getWordsThatStartWith(lookup, wordLength);
		
		System.out.println("Found " + words.size() + " words that start with " + lookup + " and have length of " + wordLength + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		
		String matching = "c_as_";
		EnglishDictionary.refineListForWordsMatching(words, matching);
		System.out.println("Matching phrase is " + matching + 
				", hence the refined list should contain words with at least one c, a, and s, in the appropriate positions");
		
		System.out.println("Refined word list, all words must match \'" + matching + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		System.out.println("Notice how some of the words have more than one c, a, or s. "
				+ "This is not possible in hangman, so we need a way to specify UNIQUE wildcards. Use the '*' character instead.");
		
		
		matching = "c*as*";
		EnglishDictionary.refineListForWordsMatching(words, matching);
		System.out.println("Matching phrase is " + matching + 
				", hence the refined list should contain words with ONLY one c, a, and s, in the appropriate positions");
		
		System.out.println("Refined word list, all words must match \'" + matching + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		lookup = "t";
		EnglishDictionary.refineListForWordsThatDontContain(words, lookup);
		System.out.println(lookup + " was a wrong guess, so parse out all those words that contain " + lookup); 
		System.out.println("Refined word list, all words must NOT contain \'" + lookup + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
	
	}

}
