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
      
	      System.out.println("Enter your word for the Hangman Game: ");
	      String selectedWord = scan.nextLine();
	      selectedWord = selectedWord.toLowerCase();
	      System.out.println();
	      int wordLength = selectedWord.length();

	      //initializes the word list with all words with same length as user's entered word
	      words = dict.getWordsThatContain("", wordLength);

	      //loops until only one word is left in the list or other conditions have been met
	      while(words.size() > 1)
	      {
		 matchFlag = 0;
      
		 mostCommonLetter = wordFrequency(words, wordLength, guessedLetters, arraySize);

		 System.out.println("AI guesses the letter: " + mostCommonLetter);

		 guessedLetters[arraySize++] = mostCommonLetter;
		 if(arraySize == 26)
		    break;

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

	      }

	}
	
	//function to calculate which letter appears most freuently in word list and returns the letter
	public static String wordFrequency(ArrayList<String> words, int wordLength, String[] guessedArray, int arraySize)
	{
		int iterator, iterator2, largest = 0, repeatFlag = 0;
	        String letter;
	        String mostCommon = "";
	        int a=0, b=0, c=0, d=0, e=0, f=0, g=0, h=0, i=0, j=0, k=0, l=0, m=0, n=0, o=0, p=0;
	        int q=0, r=0, s=0, t=0, u=0, v=0, w=0, x=0, y=0, z=0;
      
      		for (String word : words) {
         		for(iterator = 0; iterator < wordLength; iterator++){
            			letter = Character.toString(word.charAt(iterator));
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
            
            			if(letter.equals("a"))
            			{
               				a++;
               				if(a > largest)
               				{
                  				largest = a;
                  				mostCommon = "a";
               				}
            			}
		    		else if(letter.equals("b"))
		    		{
		       			b++;
		       			if(b > largest)
		       			{
			  			largest = b;
			  			mostCommon = "b";
		       			}
		    		}
            			else if(letter.equals("c"))
            			{
               				c++;
              				if(c > largest)
               				{
              					largest = c;
                  				mostCommon = "c";
               				}
           			}
            			else if(letter.equals("d"))
            			{
               				d++;
               				if(d > largest)
               				{
                  				largest = d;
                  				mostCommon = "d";
               				}
				}
				else if(letter.equals("e"))
				{
				       e++;
				       if(e > largest)
				       {
				       		largest = e;
					  	mostCommon = "e";
				       }
				}
				else if(letter.equals("f"))
				{
				       f++;
				       if(f > largest)
				       {
						largest = f;
					  	mostCommon = "f";
				       }
				}
				else if(letter.equals("g"))
				{
				       g++;
				       if(g > largest)
				       {
						largest = g;
						mostCommon = "g";
				       }
				}
            			else if(letter.equals("h"))
           			{
               				h++;
               				if(h > largest)
               				{
                  				largest = h;
				                  mostCommon = "h";
               				}
            			}
            			else if(letter.equals("i"))
            			{
			        	i++;
               				if(i > largest)
			                {
                  				largest = i;
                  				mostCommon = "i";
               				}
            			}
            			else if(letter.equals("j"))
            			{
               				j++;
               				if(j > largest)
               				{
                  				largest = j;
                  				mostCommon = "j";
               				}
            			}
            			else if(letter.equals("k"))
            			{
               				k++;
               				if(k > largest)
               				{
                  				largest = k;
                  				mostCommon = "k";
               				}
            			}
            			else if(letter.equals("l"))
            			{
               				l++;
               				if(l > largest)
               				{
                  				largest = l;
                  				mostCommon = "l";
               				}
            			}
            			else if(letter.equals("m"))
            			{
               				m++;
               				if(m > largest)
               				{
                  				largest = m;
                  				mostCommon = "m";
               				}
            			}
            			else if(letter.equals("n"))
            			{
               				n++;
               				if(n > largest)
               				{
                  				largest = n;
                  				mostCommon = "n";
               				}
            			}
            			else if(letter.equals("o"))
            			{
               				o++;
               				if(o > largest)
               				{
                  				largest = o;
                  				mostCommon = "o";
               				}
            			}
            			else if(letter.equals("p"))
            			{
               				p++;
               				if(p > largest)
               				{
                  				largest = p;
                  				mostCommon = "p";
               				}
            			}
            			else if(letter.equals("q"))
            			{
               				q++;
               				if(q > largest)
               				{
                  				largest = q;
                  				mostCommon = "q";
               				}
            			}
            			else if(letter.equals("r"))
            			{
               				r++;
               				if(r > largest)
               				{
                  				largest = r;
                  				mostCommon = "r";
               				}
            			}
            			else if(letter.equals("s"))
            			{
               				s++;
               				if(s > largest)
               				{
                  				largest = s;
                  				mostCommon = "s";
               				}
            			}
            			else if(letter.equals("t"))
            			{
               				t++;
               				if(t > largest)
               				{
                  				largest = t;
                  				mostCommon = "t";
            	   			}
            			}
            			else if(letter.equals("u"))
            			{
               				u++;
               				if(u > largest)
               				{
                  				largest = u;
                  				mostCommon = "u";
               				}
            			}
            			else if(letter.equals("v"))
            			{
               				v++;
               				if(v > largest)
               				{
                  				largest = v;
                  				mostCommon = "v";
               				}
            			}
            			else if(letter.equals("w"))
            			{
               				w++;
               				if(w > largest)
               				{
                  				largest = w;
				                mostCommon = "w";
               				}
            			}
            			else if(letter.equals("x"))
            			{
               				x++;
               				if(x > largest)
               				{
                  				largest = x;
                  				mostCommon = "x";
               				}
			        }
            			else if(letter.equals("y"))
            			{
               				y++;
               				if(y > largest)
                			{
                  				largest = y;
				                mostCommon = "y";
				        }
            			}
            			else if(letter.equals("z"))
            			{
               				z++;
               				if(z > largest)
               				{
                  				largest = z;
                  				mostCommon = "z";
               				}
            			}
         		}   
		} 
      		return mostCommon;
   	}
   	
	//draw new hangman setup as incorrect variable increases
   	public static void displayHangman(int incorrect)
   	{
        	if(incorrect == 1)
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
           
      		System.out.println("The AI has made " + incorrect + " incorrect guess(es)");
   	}

}
