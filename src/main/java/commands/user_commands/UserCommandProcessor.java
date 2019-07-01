package commands.user_commands;

import java.util.Arrays;

/**
 * Controls the commands being executed.
 * @author Connor Hanlon
 */
public class UserCommandProcessor {

  /**
   * Determines the user's command, and calls the corresponding bus command
   * which returns a response. If a command is not recognized, then the
   * response is formatted to return Unrecognized command.
   */
  public static String processCommand(String userRequest) {
    String[] request = userRequest.split("\\s+");
    String[] commandArguments = Arrays.copyOfRange(request, 1, request.length);
    String command = request[0];
    String response;
    /**
    Add /stats -> gets number of users, number of commands processed from db
    /setSchedule -> sets up times to send notifications to a user for a specified
                    route
    */
    switch (command) {
      case "/directions":
        response = GetDirectionsCommand.execute(commandArguments);
        break;
      case "/departures":
        response = GetDepartureTimesCommand.execute(commandArguments);
        break;
      case "/stops":
        response = GetStopsCommand.execute(commandArguments);
        break;
      default:
        response = "Unrecognized command: please resend command with appropriate inputs";
        break;
    }
    // formats the message so that spaces are replaced with %20, making it
    // compatible to send to a Telegram user.
    return response.replaceAll("\\s+", "%20");
  }
}
