package commands;

import commands.bus_commands.BusCommandProcessor;
import commands.telegram_commands.TelegramCommands;
import org.telegram.telegrambots.api.objects.Update;
import java.io.IOException;

public class CommandProcessor {

  public static void process(Update update) {
    String chatID = update.getMessage().getChatId().toString();
    String userRequest = update.getMessage().getText();
    try {
      String messageToUser = BusCommandProcessor.processCommand(userRequest);
      TelegramCommands.send(chatID, messageToUser);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
