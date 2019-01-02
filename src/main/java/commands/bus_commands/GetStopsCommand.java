package commands.bus_commands;

import TwinCitiesTransitSchema.NextTripRoute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import http_request_tools.HTTPRequests;

import java.util.Arrays;
import java.util.List;


public class GetStopsCommand {

  public static String execute(String request) {
    try {
      String formattedRequest = formatRequest(request);
      String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
      TextValuePair[] responseArray = gson.fromJson(jsonArray, TextValuePair[].class);
      String formattedResponse = formatResponse(responseArray);
      return formattedResponse;
    } catch (Exception e) {
      System.out.println("Failure: getDepartures failed to open URL.");
      e.printStackTrace();
    }
    return null;
  }

  private static String formatResponse(TextValuePair[] responses) {
    List<TextValuePair> responseList = Arrays.asList(responses);
    StringBuffer formattedResponse = new StringBuffer();
    formattedResponse.append("Directions:%0A");
    for (TextValuePair response : responseList) {
      formattedResponse.append(response.getText());
      formattedResponse.append("%0A");
    }
    return formattedResponse.toString().replaceAll("\\s+", "%20");
  }

  /** The format of the url for getting directions is
      "http://svc.metrotransit.org/NexTrip/Directions/request/?format=json"
  **/
  private static String formatRequest(String request) {
    String[] arguments = request.split("\\s+");
    String stop_id = arguments[0];
    if (arguments.length > 1) {
      direction = arguments[1];
    } else {
      direction = "All";
    }
    StringBuilder formatted = new StringBuilder("Stops/");
    formatted.append(stop_id);
    formatted.append(RouteDirections.getDirectionID(direction));
    formatted.append("?format=json");
    return formatted.toString();
  }
}
