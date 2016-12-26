package ee.dbg4f.iot.hub.gae;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by dmitri on 26.12.16.
 */
@Entity
public class Config {
    @Id
    public String json;

    public Config() {
    }

    public Config(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "Config{" +
                "json='" + json + '\'' +
                '}';
    }
}
