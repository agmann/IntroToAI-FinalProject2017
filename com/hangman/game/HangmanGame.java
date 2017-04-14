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
      int arraySize = 0;//variable to keep track of how many indexes in array are filled
      String mostCommonLetter, matching = "";
      int matchFlag;
      int incorrect = 0, correct = 0;
      
      System.out.println("Enter your word for the Hangman Game: ");
      String selectedWord = scan.nextLine();
      
      System.out.println("Entered word is: " + selectedWord);

      int wordLength = selectedWord.length();
      
      System.out.println("length of word is: " + wordLength);

      words = dict.getWordsThatContain("", wordLength);
      //for (String word : words) {
		//	System.out.println(word);
		//}
      
      while(words.size() > 1)
      {
         matchFlag = 0;
      
         mostCommonLetter = wordFrequency(words, wordLength, guessedLetters, arraySize);
      
         System.out.println("most common letter in word pool: " + mostCommonLetter);
      
         System.out.println("AI guesses the letter: " + mostCommonLetter);
      
         guessedLetters[arraySize++] = mostCommonLetter;
         if(arraySize == 26)
            break;
            
         matching = "";
         for(int i = 0; i < wordLength; i++)
         {
            String letter = Character.toString(selectedWord.charAt(i));
            if(mostCommonLetter.equals(letter))
            {
               matching = matching.concat(mostCommonLetter);
               matchFlag = 1;
               correct++;
            }
            else
            {
               matching = matching.concat("*");
            }
         }
        
         
         System.out.println("Current String: " + matching);

         if(matchFlag == 1)
         {
            EnglishDictionary.refineListForWordsMatching(words, matching);
            for (String word : words) {
	   	      System.out.println(word);
	 	      }
            
            if(correct == wordLength)
            {
               System.out.println("All letters are known, the AI guesses: " + words.get(0));
            
               if((words.get(0)).equals(selectedWord))
               {
                  System.out.println("The AI has guessed the word successfully. The AI wins!");
               }
               
               break;
               //this happens when all letters are "known"
            }
         }
         else if(matchFlag == 0)
         {
            incorrect++;
            System.out.println("The AI has made " + incorrect + " incorrect guess(es)");
            if(incorrect == 6)
            {
               System.out.println("The AI has used up all 6 guesses. The Player wins!");         
               break;
            }
            EnglishDictionary.refineListForWordsThatDontContain(words, mostCommonLetter);
		      for (String word : words) {
	   		   System.out.println(word);      
   		   }
         }
        
      }


      
      /*String lookup = "c";
		int wordLength = 5;
		
		words = dict.getWordsThatStartWith(lookup, wordLength);
		
		System.out.println("Found " + words.size() + " words that start with " + lookup + " and have length of " + wordLength + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		
		String matching = "c_as_";
		EnglishDictionary.refineListForWordsMatching(words, matching);
		System.out.println("Matching phrase is " + matching + 
				", hence the refined list should contain words with at least one c, a, and s, in the appropriate positions");
		
		System.out.println("Refined word list, all words must match \'" + matching + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		System.out.println("Notice how some of the words have more than one c, a, or s. "
				+ "This is not possible in hangman, so we need a way to specify UNIQUE wildcards. Use the '*' character instead.");
		
		
		matching = "c*as*";
		EnglishDictionary.refineListForWordsMatching(words, matching);
		System.out.println("Matching phrase is " + matching + 
				", hence the refined list should contain words with ONLY one c, a, and s, in the appropriate positions");
		
		System.out.println("Refined word list, all words must match \'" + matching + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
		}
		
		lookup = "t";
		EnglishDictionary.refineListForWordsThatDontContain(words, lookup);
		System.out.println(lookup + " was a wrong guess, so parse out all those words that contain " + lookup); 
		System.out.println("Refined word list, all words must NOT contain \'" + lookup + "\' \nnew size is " + words.size() + ":");
		for (String word : words) {
			System.out.println(word);
      
		}
		*/
	
	}
   
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

}
