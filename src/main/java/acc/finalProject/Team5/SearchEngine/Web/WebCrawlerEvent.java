package acc.finalProject.Team5.SearchEngine.Web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerEvent {
	static HashSet<String> urls = new HashSet<String>(); 
		
	public static void webCrawler(String urlValue)
	{
		urls.add(urlValue);
		try {			
			 Document d = Jsoup.connect(urlValue).get();//Use to fetch and parse the HTML page
			 String p = ".*" + urlValue.replaceAll("^(http|https)://", "") + ".*";// replacing http:// or  https:// with blank
			 System.out.println("\nURL p to parse: "+ p);

			 Elements urlsOnWebPage = d.select("a[href]");//finds links (a tags with href attributes) 
			 
			 String url;
			 for (Element page : urlsOnWebPage) {
				 url = page.attr("abs:href");// get an absolute URL from an attribute that may be a relative URL
				 if(urls.contains(url)) {
					 System.out.println("\nURL: " + url + " ----> already visited");// example : https://www.javatpoint.com/java-tutorial
				 } 
				 else if(!Pattern.matches(p, url)) {
					 System.out.println("\nURL: " + url + " ----> is irrevant. Will not be parsed.");// example : https://www.javatpoint.com/jsp-tutorial
				 }
				 else {
					 urls.add(page.attr("abs:href"));
					 System.out.println("\nURL: " + url + " ---->  will be crawled");//https://www.facebook.com/sharer.php?u=https://www.javatpoint.com/java-tutorial
				 }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void convertHtmlToText()
	{
		try {
			String val, url;
			String path = System.getProperty("user.dir") + "\\textFiles\\";
			Iterator<String> iter = urls.iterator();
			while(iter.hasNext())
			{
				url = iter.next();
				Document d = Jsoup.connect(url).get();
				val = d.text();
				String name = d.title().replaceAll("[^a-zA-Z0-9_-]", "")+".val";
				BufferedWriter bw = new BufferedWriter( 
		                new FileWriter(path + name, true)); 
		        bw.write(url + " " + val); 
		        bw.close(); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void startCrawl(String urlValue)
	{
		webCrawler(urlValue);
		convertHtmlToText();
	}

}
