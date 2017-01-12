package ee.dbg4f.iot.hub.gae;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by dmitri on 1.01.17.
 */
public class ConfigServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ConfigServlet.class.getName());


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String key = req.getParameter("configKey");
        String value = req.getParameter("configValue");

        UserService userService = UserServiceFactory.getUserService();

        User user = userService.getCurrentUser();

        if (user != null && user.getUserId() !=  null) {

            // TODO: check user name

            if (key != null) {
                AppServicesFactory.getAppSettings().update(key, value);
            }

            log.info("User " + user.getUserId() + " changed " + key + "=" + value   );

            resp.sendRedirect("/config.jsp");
        }
        else {
            resp.sendError(403, "Not authorized");
        }



    }

}