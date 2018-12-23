import java.io.IOException;
import java.net.URL;

public class URLCreator {
  private static final String BUS_URL = "http://svc.metrotransit.org/NexTrip/";
  private static final String TELEGRAM_UPDATE_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/getUpdates";
  private static final String TELEGRAM_SEND_MESSAGE_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/sendMessage?chat_id=";
  private static final String DEL_WEBHOOK_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/deleteWebhook";
  private static final String BOT_TOKEN = "526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA";
  private static final String SET_WEBHOOK_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/setWebhook?url=https://immense-island-48680.herokuapp.com/mtbotmain";
  private static final String WEBHOOK_INFO_URL = "https://api.telegram.org/bot526452962:AAHN2Eu_oCVHevipOgearrFLRMCt-jOPYjA/getWebhookInfo";


    public static URL makeMetroTransitURL(String userRequestInfo) {
      try {
        URL url = new URL(BUS_URL.concat(userRequestInfo));
        return url;
      } catch (IOException e){
        e.printStackTrace();
      }
      return null;
    }

    // public static URL makeTelegramUpdateURL(String userRequestInfo) {
    //   URL url = new URL(BUS_URL.concat(userRequestInfo));
    //   return url;
    // }
    //
    // public static URL
}
