package com.hangman.game;

import com.hangman.data.Database;
import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) {
		Database db = new Database(Database.DEFUALT_SERVER_INFO);
		EnglishDictionary dict = new EnglishDictionary();
		dict.openConnection();
	}

}
