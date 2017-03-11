/*

   Christian Chmielewski
   Avery VanKirk
   
   SeatClass - enumerations of the three types of FlightSection
   
   
*/

public enum SeatClass { 

  FIRST, BUSINESS, ECONOMY;

  public String toString() {
    switch(this) {
      case FIRST:    return "first";
      case BUSINESS: return "business";
      case ECONOMY: return "economy";
      default:  return "economy";
    }

  }
}