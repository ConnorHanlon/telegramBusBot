package com.commands.buscommands;

import TwinCitiesTransitSchema.NextTripRoute;

public class GetRoutesCommand implements BusCommand<NextTripRoute> {

  @override
  public String formatRequest(String request) {
    return "Routes?format=json";
  }

}
