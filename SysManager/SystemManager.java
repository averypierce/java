/*
  Christian Chmielewski
   Avery VanKirk
   
   SystemManager - This is the main interface/tool to communicate between the client and the utility classes
      
*/

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;
public class SystemManager{
   
      // Data encapsulation for SystemManager: store airline and airport information
	   HashMap<String, Airline> airlines;
      HashMap<String, Airport> airports; 
      
      // Constructor
      public SystemManager(){
         //airlines = new HashMap<String, Airline>();
    	   airlines = new HashMap<String, Airline>();
         airports = new HashMap<String, Airport>();
      }
      
      // Create a new Airport - AV
      public void createAirport(String airportCode){
      // airportCode must be exactly three characters AND UNIQUE (not in airports HashSet), handle this before createAirport is called?

       if (airportCode.length() != 3)
    	   throw new IllegalArgumentException(airportCode + " is invalid. AirPort codes must be 3 characters.");
       if(!Pattern.matches("[A-Z]+", airportCode))
    	   throw new IllegalArgumentException(airportCode + " is invalid. Airport code may only contain characters A-Z.");      
       if (this.airports.containsKey(airportCode))
    	   throw new IllegalArgumentException("Airport code "+ airportCode + " already in use.");
   	   
       this.airports.put(airportCode,new Airport(airportCode)); 
      }
      
      // Create a new Airline - CBC
      public void createAirline(String name){
      
         
         if(Pattern.matches("\\s+", name)){
            throw new IllegalArgumentException("Airline name can not be left blank.");
         }
         if(name.length() > 5 || name.length() < 1){
         
            // Throw exception for airline names that are too long.
            throw new IllegalArgumentException(name + " is invalid. Airline name must be 1-5 characters.");
         }
         
         if(name.length() > 5 || name.length() < 1 ){
         
            // Throw exception for airline names that are too long.
            throw new IllegalArgumentException(name + " is invalid. Airline name must be 1-5 characters.");
            
         }
      
         if(this.airlines.containsKey(name)){
            
            // Throw exception for airline names that are not unique.    
            throw new IllegalArgumentException("Airline name " + name + " already in use.");
            
         }
         
         this.airlines.put(name, new Airline(name));
      }
      
      // Create a new Flight - CBC
      public void createFlight(String name, String origin, String destination, int year, int month, int day, String id){
    	  
         if(Pattern.matches("\\s+", name) || name.length() == 0){
         
         	throw new IllegalArgumentException("Airline name can not be left blank.");
         }
         
         if(Pattern.matches("\\s+", id) || id.length() == 0){
         
         	throw new IllegalArgumentException("FlightID can not be left blank.");
         }
         
    	   if(id.equals(" "))
    		 throw new IllegalArgumentException("Flight ID cannot be left blank.");       
         // Do not allow origin and destination to be the same. 
         if(origin.equals(destination)){
            throw new IllegalArgumentException("Flight origin and destination cannot be the same.");
         }
         
         // Do not allow origins or destinations that do not exist to be set to a flight
         if(!airports.containsKey(origin)){
            throw new IllegalArgumentException("Origin: " + origin + ", does not exist.");
         }
        
         if(!airports.containsKey(destination)){
            throw new IllegalArgumentException("Destination: " + destination + ", does not exist.");
         }
         
         // Do not allow a flight to be created for an airline that does not exist
         if(!airlines.containsKey(name)){
            throw new IllegalArgumentException("Airline " + name + " does not exist.");
         }
         
         // Do not allow a flight to be created if a flight with the same id exists
         for(String airline: airlines.keySet()){
         
            if(airlines.get(airline).flightExists(id)){
               throw new IllegalArgumentException("Flight " + id + " already exists.");
            }
         }
                 
         // Do not allow flights to be created before the current date
         GregorianCalendar currentDate = new GregorianCalendar();
         GregorianCalendar flightDate = new GregorianCalendar(year, month, day);
         
         if(flightDate.before(currentDate)){
            throw new IllegalArgumentException("Date "+year+"-"+month+"-"+day+" has already passed.");
         }
         
         // Create flight
         airlines.get(name).addFlight(id, origin, destination, year, month, day);
         
      }
      
