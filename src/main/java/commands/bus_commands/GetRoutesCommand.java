package commands.bus_commands;

import TwinCitiesTransitSchema.NextTripRoute;

import java.util.Arrays;
import java.util.List;

//public class GetRoutesCommand extends BusCommand<NextTripRoute> {
public abstract class GetRoutesCommand extends BusCommand{

//  @Override
  public String formatResponse(NextTripRoute[] responses) {
    List<NextTripRoute> responseList = Arrays.asList(responses);
    StringBuffer formattedResponse = new StringBuffer();
    for (NextTripRoute response : responseList) {
      formattedResponse.append("Route Number: " + response.getRoute() + "\t" +  " Description: " + response.getDescription() + "\n");
    }
    return formattedResponse.toString();
  }

  @Override
  public String formatRequest(String request) {
    return "Routes?format=json";
  }

}
