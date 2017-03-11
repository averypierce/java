
/*
   Christian Chmielewski
   Avery VanKirk
   
   FlightSection - Class to track a flight's flight sections
*/

public class FlightSection{
   
   // Data encapsulation for FlightSection: maintains rows/columns for a given section
   int rows;
   int columns;
   Seat[][] seatArray;
   SeatClass sClass;
   int seatsRemaining;
   
   // How to store/track seats?
   
   // Constructor
   public FlightSection(int rows, int columns, SeatClass sClass){

	  
      this.rows = rows;
      this.columns = columns;
      this.sClass = sClass;
      this.seatsRemaining = rows*columns;
      this.seatArray = new Seat[rows][columns];
      
      // Make all seats for the flight section
      for (int i = 0; i < rows; i++){ 
         for (int j = 0; j < columns; j++){
    		  this.seatArray[i][j] = new Seat(i,Character.toChars(65 + j)[0]);
    	  }
      }	
   }
   
   // Check for if the section has available seats
   
   
   public boolean hasAvailableSeats(){
       if(seatsRemaining > 0)
    	   return true;
       else
    	   return false;
   }
   
   // Book a seat
 //I figure the Counter is probably faster than Iterating through all the seats every time
   public void bookSeat(int row, char column){
      seatsRemaining--; //decrement
      int convertedColumn = (column - 'A');  //character math. 'A' has value 65. by subtracting 65, seat A maps to index[0]
      seatArray[row-1][convertedColumn].book();
   
   }
   
   public Seat getSeat(int row, char column){
      int convertedColumn = (column - 'A');
      return seatArray[row-1][convertedColumn];
   }
   public boolean seatExists(int row, char column){
	  int convertedColumn = (column - 'A');
	  if(row <= this.rows && convertedColumn <= columns)
		  return true;
	  else
		  return false;
   }
   // Print Flight Section and seat data
   public void toStrings(){
   
      System.out.println("\t\t\t" + sClass + "\t\t\t\t");
      
      for(int i = 0; i < this.rows; i++){
         System.out.print("\n\t\t\t\t");
         for(int j = 0; j < this.columns; j++){
            this.seatArray[i][j].toStrings();
         }
      
      }
      System.out.println("\n");
   }
}