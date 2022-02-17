import java.util.List;
import java.util.Scanner;

import exception.NewsAPIException;
import model.newsinfo;
import model.thenewsdb.Location;
import model.thenewsdb.LocationResult;
import newsapi.LocationAPI;
import newsapi.NewsAPI;
import services.LocationbyIpDetection;
import services.NewsAPIService;

public class Application {
//I created a menu of specific choices about news and user has just to select one of the choices by typing the suitable number  
//When user types a number, he/she is asked to type the required parameter(s) and then they can see in console the results of their choice in a list form
	
	public static void main(String[] args) throws NewsAPIException{
		
        //it receives ApiKey and API_URL from class NewsAPI		
		NewsAPIService newsAPIService= NewsAPI.getNewsAPIService();
		System.out.println("Welcome to News APP!");
		
        //menu with choices in numbers for the user			
        //while(true) is created in order to repeat a choice (the same or another)
		while(true) {
		 System.out.println("Select one of the following \n ");
	     System.out.println("Show the top headlines");
	     System.out.println("1a. Show the top headlines for user's country according to IP Address:");
	     System.out.println("1b. Show the top headlines for another country:");
	     System.out.println("1c. Show the top headlines for a category: \n ");
	     System.out.println("Search everything news according to criteria");
	     System.out.println("2a. Search Everything News about a query ");
	     System.out.println("2b. Search Everything News about a source");
	     System.out.println("2c. Search Everything News about a language ");
	     System.out.println("2d. Search Everything News about a query between specific date of publication \n");
	     System.out.println("3. Exit \n");
	     System.out.println("Your input");
		
        //Scanner is used to ask user to type number of their choice or a parameter		
		Scanner sc = new Scanner (System.in);
		String input = sc.nextLine();
		
		System.out.println("Your input:" + input);
        //switch is used for the cases of user's possible choices		
		switch(input) {
		//it returns top headlines from user's country according to IP Address
		case "1a":
		//it receives API_URL, apiKey and ipv6 from class LocationAPI
		final LocationbyIpDetection newsSearchService1= LocationAPI.getLocationbyIPDetection();
		//it uses the method searchUserLocation which was created in class LocationbyIpDetection
		final LocationResult results2 = newsSearchService1.searchUserLocation();
	    //country is found using class getCountry in class Location
		String country2 = Location.getCountry();
		System.out.println("Your country is: " + country2);
		System.out.println("According to documentation in https://newsapi.org/docs/endpoints/top-headlines,");
		System.out.println("possible countries for endpoint (top-headlines)");
		System.out.println("are these: ae-ar-at-au-be-bg-br-ca-ch-cn-co-cu-cz-de-eg-fr-gb-gr-hk-hu-id-ie-il-in-it-jp-kr-lt-lv-ma-mx-my-ng-nl-no-nz-ph-pl-pt-ro-rs-ru-sa-se-sg-si-sk-th-tr-tw-ua-us-ve and za");
		System.out.println("So there is a possibility for user not to see top-headlines news for their country, if user does not live in one of the aforementioned countries");
			 try {
			 final List<newsinfo> results= newsAPIService.getTopHeadlinesForCountry(country2); 
				System.out.println("Results are:");
				System.out.println(results);
			} catch (NewsAPIException e){
				System.err.println(e.getMessage());
			}
			  break;
		//it returns top-headline news from a country that user typed 
		case "1b":
			System.out.println("To see top-headlines acccording to a country you have to type a country of your choice");
			System.out.println("According to documentation in https://newsapi.org/docs/endpoints/top-headlines,");
			System.out.println("possible countries for endpoint (top-headlines)");
			System.out.println("are these: ae-ar-at-au-be-bg-br-ca-ch-cn-co-cu-cz-de-eg-fr-gb-gr-hk-hu-id-ie-il-in-it-jp-kr-lt-lv-ma-mx-my-ng-nl-no-nz-ph-pl-pt-ro-rs-ru-sa-se-sg-si-sk-th-tr-tw-ua-us-ve and za");
			System.out.println("Enter the country of your choice:");
			String country = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.getTopHeadlinesForCountry(country);
			    System.out.println("Results are:");
			    System.out.println(results);
		} catch (NewsAPIException e){
			    System.err.println(e.getMessage());
		}
		  break;
		//it returns top-headline news about a category for a country that user typed 
		case "1c":
			System.out.println("To see top-headlines according to category, you have to type firstly country of your choice and then the category you want");
			System.out.println("According to documentation in https://newsapi.org/docs/endpoints/top-headlines,");
			System.out.println("possible countries for endpoint (top-headlines)");
			System.out.println("are these: ae-ar-at-au-be-bg-br-ca-ch-cn-co-cu-cz-de-eg-fr-gb-gr-hk-hu-id-ie-il-in-it-jp-kr-lt-lv-ma-mx-my-ng-nl-no-nz-ph-pl-pt-ro-rs-ru-sa-se-sg-si-sk-th-tr-tw-ua-us-ve and za");
			System.out.println("Enter the country of your choice:");
			String country1 = sc.nextLine();
			System.out.println("possible categories for endpoint (top-headlines) are these: business, entertainment, general, health, science, sports and technology");
			System.out.println("Enter the category of your choice:");
			String category = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.getTopHeadlinesForCategory(country1,category);
			     System.out.println("Results are:");
			     System.out.println(results);
		 } catch (NewsAPIException e){
			     System.err.println(e.getMessage());
			}
			break;
       //it returns everything news about a specific query that user typed
		case "2a":
			System.out.println("To see news acccording to a query, you have to type a query of your choice");
		    System.out.println("You can type whatever query you want");
			System.out.println("Enter the query of your choice:");
			String q = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.searchEverythingNewsForQuery(q);
			     System.out.println("Results are:");
			     System.out.println(results);
		} catch (NewsAPIException e){
			     System.err.println(e.getMessage());
		}
		  break;
	   //it returns everything news about a specific source that user typed
		case "2b":
			System.out.println("To see news according to sources, you must firstly type query of your choice ");
		    System.out.println("Secondly, you have to type the language of your choice and then the source you want");
		    System.out.println("You can type whatever query you want");
			System.out.println("Enter the query of your choice:");
			String q1 = sc.nextLine();
			System.out.println("According to documentation in https://newsapi.org/docs/endpoints/everything,");
			System.out.println("possible languages for endpoint (everything)");
			System.out.println("are these: ar-de-en-es-fr-he-it-nl-no-pt-ru-se-ud and zh");
			System.out.println("That means, greek language is not supported");
			System.out.println("Enter the language of your choice:");
			String language = sc.nextLine();
			System.out.println("You can type whatever sources you want");
			System.out.println("Enter the source of your choice:");
			String sources = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.searchEverythingNewsForSources(q1,language,sources);
				 System.out.println("Results are:");
				 System.out.println(results);
		  } catch (NewsAPIException e){
				 System.err.println(e.getMessage());
			}
			break;
	    //it returns everything news about a specific language that user typed
		case "2c":
			System.out.println("To see news according to language, you must firstly type query of your choice ");
		    System.out.println("and then you have to type the language you want");
		    System.out.println("You can type whatever query you want");
			System.out.println("Enter the query of your choice:");
			String q2 = sc.nextLine();
			System.out.println("According to documentation in https://newsapi.org/docs/endpoints/everything,");
			System.out.println("possible languages for endpoint (everything)");
			System.out.println("are these: ar-de-en-es-fr-he-it-nl-no-pt-ru-se-ud and zh");
			System.out.println("That means, greek language is not supported");
			System.out.println("Enter the language of your choice:");
			String language1 = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.getSearchEverythingNewsForLanguage(q2,language1);
				 System.out.println("Results are:");
				 System.out.println(results);
		  } catch (NewsAPIException e){
				 System.err.println(e.getMessage());
			}
			break;
	    //it returns everything news between specific dates for a source and query that user typed
		case "2d":
			System.out.println("To see news according to date of publication, you must firstly type query of your choice ");
		    System.out.println("Secondly, you have to type the language of your choice");
		    System.out.println("Thirdly, you have to type the sources of your choice and then the date of publication you want");   
		    System.out.println("You can type whatever query you want");
			System.out.println("Enter the query of your choice:");
			String q3 = sc.nextLine();
			System.out.println("According to documentation in https://newsapi.org/docs/endpoints/everything,");
			System.out.println("possible languages for endpoint (everything)");
			System.out.println("are these: ar-de-en-es-fr-he-it-nl-no-pt-ru-se-ud and zh");
			System.out.println("That means, greek language is not supported");
			System.out.println("Enter the language of your choice:");
			String language2 = sc.nextLine();
			System.out.println("You can type whatever sources you want");
			System.out.println("Enter the source of your choice:");
			String sources1 = sc.nextLine();
			System.out.println(" You can also type whatever dates (from&to) you want to search for");
			System.out.println("Enter the start date (from when) for the publications of your choice:");
			String from = sc.nextLine();
			System.out.println("Enter the end date (to when) for the publications of your choice:");
			String to = sc.nextLine();
			try {
			final List<newsinfo> results = newsAPIService.searchEverythingNewsForDateOfPublication(q3,language2,sources1,from,to);
				 System.out.println("Results are:");
				 System.out.println(results);
		  } catch (NewsAPIException e){
			     System.err.println(e.getMessage());
			}
			break;
		//exit of the programm	
		case "3":
			     System.out.println("End of programm ");
			     System.exit(0);
			break;
		default:
			     System.out.println("Invalid input. Please try again");
	
		}
		
		}
			
		
	}

}
