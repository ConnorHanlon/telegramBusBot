package commands.bus_commands;

import TwinCitiesTransitSchema.NextTripRoute;

public class GetRoutesCommand extends BusCommand<NextTripRoute> {

  @Override
  public String formatRequest(String request) {
    return "Routes?format=json";
  }

}
