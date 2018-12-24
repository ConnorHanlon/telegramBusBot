import commands.bus_commands.BusCommandProcessor;
import commands.telegram_commands.TelegramCommandProcessor;
import org.telegram.telegrambots.api.objects.Update;
import java.io.IOException;

public class CommandProcessor {

 public void ProcessCommand(Update update) {
   if (!update.hasMessage()) {
     //  handle error, send message to telegram
   }
   // StringBuilder sb = new StringBuilder(update.getMessage().getChatID.toString());
   String chatID = update.getMessage().getChatId().toString();
   String userRequest = update.getMessage().getText();
   // StringBuilder userRequestBuilder = new StringBuilder(chatID);
   // userRequestBuilder.append(" ");
   // userRequestBuilder.append(userText)
   // String userRequest = userRequestBuilder.toString();
   try {
     String busResponse = BusCommandProcessor.ProcessCommand(userRequest);
     TelegramCommandProcessor.ProcessCommand(chatID, busResponse);
   } catch (IOException e) {
     e.printStackTrace();
   }
}
