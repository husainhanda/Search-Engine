package acc.finalProject.Team5.SearchEngine.Algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acc.finalProject.Team5.SearchEngine.SearchEngineApplication;

public class BoyerMooreSearchWords {

	/* To Search words in text file using Boyer Moore algorithm.returns repetition number for the words */
	public static int getSearchedWords(File fileLoc, String word)
	{
		int count=0;
		String val="";
		try
		{
			BufferedReader obj = new BufferedReader(new FileReader(fileLoc));
			String l = null;
			
			while ((l = obj.readLine()) != null){
				val= val+l;
			}
			obj.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
		// Locating position of word:::::
		String txt = val;
			
		int set = 0;
		
		for (int loc = 0; loc <= txt.length(); loc += set + word.length()) 
		{
			set = SearchEngineApplication.searchBM(word, txt.substring(loc)); 
			if ((set + loc) < txt.length()) {
				count++;
				System.out.println("\n"+word+ " at position " + (set + loc));  //printing position of word
			}
		}
		if(count!=0)	{		
			System.out.println("-------------------------------------------------");
			System.out.println("\n Located in "+fileLoc.getName()); // Founded from which text file..		
			System.out.println("-------------------------------------------------");
		}
		return count;
	}
	
	//calling edit distance on similar strings
	public static void findData(File source,int number,Matcher m,String pattern) throws FileNotFoundException,ArrayIndexOutOfBoundsException{
		EditDistance.searchWords(source, number, m, pattern);
	}
	
	public static void lookForAltWords(String pattern)
	{
		String line = " ";
		String pat = "[a-zA-Z0-9]+";	  
		 
		// Create a Pattern object
		Pattern r3 = Pattern.compile(pat);
		// Now create matcher object.
		Matcher m3 = r3.matcher(line);
		int num=0;
		
		File dir = new File(System.getProperty("user.dir")+"\\textFiles\\");
		File[] arr = dir.listFiles();
		for(int i=0 ; i<arr.length ; i++)
		{
			try
			{
				findData(arr[i],num,m3,pattern);
				num++;
			} 
			catch(FileNotFoundException e) {
				System.out.println("Exception:"+e.getMessage());
			}
		}
		
		Integer valueDistanceAllowed = 1;  // allowed editDistance 
		boolean foundMatch = false;  //true if word is found having edit distance equal to valueDistanceAllowed

		System.out.println("Did you mean? ");
		int val=0;
		for(Map.Entry m: SearchEngineApplication.values.entrySet()){
			if(valueDistanceAllowed == m.getValue()) {
				val++;
				System.out.print("("+val+") "+m.getKey()+"\n");		
				foundMatch = true;
			}
		}	        
		if(!foundMatch) System.out.println("Can't find match for given word");
	}
}
