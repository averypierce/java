/*
   Christian Chmielewski
   Avery VanKirk
   
   Seat - Class to track individual seat objects
*/

public class Seat{
   
   // Data encapsulation for Seat: row, column and booked/available status
   private boolean booked;
   private int row;
   private char column;
   
   // Constructor
   public Seat(int row, char column){
   
      // Start with booked == false because a seat must be created before it can be booked.
      this.row = row;
      this.column =  column;  
      this.booked = false;
   }
   
   // Getter methods
   public boolean isBooked(){
      return this.booked;
   }
   
   
   // Setter Method
   public void book(){
      this.booked = true;
   }
   
   // Print all seat Information
   public void toStrings(){
      if(this.booked){
         System.out.print("X");
      }else{
         System.out.print("O");
      }
   }
}