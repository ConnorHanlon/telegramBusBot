package commands.bus_commands;

//import commands.bus_commands.BusCommand;
import TwinCitiesTransitSchema.NextTripDepartures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import http_request_tools.HTTPRequests;

import java.util.Arrays;
import java.util.List;

public class GetDepartureTimesCommand {

 public static String execute(String request) {
   try {
     String formattedRequest = formatRequest(request);
     String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     JsonParser jsonParser = new JsonParser();
     JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
     NextTripDepartures[] responseArray = gson.fromJson(jsonArray, NextTripDepartures[].class);
     String formattedResponse = formatResponse(responseArray);
     return formattedResponse;
   } catch (Exception e) {
     System.out.println("Failure: getDepartures failed to open URL.");
     e.printStackTrace();
   }
   return null;
 }

 private static String formatResponse(NextTripDepartures[] responses) {
   List<NextTripDepartures> responseList = Arrays.asList(responses);
   StringBuffer formattedResponse = new StringBuffer();
   formattedResponse.append("Route Number ");
   formattedResponse.append(responseList.get(0).getRoute());
   formattedResponse.append(" Direction: ");
   formattedResponse.append(responseList.get(0).getRouteDirection());
   formattedResponse.append("%0A%0A");
   formattedResponse.append(" Times of Arrival:%0A");
   for (NextTripDepartures response : responseList) {
      formattedResponse.append(response.getDepartureText());
      formattedResponse.append("%0A");
   }
   return formattedResponse.toString().replaceAll("\\s+", "%20");
 }

 /** The format of the url for getting directions is
     "http://svc.metrotransit.org/NexTrip/Directions/request/?format=json"
 **/
 private static String formatRequest(String request) {
   StringBuilder formatted = new StringBuilder(request);
//   formatted.append(request);
   formatted.append("?format=json");
   return formatted.toString();
 }
}
