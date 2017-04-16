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
	      int arraySize = 0;//keeps track of how many indexes in guessedLetters array are filled
	      String mostCommonLetter, matching = "";
	      int matchFlag;//flag variable to indicate if letter is in word
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
		 matchFlag = 0;
      
		 mostCommonLetter = wordFrequency(words, wordLength, guessedLetters, arraySize);

		 System.out.println("AI guesses the letter: " + mostCommonLetter);
		 guessedLetters[arraySize++] = mostCommonLetter;

		 matching = "";
		 //loop to generate string used to refine word list
		 for(int i = 0; i < wordLength; i++)
		 {
		    String letter = Character.toString(selectedWord.charAt(i));
		    if(mostCommonLetter.equals(letter))
		    {
		       //if letter at position i matches, append the letter 
		       matching = matching.concat(mostCommonLetter);
		       matchFlag = 1;
		       correct++;
		    }
		    else
		    {
		       //append * symbol is letter at position i is not a match
		       matching = matching.concat("*");
		    }
		 }


		 if(matchFlag == 1)
		 {
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
		 else if(matchFlag == 0)
		 {
		    //removes words in list that contain the incorrect letter
		    EnglishDictionary.refineListForWordsThatDontContain(words, mostCommonLetter);
			 
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
		 }
		 //break condition if somehow all letters are guessed
		 if(arraySize == 26)
		    break;
	      }

	}
	
	//function to calculate which letter appears most frequently in word list and returns the letter
	public static String wordFrequency(ArrayList<String> words, int wordLength, String[] guessedArray, int arraySize)
	{
		int iterator, iterator2, largest = 0, repeatFlag = 0;
	        String letter;
	        char character;
	        String mostCommon = "";
	        
	        int[] freqs = new int[26];
	        for (int index = 0; index < freqs.length; index++) {
	        	freqs[index] = 0;
	        }
      
      		for (String word : words) {
         		for(iterator = 0; iterator < wordLength; iterator++){
         				character = word.charAt(iterator);
            			letter = Character.toString(character);
            			if (!Character.isAlphabetic(character)) {
    			        	continue;
    			        }
            			repeatFlag = 0;
            			//this for loop checks causes the letter to not be counted if it's been guessed already
			        for(iterator2 = 0; iterator2 < arraySize; iterator2++)
			        {
			        	if(letter.equals(guessedArray[iterator2]))
			       		{
				  		repeatFlag = 1;
				  		break;
			       		}
			    	}
            
			        if(repeatFlag == 1)
			        {
			        	continue;
			        }
			        
			        int index = ((int)character - (int)('a'));
			        freqs[index]++;
			        if (freqs[index] > largest) {
			        	largest = freqs[index];
			        	mostCommon = Character.toString(character);
			        }
            			
         		}   
		} 
      		return mostCommon;
   	}
   	
	//draw new hangman setup as incorrect variable increases
   	public static void displayHangman(int incorrect)
   	{
   		if (incorrect == 0) {
   			System.out.println("");
   			System.out.println("   _______");
   			System.out.println("  |       |");
   			System.out.println("  |       |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("  |");
   			System.out.println("------------");
   			System.out.println("");
   		}
   		else if(incorrect == 1)
      		{
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
      		}
		else if(incorrect == 2)
		{
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
		}
      		else if(incorrect == 3)
      		{
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |     --|");
			 System.out.println("  |       |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
      		}
     	 	else if(incorrect == 4)
      		{
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
      		}            
	       else if(incorrect == 5)
	       {
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |      / ");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
      		}                 
      		else if(incorrect == 6)
      		{
			 System.out.println("");
			 System.out.println("   _______");
			 System.out.println("  |       |");
			 System.out.println("  |       |");
			 System.out.println("  |       O");
			 System.out.println("  |     --|--");
			 System.out.println("  |       |");
			 System.out.println("  |      / \\");
			 System.out.println("  |");
			 System.out.println("  |");
			 System.out.println("------------");
			 System.out.println("");
      		}   
           
      		if (incorrect != 0) System.out.println("The AI has made " + incorrect + " incorrect guess(es)");
   	}

}
