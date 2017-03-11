import java.util.HashMap;
import java.util.*;

/*
   Christian Chmielewski
   Avery VanKirk
   
   Flight - Class to track a flight's information
*/

public class Flight{
   
   // Data encapsulation for Flight: Track FlightSection, origin, destination , id, and date
   private HashMap<SeatClass, FlightSection> seatBreakdown; 
   private String origin;
   private String destination;
   private String id;
   private GregorianCalendar date;
   

   // Constructor
   public Flight(String origin, String destination, String id, int year, int month, int day){
      this.origin = origin;
      this.destination = destination;
      this.id = id;
      this.date = new GregorianCalendar(year, month, day); //depreciated method. suppressing
      this.seatBreakdown = new HashMap<SeatClass, FlightSection>();
   }
   
   
   // Setter Methods
   
   public void addFlightSection(int rows, int columns, SeatClass sClass){
   
	       this.seatBreakdown.put(sClass, new FlightSection(rows, columns, sClass));
   }
   
   public FlightSection getFlightSection(SeatClass sClass){
      return seatBreakdown.get(sClass);
   }
   
   // Check to see if a FlightSection exists
   public boolean sectionExists(SeatClass sClass){
      
      if(!this.seatBreakdown.containsKey(sClass)){
      
         return false;
         
      }else{
      
         return true;
         
      }
     
   }
   
   
   // Compare origin and destination of two flights - CBC
   public boolean sameFlightPath(String origin, String destination){
      if((this.origin.equals(origin)) && (this.destination.equals(destination))){
         return true;
      }else{
         return false;
      }
   }
   
   // Print all flight, flightSection, and seat info
   public void toStrings(){
      System.out.println("\t\t Flight: " + id + "\t Traveling from " + origin + " to " + destination + " on " + date.get(Calendar.MONTH) + "/" + date.get(Calendar.DAY_OF_MONTH)+ "/" + date.get(Calendar.YEAR));
      System.out.println("\t\t\t X = booked, O = open\n");
      for(SeatClass sClass: seatBreakdown.keySet()){
         seatBreakdown.get(sClass).toStrings();
      }
      
   }
}