package commands;

import commands.user_commands.UserCommandProcessor;
import commands.telegram_commands.TelegramCommands;
import org.telegram.telegrambots.api.objects.Update;
import java.io.IOException;

/**
 * Handles all commands from the user and sends a formatted message back to the user.
 *
 * @author Connor Hanlon
 */
public class CommandProcessor {

  public static void process(Update update) {
    String chatID = update.getMessage().getChatId().toString();
    // Update DB information ie add user to the database if not
    String userRequest = update.getMessage().getText();
    try {
      String messageToUser = UserCommandProcessor.processCommand(userRequest);
      TelegramCommands.send(chatID, messageToUser);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
