package acc.finalProject.Team5.SearchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import acc.finalProject.Team5.SearchEngine.Algorithms.BoyerMooreAlgorithm;
import acc.finalProject.Team5.SearchEngine.Algorithms.BoyerMooreSearchWords;
import acc.finalProject.Team5.SearchEngine.Algorithms.MergeSortAlgorithm;
import acc.finalProject.Team5.SearchEngine.Web.WebCrawlerEvent;


public class SearchEngineApplication {

	public static ArrayList<String> val = new ArrayList<String>();
	public static Hashtable<String, Integer> values = new Hashtable<String, Integer>();

	public SearchEngineApplication() {
		System.out.println(":::::::::::::::::::SEARCH ENGINE STARTED:::::::::::::::::::");
	}

	// method to search patterns using Boyer Moore method.
	public static int searchBM(String pat, String txt) {
		BoyerMooreAlgorithm bm = new BoyerMooreAlgorithm(pat);
		int val = bm.search(txt);
		return val;
	}

	public static void searchEngine() {
		System.out.println("\n:::::::::::::::::::CRAWLER STARTED:::::::::::::::::::");
		String url = "https://www.javatpoint.com/java-tutorial";
		WebCrawlerEvent.startCrawl(url);
		System.out.println("\n:::::::::::::::::::CRAWLER STOPPED:::::::::::::::::::");
		
		/** to store the repetition of words in all text files where the key is name of file and value is repetition */
		Hashtable<String, Integer> repeats = new Hashtable<String, Integer>();
		Scanner sc = new Scanner(System.in);
		String input = "y";
		do {
			System.out.println("\nENTER THE WORD TO BE SEARCHED: ");
			String p = sc.nextLine();
			long valFN = 0;
			int r = 0;
			int pages = 0;

			try {
				File dir = new File(System.getProperty("user.dir") + "\\textFiles\\");

				File[] arr = dir.listFiles();
				for (int i = 0; i < arr.length; i++) {
					// Search word from user input.
					r = BoyerMooreSearchWords.getSearchedWords(arr[i], p);
					repeats.put(arr[i].getName(), r);
					if (r != 0)
						pages++;
					valFN++;
				}

				if (pages == 0) {
					System.out.println("Not Found!!");
					System.out.println("Searching web::::::::::::::");
					BoyerMooreSearchWords.lookForAltWords(p);
				} 
				else {
					//Web Pages getting ranked by merge sort 
					//MergeSort.pageSort by default uses merge sort
					MergeSortAlgorithm.getSortedPages(repeats,pages);
				}	
				System.out.println("\n\n Do you wish to continue(y/n)??");
				input = sc.nextLine();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
		while(input.equals("y"));
		System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
		System.out.println(" BROWSER STOPPED!! ");
		System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
			
	}

	// Main method runs searchEngine function
	public static void main(String[] args) {
			SearchEngineApplication.searchEngine();		
	}
}
