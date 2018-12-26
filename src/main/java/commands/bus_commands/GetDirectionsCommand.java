package commands.bus_commands;

//import commands.bus_commands.BusCommand;
import TwinCitiesTransitSchema.TextValuePair;
import java.util.Arrays;
import java.util.List;

//public class GetDirectionsCommand extends BusCommand<TextValuePair> {
public class GetDirectionsCommand extends BusCommand {

  @Override
  public String formatResponse(TextValuePair[] responses) {
    List<TextValuePair> responseList = Arrays.asList(responses);
    StringBuffer formattedResponse = new StringBuffer();
    for (TextValuePair response : responseList) {
      formattedResponse.append("Direction:%20" + response.getText() + "%20-%20" + response.getValue() + "%0A");
    }
    return formattedResponse.toString();
  }

  /** The format of the url for getting directions is
      "http://svc.metrotransit.org/NexTrip/Directions/request/?format=json"
  **/
  @Override
  public String formatRequest(String request) {
    StringBuilder formatted = new StringBuilder("Directions/");
    formatted.append(request);
    formatted.append("?format=json");
    return formatted.toString();
  }

}
