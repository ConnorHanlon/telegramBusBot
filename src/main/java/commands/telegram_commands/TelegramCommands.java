package commands.telegram_commands;

import http_request_tools.HTTPRequests;
import java.io.IOException;

public class TelegramCommands {

  public static void send(String chat_id, String message) throws IOException {
    HTTPRequests.sendToTelegramUser(chat_id, message);
  }

}
