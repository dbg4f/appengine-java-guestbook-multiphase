package ee.dbg4f.iot.hub.gae;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitri on 1.01.17.
 */
public class ConfigServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String key = req.getParameter("configKey");
        String value = req.getParameter("configValue");

        //TODO: check credentials

        AppServicesFactory.getAppSettings().update(key, value);

        resp.sendRedirect("/config.jsp");

    }

}