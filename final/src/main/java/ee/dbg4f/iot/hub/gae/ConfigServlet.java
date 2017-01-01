package ee.dbg4f.iot.hub.gae;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.internal.LinkedTreeMap;
import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dmitri on 1.01.17.
 */
public class ConfigServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String key = req.getParameter("configKey");
        String value = req.getParameter("configValue");

        //TODO: check credentials

        Config config = ObjectifyService.ofy()
                .load()
                .type(Config.class)
                .first().now();

        Gson gson = new Gson();

        Type type = new TypeToken<Map<String, String>>(){}.getType();

        Map<String, String> map = new LinkedTreeMap<>();

        if (config != null) {
            map = gson.fromJson(config.json, type);
        }

        map.put(key, value);

        Config newConfig = new Config(gson.toJson(map));

        ObjectifyService.ofy().delete().entity(config).now();

        ObjectifyService.ofy().save().entity(newConfig).now();

        resp.sendRedirect("/config.jsp");

    }

}