package com.hangman.game;

import java.util.ArrayList;
import java.util.Scanner;

import com.hangman.data.EnglishDictionary;

public class HangmanGame {

	public static void main(String[] args) {
		EnglishDictionary dict = new EnglishDictionary();
		ArrayList<String> words = new ArrayList<String>();
		
	      Scanner scan = new Scanner(System.in);
	      String[] guessedLetters = new String[26];//array to keep track of guessed letters
	      String corrects = ""; //String that contains correctly guessed letters
	      int arraySize = 0;//keeps track of how many indexes in guessedLetters array are filled
	      String mostCommonLetter, matching = "";
	      int incorrect = 0, correct = 0;//variables to track number of correct and incorrect guesses

	      //displays the hangman setup
	      displayHangman(0);
      
	      System.out.println("Enter your word for the Hangman Game: ");
	      String selectedWord = scan.nextLine();
	      selectedWord = selectedWord.toLowerCase();
	      System.out.println();
	      int wordLength = selectedWord.length();

	      //initializes the word list with all words with same length as user's entered word
	      words = dict.getWordsThatContain("", wordLength);

	      //loops until a win or loss condition has been met
	      while(words.size() > 0)
	      {
      
		 mostCommonLetter = wordFrequency(words, wordLength, corrects);

		 System.out.println("AI guesses the letter: " + mostCommonLetter);
		 guessedLetters[arraySize++] = mostCommonLetter;

		 

		 if (selectedWord.contains(mostCommonLetter))
		 {
			 
			//append corrects
		    corrects += mostCommonLetter;
		     
			 matching = "";
			 //loop to generate string used to refine word list
			 for(int i = 0; i < wordLength; i++)
			 {
			    String letter = Character.toString(selectedWord.charAt(i));
			    if(mostCommonLetter.equals(letter))
			    {
			       //if letter at position i matches, append the letter 
			       matching = matching.concat(mostCommonLetter);
			     
			       correct++;
			    }
			    else
			    {
			       //append * symbol is letter at position i is not a match
			       matching = matching.concat("*");
			    }
			 }

			
		    //removes words in list that do not contain the correct letter at the specified position(s)
		    EnglishDictionary.refineListForWordsMatching(words, matching);

		    //condition for if all letters in the word have been guessed.
		    if(correct == wordLength)
		    {
		       for (int i = 0; i < words.size(); i++) {
			  System.out.println("The AI guesses the word: " + words.get(i));

			  if((words.get(i)).equals(selectedWord))
			  {
			    System.out.println("The AI has guessed the word successfully. The AI wins!");
			    break;
			  }
			  else
			  {
			     incorrect++;
			     displayHangman(incorrect);
			     if(incorrect == 6)
				break;
			  }             
		       }
		       break;
		    }
		 }
		 else
		 {
		    
			 
		    if(words.size() == 0)
                    {
            	    	System.out.println("There are no words left in the list.");
            		System.out.println("This means our dictionary most likely did not contain the word.");
            		break;
          	    } 
			 
		    incorrect++;
		    System.out.println("The letter " + mostCommonLetter + " is not in the word.");
		    //draw new hangman setup as incorrect variable increases
		    displayHangman(incorrect);

		    //if all guesses are used up, AI loses.
		    if(incorrect == 6)
		    {
		       System.out.println("The AI has used up all 6 guesses. The Player wins!");
		       System.out.println("There were " + words.size() + " words remaining in the end pool: ");
             	       for(String word: words){
                       	System.out.println(word);
             	       }   
		       break;
		    }
		  //removes words in list that contain the incorrect letter
		    EnglishDictionary.refineListForWordsThatDontContain(words, mostCommonLetter);
		 }
		 //break condition if somehow all letters are guessed
		 if(arraySize == 26)
		    break;
	   }
	      
	   scan.close();

	}
	
	//function to calculate which letter appears most frequently in word list and returns the letter
	public static String wordFrequency(ArrayList<String> words, int wordLength, String corrects)
	{
			int iterator, largestFrequency = 0;
	        String letter;
	        char character;
	        String mostCommon = "";
	        
	        /* Initialize histogram array to zero */
	        int[] freqs = new int[26];
	        for (int index = 0; index < freqs.length; index++) {
	        	freqs[index] = 0;
	        }
      
      		for (String word : words) {
         		for(iterator = 0; iterator < wordLength; iterator++){
         			character = word.charAt(iterator);
            		letter = Character.toString(character);
            		if (!Character.isAlphabetic(character) || corrects.contains(letter)) {
    			       	continue;
            		}
			        
			        int index = ((int)character - (int)('a'));
			        freqs[index]++;
			        if (freqs[index] > largestFrequency) {
			        	largestFrequency = freqs[index];
			        	mostCommon = Character.toString(character);
			        }
            			
         		}   
      		} 
      		return mostCommon;
   	}
   	
	//draw new hangman setup as incorrect variable increases
   	public static void displayHangman(int incorrect)
   	{
   		
   		System.out.println("");
		System.out.println("   _______");
		System.out.println("  |       |");
		System.out.println("  |       |");
			
   		if (incorrect == 0) {
   			
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			
   		}
   		else if(incorrect == 1)
      		{
			 
			 System.out.println("  |       O");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 
      		}
		else if(incorrect == 2)
		{
			
			 System.out.println("  |       O");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |");
			
		}
      		else if(incorrect == 3)
      		{
			
			 System.out.println("  |       O");
			 System.out.println("  |     --|");
			 System.out.println("  |       |");
			 System.out.println("  |");
			 
      		}
     	 	else if(incorrect == 4)
      		{
			 
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |");
			 
      		}            
	       else if(incorrect == 5)
	       {
			 
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |      / ");
			 
      		}                 
      		else if(incorrect == 6)
      		{
			 
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |      / \\");
			 
      		}   
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("------------");
   			System.out.println("");
           
      		if (incorrect != 0) System.out.println("The AI has made " + incorrect + " incorrect guess(es)");
   	}

}
