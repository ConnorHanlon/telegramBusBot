package http_request_tools;

import java.io.IOException;
import java.net.URL;

public class URLCreator {
  private static final String BUS_URL = "http://svc.metrotransit.org/NexTrip/";
  private static final String TELEGRAM_SEND_MESSAGE_URL_BEGIN = "https://api.telegram.org/bot";
  private static final String TELEGRAM_SEND_MESSAGE_URL_END = "/sendMessage?chat_id=";

    public static URL makeMetroTransitURL(String userRequestInfo) throws IOException {
      URL url = new URL(BUS_URL.concat(userRequestInfo));
      return url;
    }

    public static URL makeTelegramUserURL(String messageToUser) throws IOException {
      String partialTelegramURL = makeTelegramSendMessagePartialURL();
      URL url = new URL(partialTelegramURL.concat(messageToUser));
      return url;
    }

    private static String makeTelegramSendMessagePartialURL() {
      String token = System.getenv("token");
      StringBuilder partialURL = new StringBuilder(TELEGRAM_SEND_MESSAGE_URL_BEGIN);
      partialURL.append(token);
      partialURL.append(TELEGRAM_SEND_MESSAGE_URL_END);
      return partialURL.toString();
    }
}
