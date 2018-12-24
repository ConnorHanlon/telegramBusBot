package commands.bus_commands;

import java.util.Arrays;

/** Controlls the commands being executed. **/
public class BusCommandProcessor {

  /** Determines the user's command, and calls the corresponding bus command
      which returns a response. If a command is not recognized, then the
      response is formatted to return Unrecognized command. **/
  public static String ProcessCommand(String userRequest) {
    String[] request = userRequest.split("\\s+");
    String[] commandArgsArray = Arrays.copyOfRange(request, 1, request.length);
    String commandArguments  = String.join(" ", commandArgsArray);
    String command = request[0];
//    StringBuilder response = new StringBuilder();
    String response;
    switch (command) {
      case "/routes":
          GetRoutesCommand getRoutes = new GetRoutesCommand();
//          response.append(getRoutes.execute(commandArguments));
          response = getRoutes.execute(commandArguments);
          break;
      case "/directions":
          GetDirectionsCommand getDirections = new GetDirectionsCommand();
//          response.append(getDirections.execute(commandArguments));
          response = getDirections.execute(commandArguments);
          break;
      default:
//          response.append("Unrecognized command: please resend command with appropriate inputs");
          response = "Unrecognized command: please resend command with appropriate inputs";
          break;
    }
    return response;
//    return response.toString();
  }
}
