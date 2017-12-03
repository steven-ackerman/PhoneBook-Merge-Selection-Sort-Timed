/**
* Author:
*  Steven Ackerman
*  sackerma@uoregon.edu
* Sample Code Sources:
* See Comments at end of this Class.
* Other Credits for Project:
* See Comments at end of this Class.
* 
*/

//Import Statements:
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



//Start PhoneBook Class
public class PhoneBook {
	private static final String fileName = "phonebook_test.txt";
	
	//Constructor
	public PhoneBook(){
		
		
	}//End PhoneBook Constructor
		
	//Start Main Method
	public static void main(String[] args) throws IOException {
		//Create PhoneBook Entries ArrayList.
		ArrayList<PhoneBookEntries> phoneBook = new ArrayList<PhoneBookEntries>();
		
		FileReader fr;
		BufferedReader br;
		String phone = new String();
		String lastName = new String();
		String firstName = new String();
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] phoneEntryInfo = currentLine.split("[\\s\\-\\.\\'\\?\\,\\_\\@]+");
				phone = phoneEntryInfo[0];
				lastName = phoneEntryInfo[1];
				firstName = phoneEntryInfo[2];
				PhoneBookEntries person = new PhoneBookEntries(phone, lastName, firstName);
				phoneBook.add(person);
				
			}
			//br.close();
			//fr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		//Test all Methods & Constructors.
		containsItem(phoneBook, "Zul");
		System.out.println("Selection Sort:");
		System.out.println(alphabeticalSelectionSort(phoneBook));
		System.out.println("MergeSort:");
		System.out.println(mergeSort(phoneBook));
		System.out.println("Alpha Sorted: Should Return True");
		System.out.println(alphaSorted(mergeSort(phoneBook)));
		System.out.println("Alpha Sorted: Should Return False");
		System.out.println(alphaSorted(phoneBook));
		sortMethodTimer(phoneBook);
		
	}//End Main Method
	
	//Start Contains Method
	public static void containsItem(ArrayList<PhoneBookEntries> phoneBookArray,String searchCriteria) throws IOException {
		//Create String & Buffered  Writers
		FileWriter fw = new FileWriter("Output.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		for (PhoneBookEntries item : phoneBookArray) {
			if (item.getFirst().contains(searchCriteria)
					||item.getLastName().contains(searchCriteria)
					||item.getPhone().contains(searchCriteria)) {
				bw.write(item.toString());
				
			}

			//bw.close();
		}
		bw.close();
	}//End containsItem method.
	
	//Start alphabetically sorted Array Method
	public static ArrayList<PhoneBookEntries> alphabeticalSelectionSort(ArrayList<PhoneBookEntries> phoneBookArray){
		//Copy Array to another Array and Sort.
		ArrayList<PhoneBookEntries> alphabeticallySorted = new ArrayList<PhoneBookEntries>();
		alphabeticallySorted.addAll(phoneBookArray);
		int max, i ,j;
		//
	     PhoneBookEntries temp;
	     for (i = 0; i < alphabeticallySorted.size()-1; i++)
	      {
	        max = i;
	        //Find the two items and compare them. If one is bigger than the other set max to equal the smallest of the two.
	        for (j = i+1; j < alphabeticallySorted.size(); j++)
	        {
	          if (alphabeticallySorted.get(max).getLastName().compareTo(alphabeticallySorted.get(j).getLastName()) > 0)
	            max = j;
	        }
	        //Sets the max position at the i'th element index, swapping the elements.
	        temp = alphabeticallySorted.get(i);
	        alphabeticallySorted.set(i, alphabeticallySorted.get(max));
	        alphabeticallySorted.set(max, temp);
	      }
		
		
		
		return alphabeticallySorted;
	}
	public static ArrayList<PhoneBookEntries> mergeSort(ArrayList<PhoneBookEntries> phoneBookArray) {
		//Copy Array to another Array and Sort.
		ArrayList<PhoneBookEntries> alphabeticallySorted = new ArrayList<PhoneBookEntries>();
		alphabeticallySorted.addAll(phoneBookArray);
        //Create ArrayList's for the left and right elements of ArrayList
		ArrayList<PhoneBookEntries> left = new ArrayList<PhoneBookEntries>();
        ArrayList<PhoneBookEntries> right = new ArrayList<PhoneBookEntries>();
        int middle;
 
        if (alphabeticallySorted.size() == 1) {    
            return alphabeticallySorted;
        } else {
            middle = alphabeticallySorted.size() / 2;
            //Copy the first half of Array in the Left ArrayList.
            for (int i = 0; i < middle; i++) {
                    left.add(alphabeticallySorted.get(i));
            }
 
            //Copy the second half of Array into the Right ArrayList.
            for (int i = middle; i < alphabeticallySorted.size(); i++) {
                    right.add(alphabeticallySorted.get(i));
            }
 
            // Recursively Sort the Left and Right ArrayList's
            left  = mergeSort(left);
            right = mergeSort(right);
 
            // Call the Merge method to  merge both Arrays into one.
            return merge(left, right, alphabeticallySorted);
        }
        //return alphabeticallySorted;
    }
	
	private static ArrayList<PhoneBookEntries> merge(ArrayList<PhoneBookEntries> left,
			ArrayList<PhoneBookEntries> right,
			ArrayList<PhoneBookEntries> phoneBookArray) {
		//Copy Array to another Array and Sort.
		ArrayList<PhoneBookEntries> alphabeticallySortedMerge = new ArrayList<PhoneBookEntries>();
		
		
        int leftIndex = 0;
        int rightIndex = 0;
        
        
        // Takes the smaller of Left and Right and Adds it to alphabeticallySorted.
        while (leftIndex < left.size() && rightIndex < right.size()) {
        	if (left.get(leftIndex).getLastName().compareTo(right.get(rightIndex).getLastName()) < 0) {
            	alphabeticallySortedMerge.add(left.get(leftIndex));
                leftIndex++;
            } else {
            	alphabeticallySortedMerge.add(right.get(rightIndex));
                rightIndex++;
            }
            
        }
 
        ArrayList<PhoneBookEntries> remaining;
        int remainingIndex;
        if (leftIndex >= left.size()) {
            remaining = right;
            remainingIndex = rightIndex;
        } else {
            remaining = left;
            remainingIndex = leftIndex;
        }
 
        // Copy the rest of the remaining Array.
        for (int i = remainingIndex; i < remaining.size(); i++) {
        	alphabeticallySortedMerge.add(remaining.get(i));
        	
        }
        return alphabeticallySortedMerge;
    }
	private static Boolean alphaSorted(ArrayList<PhoneBookEntries> phoneBook){
		//Iterate over each last name in phoneBook
		for(int i = 0; i < phoneBook.size() - 1; i++) {
			if(phoneBook.get(i).getLastName().compareTo(phoneBook.get(i + 1).getLastName()) > 0){
				return false;
			}
		}
		return true;
	}
	//Start Sort Method Timer.
	private static  void sortMethodTimer(ArrayList<PhoneBookEntries> phoneBook) {
		
		
		//Merge Start Timer:
		long startTime = System.currentTimeMillis();
		mergeSort(phoneBook);
		long endTime = System.currentTimeMillis();
		long totalRuntime = (endTime - startTime) / 1000;
		long totalRunTimeMilliseconds = (endTime - startTime);
		System.out.println("Merge Sort Time in Seconds:"+totalRuntime+"\nTime in Milliseconds:"+totalRunTimeMilliseconds);
		
		//Selection Sort Timer:
		startTime = System.currentTimeMillis();
		alphabeticalSelectionSort(phoneBook);
		endTime = System.currentTimeMillis();
		totalRuntime = (endTime - startTime) / 1000;
		totalRunTimeMilliseconds = (endTime - startTime);
		System.out.println("Selection Sort Time in Seconds:"+totalRuntime+"\nTime in Milliseconds:"+totalRunTimeMilliseconds);
		
	
	}
}//End Class


/*
 * ----<Sources>----
 *  Graduate Teaching Fellow's  Erin McCarthy and Annaliese Johnson
 *  for help in Lab and Office Hours. They explained well the things that were supposed to happen and 
 *  what I needed to understand first in order to solve the problem. 
 * 
 * Stack Overflow:
 * Sample Code viewed that created a StringWriter and used Print Writer.
 * Sample Code viewed that Merged Array Lists.
 * 
 * Code was not used for my implementation because it would not work for my implementation. 
 *  
 * Array List sorting Tutorial:
 * https://beginnersbook.com/2013/12/how-to-sort-arraylist-in-java/
 * 
 * Java Early Objects Text Book:
 * Chapter 18. Full Chapter and Code from Page 762:
 * 
 * 
 * Example Code from Professor  Lei Jiao:
 * https://classes.cs.uoregon.edu/17F/cis212/examples/11-7a.java
 * https://classes.cs.uoregon.edu/17F/cis212/examples/10-7a.java
 * */
