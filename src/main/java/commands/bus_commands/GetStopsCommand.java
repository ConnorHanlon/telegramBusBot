package commands.bus_commands;

import TwinCitiesTransitSchema.TextValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import http_request_tools.HTTPRequests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class GetStopsCommand {

  public static String execute(String request) {
    String returnResponse;
    try {
      String formattedRequest = formatRequest(request);
      String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
      TextValuePair[] responseArray = gson.fromJson(jsonArray, TextValuePair[].class);
      String formattedResponse = formatResponse(responseArray);
      returnResponse = formattedResponse;
    } catch (IllegalArgumentException e) {
      returnResponse = "One or more inputs missing from the command. Please re-enter command followed by a stop ID and one of the following directions: North, South, East, West.";
    }
    return returnResponse;
  }

  private static String formatResponse(TextValuePair[] responses) {
    if(responses.length == 0) {
      return "Unrecognized stop or direction. Please re-enter command followed by a stop ID and one of the following directions: North, South, East, West.";
    }
    List<TextValuePair> responseList = Arrays.asList(responses);
    StringBuffer formattedResponse = new StringBuffer();
    formattedResponse.append("Stop Name:%0A");
    for (TextValuePair response : responseList) {
      formattedResponse.append(response.getText());
      formattedResponse.append("%0A");
    }
    return formattedResponse.toString();
  }

  private static String formatRequest(String request) {
    String[] arguments = request.split("\\s+");
    if(arguments.length != 2) {
      throw new IllegalArgumentException();
    }
    String stop_id = arguments[0];
    StringBuilder formatted = new StringBuilder("Stops/");
    formatted.append(stop_id);
    formatted.append("/");
    formatted.append(RouteDirections.getDirectionID(arguments[1]));
    formatted.append("?format=json");
    return formatted.toString();
  }
}
