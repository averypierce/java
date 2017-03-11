import java.util.HashMap;
/*
   Christian Chmielewski
   Avery VanKirk
   
   Airline -  Class to track an airline's information
   
*/
import java.util.LinkedList;

public class Airline{
   
   // Data encapsulation for Airline
   HashMap<String, Flight> flights; //Map flight ID to actual Object
   String name; //airline name
   
   // Constructor
   public Airline(String name){
	
		  flights = new HashMap<String,Flight>();
		  this.name = name;
	 
   }
   
   // Add a flight to the airline
   public void addFlight(String id, String origin, String destination, int year, int month, int day){
	   flights.put(id, new Flight(origin, destination, id, year, month, day));
   }
   
   // Getter function
   public Flight getFlight(String flightId){
	   return this.flights.get(flightId);
   }
   
   // FlightExists
   public boolean flightExists(String id){
      if(flights.containsKey(id)){
         return true;
      }
      return false;
   }
   
   // Get flights that are the same
   public LinkedList<Flight> getSameFlights(String origin, String destination){
      
      LinkedList<Flight> sameFlights = new LinkedList<Flight>();
      for(String flight: flights.keySet()){
         if(flights.get(flight).sameFlightPath(origin, destination)){
               sameFlights.add(flights.get(flight));
         }
      }
      return sameFlights;
   }
   
   // Print all airline, flight, flight section and seat info
   public void toStrings(){
      System.out.println("\t" + this.name);
      
      for(String flight: flights.keySet()){
         flights.get(flight).toStrings();
      }
   }
}