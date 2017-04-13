package com.hangman.game;

import java.util.ArrayList;

import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) {
		EnglishDictionary dict = new EnglishDictionary();
		ArrayList<String> words = new ArrayList<String>();
		String lookup = "l";
		int wordLength = 5;
		
		words = dict.getWordsThatContain(lookup, wordLength);
		
		System.out.println("Found " + words.size() + " words that contain " + lookup + " and have length of " + wordLength + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		lookup = "ass";
		String matching = "c_as_";
		dict.refineListForWordsMatching(words, matching);
		
		System.out.println("Refined word list, all words must match \'" + matching + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
	
	}

}
