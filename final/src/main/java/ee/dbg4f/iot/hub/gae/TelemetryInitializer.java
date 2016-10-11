package ee.dbg4f.iot.hub.gae;

import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author bogdel on 10/11/16.
 */
public class TelemetryInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectifyService.register(TelemetryEntry.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
