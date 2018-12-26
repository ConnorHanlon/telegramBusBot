package http_request_tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** A class that makes the HTTP requests to both Metro Transit and Telegram.**/
public class HTTPRequests {

  public static String makeMetroTransitRequest(String request) {
    String response;
    try {
      URL url = URLCreator.makeMetroTransitURL(request);
      response = makeHTTPRequest(url);
    } catch (IOException e) {
      e.printStackTrace();
      response = "Failed to make MetroTransitRequest";
    }
    return response;
  }

  private static String makeHTTPRequest(URL url) throws IOException {
    HttpURLConnection con = (HttpURLConnection)url.openConnection();
    con.setRequestMethod("GET");
    BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();
    while ((inputLine = input.readLine()) != null) {
        response.append(inputLine);
    }
    input.close();
    return response.toString();
  }

  public static void sendToTelegramUser(String chat_id, String messageToUser) throws IOException {
    StringBuilder message = new StringBuilder(chat_id);
    message.append("&text=");
    message.append(messageToUser);
    URL url = URLCreator.makeTelegramUserURL(message.toString());
  }
}
