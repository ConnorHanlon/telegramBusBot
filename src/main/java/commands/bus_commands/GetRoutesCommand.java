//package commands.bus_commands;
//
//import TwinCitiesTransitSchema.NextTripRoute;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonParser;
//import http_request_tools.HTTPRequests;
//
//import java.util.Arrays;
//import java.util.List;
//
////public class GetRoutesCommand extends BusCommand<NextTripRoute> {
//public class GetRoutesCommand {
//
//  public static String execute(String request) {
//    try {
//      String formattedRequest = formatRequest(request);
//      String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
//      Gson gson = new GsonBuilder().setPrettyPrinting().create();
//      JsonParser jsonParser = new JsonParser();
//      JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
//      NextTripRoute[] responseArray = gson.fromJson(jsonArray, NextTripRoute[].class);
//      String formattedResponse = formatResponse(responseArray);
//      return formattedResponse;
//    } catch (Exception e) {
//      System.out.println("Failure: getDepartures failed to open URL.");
//      e.printStackTrace();
//    }
//    return null;
//  }
//
//  private static String formatResponse(NextTripRoute[] responses) {
//    List<NextTripRoute> responseList = Arrays.asList(responses);
//    StringBuffer formattedResponse = new StringBuffer();
//    for (NextTripRoute response : responseList) {
//      formattedResponse.append("Route Number:" + response.getRoute() + " " +  " Description: " + response.getDescription() + "%0A");
//    }
//    String noSpacesResponse = formattedResponse.toString().replaceAll("\\s+", "%20");
//    return noSpacesResponse.replaceAll("'", "%20");
//  }
//
//  private static String formatRequest(String request) {
//    return "Routes?format=json";
//  }
//
//}
