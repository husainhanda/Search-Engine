package acc.finalProject.Team5.SearchEngine.Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class MergeSortAlgorithm {
	
	public static void getSortedPages(Hashtable<?, Integer> ht,int rep)
	{
		 //Sorting list
	       ArrayList<Map.Entry<?, Integer>> list= new ArrayList(ht.entrySet());
	       Collections.sort(list, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> in, Map.Entry<?, Integer> in2) {
	            return in.getValue().compareTo(in2.getValue());
	        }});
	      
	       Collections.reverse(list);
	       if(rep!=0) {
		       System.out.println("\n:::::::::::::::WEB PAGES RANKED:::::::::::::::\n");
		       
		       int a = 10;
		       int b = 1;
		       System.out.printf( "%-10s %s\n", "Sr.No", "Name and repetition" );
		       System.out.println(":::::::::::::::::::::::::::::::::::::::::::::");
				while (list.size() > b && a>0){
					System.out.printf("\n%-10d| %s\n", b, list.get(b));
					b++;
					a--;
				}
				System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::\n");
	       }
	}

}
