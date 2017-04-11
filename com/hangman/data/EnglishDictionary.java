package com.hangman.data;

import java.sql.Connection;

/*
 * The EnglishDictionary class serves as an abstraction from the MySQL database that contains
 * words from the English dictionary. 
 * The methods provided in this class will be used in the com.hangman.game package to 
 * parse available English words so that the AI player can guess the word. This class should not contain 
 * any game-play/AI implementation, solely code that serves data from a MySQL server. 
 */
public class EnglishDictionary {
	
	private Database englishDictData = null;
	private Connection dataConnection = null;
	
	public EnglishDictionary() {
		
		englishDictData = new Database(Database.DEFUALT_SERVER_INFO);
	}
	
	public void openConnection() {
		
		try {
			dataConnection = englishDictData.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dataConnection != null) {
			System.out.println("{ " + englishDictData.getServerDetails() + " }\n");
		}
	}

}
