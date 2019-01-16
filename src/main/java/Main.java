import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commands.CommandProcessor;
import org.telegram.telegrambots.api.objects.Update;
import static spark.Spark.*;

public class Main {

    /**
     * Twin Cities Metro Transit API can be found at http://svc.metrotransit.org/
     *
     * Bot Name: TCMetroTransitBot
     * link: t.me/TCMetroTransitBot
     *
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
        getHerokuAssignedPort();
        post("/mtbotmain", (req, res) -> {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Update update = gson.fromJson(req.body(), Update.class);
            try {
              CommandProcessor.process(update);
            } catch (Exception e) {
              res.body("Failure");
              res.status(400);
              return "failure";
            }
            res.body("MetroTransitBot Server");
            res.status(200);
            return "MetroTransitBot Server";
        });
    }

}
