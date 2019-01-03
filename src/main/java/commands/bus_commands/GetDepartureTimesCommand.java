package commands.bus_commands;

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
   String formattedResponse;
   try {
     String formattedRequest = formatRequest(request);
     String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     JsonParser jsonParser = new JsonParser();
     JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
     NextTripDepartures[] responseArray = gson.fromJson(jsonArray, NextTripDepartures[].class);
     formattedResponse = formatResponse(responseArray);
   } catch (IllegalArgumentException e) {
     formattedResponse = "The stop ID is missing from the command. Please re-enter command followed by the stop ID";
   }
   return formattedResponse;
 }

 private static String formatResponse(NextTripDepartures[] responses) {
   if(responses.length == 0) {
     return "The stop ID provided is not recognized by Twin Cities Metro Transit.";
   }
   List<NextTripDepartures> responseList = Arrays.asList(responses);
   StringBuffer formattedResponse = new StringBuffer();
   formattedResponse.append("Route Number ");
   formattedResponse.append(responseList.get(0).getRoute());
   formattedResponse.append(" Direction: ");
   formattedResponse.append(responseList.get(0).getRouteDirection());
   formattedResponse.append("%0A%0A");
   formattedResponse.append("Times of Arrival:%0A");
   for (NextTripDepartures response : responseList) {
      formattedResponse.append(response.getDepartureText());
      formattedResponse.append("%0A");
   }
   return formattedResponse.toString();
 }

 /** The format of the url for getting directions is
     "http://svc.metrotransit.org/NexTrip/Directions/request/?format=json"
 **/
 private static String formatRequest(String request) {
   String[] arguments = request.split("\\s+");
   if(arguments.length == 0) {
     throw new IllegalArgumentException();
   }
   StringBuilder formatted = new StringBuilder(arguments[0]);
   formatted.append("?format=json");
   return formatted.toString();
 }
}
