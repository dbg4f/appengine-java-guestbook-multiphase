package ee.dbg4f.iot.hub.gae;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author bogdel on 10/11/16.
 */
public class TelemetryServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String sample = req.getParameter("sample");

        if (sample != null) {

            TelemetryEntry telemetryEntry = new TelemetryEntry("sample", null, "text", sample);

            ObjectifyService.ofy().save().entity(telemetryEntry).now();


        }


        

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

        if (user != null) {



        }




    }
}