      // Create a new Section - CBC
      public void createSection(String airline, String id, int rows, int columns, SeatClass sClass){
         
         // Make sure the airline exists
         if(!airlines.containsKey(airline)){
            throw new IllegalArgumentException("Airline: " + airline + ", does not exist.");
         }
         
         // Make sure the flight exists
         if(!airlines.get(airline).flightExists(id)){
               throw new IllegalArgumentException("Flight " + id + " does not exist.");
         }
         
         // Make sure the SeatClass does not exist
         if(airlines.get(airline).getFlight(id).sectionExists(sClass)){
               throw new IllegalArgumentException("Seat class " + sClass + " already exists on " + airline + " flight " + id +".");
         }
         
         // Make the seat class
         airlines.get(airline).getFlight(id).addFlightSection(rows, columns, sClass);
         
      }
      
      // Find available flights
      public void findAvailableFlights(String origin, String destination){
         
          // Do not allow origins or destinations that do not exist to be set to a flight
         if(!airports.containsKey(origin)){
            throw new IllegalArgumentException("Origin: " + origin + ", does not exist.");
         }
        
         if(!airports.containsKey(destination)){
            throw new IllegalArgumentException("Destination: " + destination + ", does not exist.");
         }
         // Create a list for holding any flights found
         LinkedList<Flight> sameFlights = new LinkedList<Flight>();
         for(String airline: airlines.keySet()){
            
               for(Flight flight: airlines.get(airline).getSameFlights(origin, destination)){
                  sameFlights.add(flight);
               
               }
               
         }
         if(sameFlights.size() > 0){
         
            System.out.println("\n\nBelow are the available flights traveling from " + origin + " to " + destination + ".\n");

            for(Flight flight: sameFlights){
               flight.toStrings();
            }
         }else{
            System.out.println("There are no available flights from " + origin + " to " + destination + ".");
         }
      }
      
      // Book seat - AV
       public void bookSeat(String airline, String flight, SeatClass sClass, int row, char column){
           column = Character.toUpperCase(column);
           //Check if airline exists
           if(!airlines.containsKey(airline))
            throw new IllegalArgumentException("Airline " + airline + " does not exist.");
              
           //check if flight exists within airline
           if(!airlines.get(airline).flightExists(flight))
            throw new IllegalArgumentException("Flight " + flight + " does not exist on airline " + airline +".");
            
           //check if section exists within flight within airline
           if(!airlines.get(airline).getFlight(flight).sectionExists(sClass))
            throw new IllegalArgumentException("Seat class " + sClass + " does not exist on " + airline + " flight " + flight+".");
           
           //Check if seat Exists
           if(!airlines.get(airline).getFlight(flight).getFlightSection(sClass).seatExists(row,column))
               throw new IllegalArgumentException("Seat "+row+column+" does not exist in " + sClass + " class on flight "+ flight);
            
           //check if seat is already booked
           if(airlines.get(airline).getFlight(flight).getFlightSection(sClass).getSeat(row, column).isBooked())
            throw new IllegalArgumentException("Seat "+row+column+" is already booked.");
      	 
           airlines.get(airline).getFlight(flight).getFlightSection(sClass).bookSeat(row, column);

      }
      
      // Display system details - CBC
      public void displaySystemDetails(){
         // Displays attribute values for ALL OBJECTS (Airports, Airlines, Flights, ? - FlightSections, ? - Seats)
         
         int airportsPerRow = 0;
         // Print all airports, six per row
         System.out.println("\n\nAirports");
         System.out.println("----------------------------------------------------------------------------------------------");
         
         for(String airport: airports.keySet()){
            if(airportsPerRow < 5){
               System.out.print(airport + "\t");
               airportsPerRow++;
            }else{
               System.out.println(airport);
               airportsPerRow = 0;
            }  
         }
         
         // Print all Airlines, flights, flightSections, and Seats
         System.out.println("\n");
         System.out.println("\n\nAirlines");
         System.out.println("----------------------------------------------------------------------------------------------");
         
         for(String airline: airlines.keySet()){
            airlines.get(airline).toStrings();
         }
      }
      
      
      
}