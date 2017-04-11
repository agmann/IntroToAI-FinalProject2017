package com.hangman.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;


/*
 * This class serves as a representation of a MySQL Database located on a remote server.
 * For the purposes of this project, there exists only one database and database server that the 
 * application interfaces with, so much of the data for this class is final. 
 */
public class Database {
	
	public static final String DEFUALT_SERVER_INFO = "input/mysql-server.info.txt";
	
	private String userName = null;//"root";
	private String password = null;//"ILikeToCode";
	private String dbms = null;//"mysql";
	private String serverName = null;//"192.168.20.180";
	private String portNumber = null;//"3306";
	private String dbName = null;//"entries";
	
	public Database(String paramsFile) {
		File params = new File(paramsFile);
		FileInputStream paramsIn = null;
		try {
			paramsIn = new FileInputStream(params);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner paramsFileStream = new Scanner(paramsIn);
		while (paramsFileStream.hasNextLine()) {
			String line = paramsFileStream.nextLine();
			
			if (line.length() <= 5) {
				continue;
			}
			else if (line.startsWith("#")) {
				continue;
			}
			
			keyval kv = parsePropertyValue(line);
			String property = kv.key;
			String value = kv.val;
					
			
			if (property.equals("dbms")) {
				dbms = value;
			}
			else if (property.equals("server name")) {
				serverName = value;
			}
			else if (property.equals("port number")) {
				portNumber = value;
			}
			else if (property.equals("user name") || property.equals("username")) {
				userName = value;
			}
			else if (property.equals("password")) {
				password = value;
			}
			else if (property.equals("default")) {
				dbName = value;
			}
			else {
				System.err.println("Invalid property: " + property);
			}

		}
		paramsFileStream.close();
		
	}
	
	public String getServerDetails() {
		String details = "";
		details += "dbms: " + dbms + "\n";
		details += "server name: " + serverName + "\n";
		details += "port number: " + portNumber + "\n";
		details += "user name: " + userName + "\n";
		details += "password: " + password + "\n";
		details += "default database: " + dbName;
		return details;
	}
	
	private class keyval {
		public String key, val;
		public keyval(String key, String val) {
			this.key = key;
			this.val = val;
		}
	}
	
	private keyval parsePropertyValue(String line) {
		keyval kv = null;
		if (line.length() <= 5) {
			return null;
		}
		else if (line.startsWith("#")) {
			return null;
		}
		int delim = line.indexOf(":");
		if (delim == -1 || delim == line.length()-1) {
			return null;
		}
		String prop = unwrap(line.substring(0, delim)).toLowerCase();
		String val = unwrap(line.substring(delim+1));
		kv = new keyval(prop, val);
		return kv;
	}
	
	//Unused Helpers
	/*private String parseProperty(String line) {
		return parsePropertyValue(line).key;
	}
	
	private String parseValue(String line) {
		return parsePropertyValue(line).val;
	}*/
	
	private String unwrap(String line) {
		String value = null;
		int openQuote = line.indexOf("\"");
		int closeQuote = line.indexOf("\"", openQuote+1);
		
		if (openQuote == -1 || closeQuote == -1) {
			return null;
		}
		
		value = line.substring(openQuote+1, closeQuote);
		
		return value;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);
	    connectionProps.setProperty("ssl", "false");

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/",
	                   connectionProps);
	    } else if (this.dbms.equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + ":" +
	                   this.dbName +
	                   ";create=true",
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}
		
		

}
