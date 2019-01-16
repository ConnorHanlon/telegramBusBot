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
 * Command class responsible for returning all directions of a given stop.
 *
 * @author Connor Hanlon
 */
public class GetDirectionsCommand {

  /**
   * A request is made of the Twin Cities Metro Transit API using the Telegram user's provided information.
   * The response from the API is formatted and returned.
   *
   * @param request the user provided route ID number
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
      formattedResponse = "The route number is missing from the command. Please re-enter command followed by the route number";
    }
    return formattedResponse;
  }

  private static String formatResponse(TextValuePair[] responses) {
    if(responses.length == 0) {
      return "The route number provided is not recognized by Twin Cities Metro Transit.";
    }
    List<TextValuePair> responseList = Arrays.asList(responses);
    StringBuffer formattedResponse = new StringBuffer();
    formattedResponse.append("Directions:%0A");
    for (TextValuePair response : responseList) {
      formattedResponse.append(response.getText());
      formattedResponse.append("%0A");
    }
    return formattedResponse.toString();
  }

  /** The format of the url for getting directions is
   "http://svc.metrotransit.org/NexTrip/Directions/request/?format=json"
   **/
  private static String formatRequest(String[] request) {
    if(request.length == 0) {
      throw new IllegalArgumentException();
    }
    StringBuilder formatted = new StringBuilder("Directions/");
    formatted.append(request[0]);
    formatted.append("?format=json");
    return formatted.toString();
  }

}
