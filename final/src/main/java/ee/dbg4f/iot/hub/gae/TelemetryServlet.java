package ee.dbg4f.iot.hub.gae;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.JsonSyntaxException;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author bogdel on 10/11/16.
 */
public class TelemetryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(TelemetryServlet.class.getName());


    public static boolean isJSONValid(String jsonInString) {
        try {
            new Gson().fromJson(jsonInString, Object.class);
            return true;
        } catch(JsonSyntaxException ex) {
            log.log(Level.WARNING, "Json " + jsonInString + " not valid", ex);
            return false;
        }
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
/*
        String sample = req.getParameter("sample");

        if (sample != null) {

            TelemetryEntry telemetryEntry = new TelemetryEntry("sample", null, "text", sample);

            ObjectifyService.ofy().save().entity(telemetryEntry).now();


        }
        */

        String encrypted = req.getParameter("encrypted");

        if (encrypted != null) {

            try {
                String text = AppServicesFactory.getCipher().decrypt(new Base64Text(encrypted));

                text = text.trim();

                if (isJSONValid(text)) {

                    TelemetryEntry telemetryEntry = new TelemetryEntry("sample", null, "text", text);

                    ObjectifyService.ofy().save().entity(telemetryEntry).now();
                }
                else {
                    String errorTextJsonNotValid = encrypted + ", text=" + text + " is not valid ";
                    log.log(Level.WARNING, errorTextJsonNotValid);
                    resp.sendError(400, errorTextJsonNotValid);
                }


            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to decrypt and save " + encrypted + " " + e.getMessage(), e);
                resp.sendError(403);
            }

        }


        

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

        if (user != null) {



        }




    }
}
