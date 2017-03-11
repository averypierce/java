/*

   Christian Chmielewski
   Avery VanKirk
   
   ABSClient - The Airline Booking Service Client is the program that utilizes the tools provided by:
                  * SystemManager
                  * Airline
                  * Flight
                  * FlightSection
                  * Seat
                  * Airport
   
   
*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class ABSClient{
   public static void main(String[] args) throws FileNotFoundException{

      SystemManager mySystemManager = new SystemManager();
	  Scanner input = new Scanner(System.in);
      boolean proceed = true;
      SeatClass sClass;
      String userInput;
      String airportCode;
      String airlineName;
      String origin; 
      String destination; 
      String id; 
      int userSelection;
      int year;
      int month; 
      int day;
      int rows;
      int columns;
      char column;
      
      
      //Temporary Test Case from file 
      Scanner fileScanner = new Scanner(new File("testcases.txt"));
      try{	
    	  while(fileScanner.hasNext()){
    		  String[] commands = fileScanner.nextLine().split(",");    		  
    		  try{
    			  
	    		  if(commands[0].equals("createAirport")){	  
	    			  mySystemManager.createAirport(commands[1]);
	    		   }
	    		  if(commands[0].equals("createAirline")){
	    			  mySystemManager.createAirline(commands[1]);
	    		   }
	    		  if(commands[0].equals("createFlight")){
	    			  mySystemManager.createFlight(commands[1], commands[2], commands[3],Integer.parseInt(commands[4]), Integer.parseInt(commands[5]),Integer.parseInt(commands[6]),commands[7]);
	    		   }
	    		  if(commands[0].equals("createSection")){
	    			  mySystemManager.createSection(commands[1], commands[2], Integer.parseInt(commands[3]), Integer.parseInt(commands[3]), SeatClass.valueOf(commands[5]));
	    		   }
	    		  if(commands[0].equals("findAvailableFlights")){
	    			  mySystemManager.findAvailableFlights(commands[1], commands[2]);
	    		   }
	    		  if(commands[0].equals("bookSeat")){
	    			  mySystemManager.bookSeat(commands[1], commands[2],  SeatClass.valueOf(commands[3]), Integer.parseInt(commands[4]), commands[5].charAt(0));
	    		   }
	    		  if(commands[0].equals("displaySystemDetails")){
	    			 mySystemManager.displaySystemDetails(); 
	    		   }
    		  }
    		  catch(IllegalArgumentException e){
              System.out.println(e.getMessage()+ "\n");
           }
           
    	  }    
      }
      finally{
    	  fileScanner.close();
      }
        
      
      
      // Allow user to input data until they choose to exit
      while(proceed){
         try{
            System.out.println("What would you like to do?");
            System.out.println("1) Create an airport");
            System.out.println("2) Create an airline");
            System.out.println("3) Create a flight");
            System.out.println("4) Create a section");
            System.out.println("5) Find available flights");
            System.out.println("6) Book a seat");
            System.out.println("7) Print system details");
            System.out.println("8) Exit program\n");
            System.out.print("Selection number: ");
            userInput = input.next();
            
            userSelection = Integer.parseInt(userInput);
            
            // Handle user selection
            if(userSelection > 0 && userSelection < 9){
               switch(userSelection){
                  case 1:
                     System.out.print("\nAirport codes must be three alphabetic characters and unique. \n Please enter a new airport code: ");
                     airportCode = input.next().toUpperCase();
                     mySystemManager.createAirport(airportCode);
                     
                  break;
                  
                  case 2:
                     System.out.print("\nAirline names can be up to five (5) alphabetic characters and unique. \n Please enter a new airline name: ");
                     airlineName = input.next();
                     mySystemManager.createAirline(airlineName);
                  break;
                  
                  case 3:
                     System.out.print("\nWhat airline will this new flight be for: ");
                     airlineName = input.next();
                     System.out.print("\nFlight identification must be unique. \n Please enter a new flight identification: ");
                     id = input.next();
                     System.out.print("\nWhat will the origin airport be: ");
                     origin = input.next();
                     System.out.print("\nWhat will the destination airport be: ");
                     destination = input.next();
                     System.out.print("\nWhat year will this flight depart: ");
                     year = Integer.parseInt(input.next());
                     System.out.print("\nWhat month will this flight depart: ");
                     month = Integer.parseInt(input.next());
                     System.out.print("\nWhat day will this flight depart: ");
                     day = Integer.parseInt(input.next());
                     
                     mySystemManager.createFlight(airlineName, origin, destination, year, month, day, id);
                  break;
                  
                  case 4:
                     System.out.print("\nWhat airline will this new flight be for: ");
                     airlineName = input.next();
                     System.out.print("\nWhat flight number are you looking for: ");
                     id = input.next();
                     System.out.print("\nWhat kind of section do you want to make (first, business, economy): ");
                     sClass = SeatClass.valueOf(input.next().toUpperCase());
                     System.out.print("\nHow many columns in this section: ");
                     columns = Integer.parseInt(input.next());
                     System.out.print("\nHow many rows in this section: ");
                     rows = Integer.parseInt(input.next());
                     
                     
                     mySystemManager.createSection(airlineName, id, rows, columns, sClass);
                     
                  break;
                  
                  case 5:
                     System.out.print("\nPlease enter a origin airport code: ");
                     origin = input.next().toUpperCase();
                     System.out.print("\nPlease enter a destination airport code: ");
                     destination = input.next().toUpperCase();
                     
                     mySystemManager.findAvailableFlights(origin, destination);
                     
                  break;
                  
                  case 6:
                     System.out.print("\nWhat airline are you trying to find a seat on: ");
                     airlineName = input.next();
                     System.out.print("\nWhat flight are you trying to find a seat on: ");
                     id = input.next();
                     System.out.print("\nWhich seat class do you want to sit in (first, business, economy): ");
                     sClass = SeatClass.valueOf(input.next().toUpperCase());         
                     System.out.print("\nWhat row do you want to sit in: ");
                     rows = Integer.parseInt(input.next());
                     System.out.print("\nWhich column do you want to sit in (enter a letter): ");
                     column = input.next().charAt(0);
                     
                     mySystemManager.bookSeat(airlineName, id,  sClass, rows, column);
            
                  break;
                  
                  case 7:
                     mySystemManager.displaySystemDetails(); 
                  break;
                  
                  case 8:
                     
                     System.out.println("Thank you for using this program.");
                     proceed = false;
                     
                  break;
               
               }
            
            }else{
               throw new IllegalArgumentException(userInput + " is an invalid selection. \n Please try again.");
            }
            
            
            
         }catch(IllegalArgumentException e){
            System.out.println(e.getMessage()+ "\n\n");
         }
      }
   }
}