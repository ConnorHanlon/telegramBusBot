package commands.bus_commands;

import java.util.Arrays;

/** Controlls the commands being executed. **/
public class BusCommandProcessor {

  /** Determines the user's command, and calls the corresponding bus command
      which returns a response. If a command is not recognized, then the
      response is formatted to return Unrecognized command. **/
  public static String processCommand(String userRequest) {
    String[] request = userRequest.split("\\s+");
    String[] commandArgsArray = Arrays.copyOfRange(request, 1, request.length);
    String commandArguments  = String.join(" ", commandArgsArray);
    String command = request[0];
    String response;
    switch (command) {
     case "/routes":
         response = GetRoutesCommand.execute(commandArguments);
         break;
      case "/directions":
          response = GetDirectionsCommand.execute(commandArguments);
          break;
      case "/departures":
          response = GetDepartureTimesCommand.execute(commandArguments);
          break;
      default:
          response = "Unrecognized command: please resend command with appropriate inputs";
          break;
    }
    return response;
  }
}
