package commands.user_commands;

import TwinCitiesTransitSchema.TextValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import http_request_tools.HTTPRequests;

import java.util.Arrays;
import java.util.List;

/**
 * Command class responsible for determining all of the locations of the stops
 * that a desired route has.
 *
 * @author Connor Hanlon
 */
public class GetStopsCommand {

  /**
   *
   *  A request is made of the Twin Cities Metro Transit API using the Telegram user's provided information.
   *  The response from the API is formatted and returned.
   *
   * @param request user provided stop ID and direction
   * @return formatted response to be sent to the Telegram user.
   */
  public static String execute(String[] request) {
    String formattedResponse;
    try {
      String formattedRequest = formatRequest(request);
      String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (jsonParser.parse(response)).getAsJsonArray();
      TextValuePair[] responseArray = gson.fromJson(jsonArray, TextValuePair[].class);
      formattedResponse = formatResponse(responseArray);
    } catch (IllegalArgumentException e) {
      formattedResponse = "One or more inputs missing from the command. Please re-enter command followed by a route number and one of the following directions: North, South, East, West.";
    }
    return formattedResponse;
  }

  private static String formatResponse(TextValuePair[] responses) {
    // If a stop ID or direction is not recognized
    if(responses.length == 0) {  //
      return "Unrecognized stop or direction. Please re-enter command followed by a route number and one of the following directions: North, South, East, West.";
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


  private static String formatRequest(String[] request) {
    if(request.length < 2) {  // if stop ID and/or direction missing
      throw new IllegalArgumentException();
    }
    String stop_id = request[0];
    StringBuilder formatted = new StringBuilder("Stops/");
    formatted.append(stop_id);
    formatted.append("/");
    formatted.append(RouteDirections.getDirectionID(request[1]));
    formatted.append("?format=json");
    return formatted.toString();
  }
}
