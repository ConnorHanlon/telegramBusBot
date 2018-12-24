package com.command;

import org.telegram.telegrambots.api.objects.Update;

public class CommandProcessor {

 public void ProcessCommand(Update update) {
   if (!update.hasMessage()) {
     //  handle error, send message to telegram
   }
   // StringBuilder sb = new StringBuilder(update.getMessage().getChatID.toString());
   String chatID = update.getMessage().getChatId().toString();
   String userText = update.getMessage().getText();
   StringBuilder userRequestBuilder = new StringBuilder(chatID);
   userRequestBuilder.append(" ");
   userRequestBuilder.append(userText)
   String userRequest = userRequestBuilder.toString();
   // String[] userRequest = text.split("\\s+");
   // String userCommand = userRequest[0];
   // String messageToUser;
   try {
     String busResponse = BusCommandProcessor.ProcessCommand(userRequest);
     TelegramCommandProcessor.ProcessCommand(busResponse);
   } catch (IOException e) {
     e.printStackTrace();
   }
}
