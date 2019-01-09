package commands.user_commands;

import TwinCitiesTransitSchema.NextTripDepartures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import http_request_tools.HTTPRequests;

import java.util.Arrays;
import java.util.List;

/**
 * Command class responsible for determining the future departure times of a desired stop.
 *
 * @author Connor Hanlon
 */
public class GetDepartureTimesCommand {

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
            NextTripDepartures[] responseArray = gson.fromJson(jsonArray, NextTripDepartures[].class);
            formattedResponse = formatResponse(responseArray);
        } catch (IllegalArgumentException e) {
            formattedResponse = "The stop ID is missing from the command. Please re-enter command followed by the stop ID";
        }
        return formattedResponse;
    }

    /**
     * Formats the response from the Twin Cities Metro Transit API to return all future times of arrival.
     *
     * @param responses List of NextTripDepartures from the Twin Cities Metro Transit API which contains the times of arrival.
     */
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

    /**
     * Using the stop ID provided by the user, the tail end of the request URL is created.
     *
     * The format of the tail of the URL is {STOPID}?format=json
     *
     * @param request the stop ID provided by the user.
     * @return {STOPID}?format=json where {STOPID} is the placeholder for the user's provided stop ID.
     * @throws IllegalArgumentException thrown if the request is empty and no stop id provided
     */
    private static String formatRequest(String[] request) {
        if(request.length == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder formatted = new StringBuilder(request[0]);
        formatted.append("?format=json");
        return formatted.toString();
    }
}
