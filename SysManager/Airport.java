/*
   Christian Chmielewski
   Avery VanKirk
   
   Airport - A class to track an airport's information
*/

public class Airport{

   // Data encapsulation for Airport
   String airportCode;
   
   // Constructor
   public Airport(String airportCode){
		  this.airportCode = airportCode;
   }
   
   // Getter Method
   public String getAirportCode(){
      return this.airportCode;
   }
   
  
}