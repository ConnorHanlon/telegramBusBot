package com.commands.buscommands;

import com.google.gson.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.requests.tools.HTTPRequests;

/** abstract class for all bus commands. All commands have the same execute,
    however the way they format the request is different and classes that extend
    this one must implement their own formatRequest method. **/
public abstract class BusCommand<T> {

  public static List<T> execute(String request) {
    try {
      String formattedRequest = formatRequest(request);
      String reponse = HTTPRequests.makeMetroTransitRequest(formattedRequest);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      JsonParser jsonParser = new JsonParser();
      JsonArray jsonArray = (jsonParser.parse(response.toString())).getAsJsonArray();
      T[] responseArray = gson.fromJson(jsonArray, T[].class);
      return Arrays.asList(responseArray);
    } catch (IOException e) {
      System.out.println("Failure: getDepartures failed to open URL.");
      e.printStackTrace();
    }
    return null;
  }

  public abstract String formatRequest(String request);
}
