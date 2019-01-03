import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import commands.CommandProcessor;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import static spark.Spark.*;

public class Main {

    /**
     * Bot Name: TCMetroTransitBot
     * link: t.me/TCMetroTransitBot
     *
     * Use this token to access HTTP API:
     *
     * @param args
     */

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            Integer i =  Integer.parseInt(processBuilder.environment().get("PORT"));
            System.out.println(i);
            return i;
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
//       port(getHerokuAssignedPort());

//        post("/mtbotmain", (req, res) -> {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            Update update = gson.fromJson(req.body(), Update.class);
//            String chatID = getChatID(update);
//            if (!update.hasMessage()) {
//                HandleRequest.sendToTelegram(chatID, "Error: no message received");
//            } else {
//                handleCommand(chatID, getText(update));
//            }
//            System.out.println(req.headers());
//            res.body("MetroTransitBot Server");
//            res.status(200);
//            return "MetroTransitBot Server";
//        });
        port(80); // 80 88 443 8443
        post("/mtbotmain", (req, res) -> {
            // handleCommand("594886854", "/directions 2");
            // messages.sendMessage()
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Update update = gson.fromJson(req.body(), Update.class);
            try {
              CommandProcessor.process(update);
            } catch (Exception e) {
              res.body("Failure");
              res.status(400);
              return "failure";
            }
            res.body("success");
            res.status(200);
            return "success";
        });
    }

}
