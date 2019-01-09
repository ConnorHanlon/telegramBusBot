package commands.user_commands;

/**
 * Static class used to convert a direction to a direction identifier recognized by the Twin
 * Cities Metro Transit API.
 *
 * @author Connor Hanlon 1/3/2019
 */
public class RouteDirections {


 /**
  *  Static method that returns the Twin Cities Metro Transit identifier for a desired direction.
  *
  *  The directions recognized are:
  *  South : 1
  *  East : 2
  *  West : 3
  *  North : 4 (Default direction)
  */
 public static String getDirectionID(String direction) {
   String id;
   switch (direction) {
     case "South":
       id = "1";
       break;
     case "East":
       id = "2";
       break;
     case "West":
       id = "3";
       break;
     default:  // North
       id = "4";
       break;
   }
   return id;
 }

}
