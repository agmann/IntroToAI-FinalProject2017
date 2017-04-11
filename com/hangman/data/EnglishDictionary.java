package com.hangman.data;
import java.sql.*;

import java.util.ArrayList;

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
	
	/*
	 * Open the connection to the mySQL database
	 */
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
	
	/*
	 * returns an ArrayList<String> of all the words that begin with the input string @param start
	 * optional @param wordLength specifies the exact length of the words being searched for.
	 */
	public ArrayList<String> getWordsThatStartWith(String start) throws Exception {
		if (dataConnection == null) {
			throw new Exception("Connection was not opened. Use openConnection() before making this call.");
		}
		
		start = start.toLowerCase();
		ArrayList<String> words = new ArrayList<String>();
		String query = "SELECT word FROM " + englishDictData.dbName + ".entries" + " WHERE word LIKE \'" + start + "%" +"\'";
		
		words = runQueryToStringList(query);
		return words;
				
	}
	
	public ArrayList<String> getWordsThatStartWith(String start, int wordLength) throws Exception {
		ArrayList<String> words = new ArrayList<String>();
		if (dataConnection == null) {
			throw new Exception("Connection was not opened. Use openConnection() before making this call.");
		}
		if (start.length() > wordLength) {
			return words;
		}
		start = start.toLowerCase();
		String search = start;
		for (int i = 0; i < wordLength-start.length(); i++) {
			search += "_";
		}
		String query = "SELECT word FROM " + englishDictData.dbName + ".entries" + " WHERE word LIKE \'" + search +"\'";

		words = runQueryToStringList(query);
		return words;
		
		
	}
	
	public ArrayList<String> getWordsThatContain(String contain) throws Exception {
		if (dataConnection == null) {
			throw new Exception("Connection was not opened. Use openConnection() before making this call.");
		}
		
		contain = contain.toLowerCase();
		ArrayList<String> words = new ArrayList<String>();
		String query = "SELECT word FROM " + englishDictData.dbName + ".entries" + " WHERE word LIKE \'%" + contain + "%" +"\'";
		
		words = runQueryToStringList(query);
		return words;
	}
	
	private ArrayList<String> runQueryToStringList(String query) {
		ArrayList<String> words = new ArrayList<String>();
		Statement st;
		ResultSet rs;
		try {
			st = dataConnection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				String value = rs.getString("word");
				if (!words.contains(value)) {
					words.add(value);
				}
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}

}
