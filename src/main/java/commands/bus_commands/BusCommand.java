package commands.bus_commands;

import com.google.gson.*;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import http_request_tools.HTTPRequests;

/** abstract class for all bus commands. All commands have the same execute,
    however the way they format the request is different and classes that extend
    this one must implement their own formatRequest method. **/
public abstract class BusCommand<T> {

  public String execute(String request) {
    try {
      String formattedRequest = formatRequest(request);
      String response = HTTPRequests.makeMetroTransitRequest(formattedRequest);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (jsonParser.parse(response.toString())).getAsJsonArray();
      Type typeOfT = new TypeToken<T[]>(){}.getType();
      T[] responseArray = gson.fromJson(jsonArray, typeOfT);
      String formattedResponse = formatResponse(responseArray);
      return formattedResponse;
    } catch (Exception e) {
      System.out.println("Failure: getDepartures failed to open URL.");
      e.printStackTrace();
    }
    return null;
  }

  public abstract String formatResponse(T[] response);

  public abstract String formatRequest(String request);
}
