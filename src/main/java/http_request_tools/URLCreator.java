package http_request_tools;

import java.io.IOException;
import java.net.URL;

public class URLCreator {
  private static final String BUS_URL = "http://svc.metrotransit.org/NexTrip/";
  private static final String TELEGRAM_SEND_MESSAGE_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/sendMessage?chat_id=";
  private static final String BOT_TOKEN = "526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA";
  private static final String SET_WEBHOOK_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/setWebhook?url=https://immense-island-48680.herokuapp.com/mtbotmain";


    public static URL makeMetroTransitURL(String userRequestInfo) throws IOException {
      URL url = new URL(BUS_URL.concat(userRequestInfo));
      return url;
    }

    public static URL makeTelegramUserURL(String messageToUser) throws IOException {
      URL url = new URL(TELEGRAM_SEND_MESSAGE_URL.concat(messageToUser));
      return url;
    }
}
