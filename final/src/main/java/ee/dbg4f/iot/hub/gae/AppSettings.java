package ee.dbg4f.iot.hub.gae;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.internal.LinkedTreeMap;
import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import com.googlecode.objectify.ObjectifyService;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by dmitri on 10.01.17.
 */
public class AppSettings {

    private Gson gson = new Gson();

    private Type mapType = new TypeToken<Map<String, String>>(){}.getType();

    public String getValue(String key) {
        return loadSettings().get(key);
    }

    public String toString() {
        return loadSettings().toString();
    }

    private Map<String, String> loadSettings() {
        Config config = getConfig();

        if (config != null) {
            return gson.fromJson(config.json, mapType);
        }
        else {
            return new LinkedTreeMap<>();
        }

    }

    private Config getConfig() {
        return ObjectifyService.ofy()
                    .load()
                    .type(Config.class)
                    .first().now();
    }

    public void update(String key, String value) {

        Map<String, String> map = loadSettings();

        map.put(key, value);

        Config newConfig = new Config(gson.toJson(map));

        replaceConfig(newConfig);
    }

    private void replaceConfig(Config newConfig) {

        //ObjectifyService.ofy().delete().entity(config).now();
        ObjectifyService.ofy().clear();

        ObjectifyService.ofy().save().entity(newConfig).now();
    }
}
