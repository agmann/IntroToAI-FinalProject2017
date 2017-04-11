package com.hangman.game;

import com.hangman.data.Database;

public class HangmanGame {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		Database db = new Database(Database.DEFUALT_SERVER_INFO);
		System.out.println(db.getServerDetails());
	}

}
