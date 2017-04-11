package com.hangman.game;

import java.util.ArrayList;

import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) throws Exception {
		EnglishDictionary dict = new EnglishDictionary();
		dict.openConnection();
		ArrayList<String> words = new ArrayList<>();
		String lookup = "b";
		int wordLength = 2;
		
		words = dict.getWordsThatStartWith(lookup, wordLength);
		
		System.out.println("Found " + words.size() + " words that start with " + lookup + " and have length of " + wordLength + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		lookup = "ass";
		words = dict.getWordsThatContain(lookup);
		
		System.out.println("Found " + words.size() + " words that contain " + lookup + " and have length of " + "any" + ":");
		for (String word : words) {
			System.out.println(word);
		}
	}

}
