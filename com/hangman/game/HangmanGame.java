package com.hangman.game;

import java.util.ArrayList;

import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) {
		EnglishDictionary dict = new EnglishDictionary();
		ArrayList<String> words = new ArrayList<String>();
		String lookup = "clas";
		int wordLength = 5;
		
		words = dict.getWordsThatStartWith(lookup, wordLength);
		
		System.out.println("Found " + words.size() + " words that start with " + lookup + " and have length of " + wordLength + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		lookup = "ass";
		dict.refineListForWordsMatching(words, "____s");
		
		System.out.println("Refined word list, new size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
	
	}

}
