package testdatasource;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ScrapingArticle {

	
	public static void main(String[] args) {
		
		try {
		
			Document doc = Jsoup.connect("https://may1215may.pixnet.net/blog/post/405415959").get();
//			Elements temp = doc.select("div.articleContent");
			Elements temp = doc.select("div.article-body");
			
		   for (Element script : doc.getElementsByTag("script")) { //除去所有 script
		        script.remove();
		    }
			
			String content;
			content = temp.toString();
							
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
