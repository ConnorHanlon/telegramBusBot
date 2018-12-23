import org.telegram.telegrambots.api.objects.Update;

public class CommandProcessor {

 public void ProcessCommand(Update update) {
   if (!update.hasMessage()) {
     //  handle error, send message to telegram
   }
   String chat_id = update.getMessage().getChatId().toString();
   String user_text = update.getMessage().getText();
   String[] userRequest = text.split("\\s+");
   String userCommand = userRequest[0];
   String messageToUser;
   try {
     String busResponse = BusCommands.ProcessCommand(userCommand);
     TelegramCommands.ProcessCommand(chat_id, bus_response);
   } catch (IOException e) {
     e.printStackTrace();
   }
}
