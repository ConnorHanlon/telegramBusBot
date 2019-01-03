package commands.bus_commands;

public class RouteDirections {

 public static String getDirectionID(String direction) {
   String id;
   switch (direction) {
     case "North":
       id = "4";
       break;
     case "South":
       id = "1";
       break;
     case "East":
       id = "2";
       break;
     case "West":
       id = "3";
       break;
     default:
       id = "0";
       break;
   }
   return id;
 }

}
