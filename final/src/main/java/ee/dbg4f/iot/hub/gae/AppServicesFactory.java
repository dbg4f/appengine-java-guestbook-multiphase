package ee.dbg4f.iot.hub.gae;

/**
 * Created by dmitri on 10.01.17.
 */
public class AppServicesFactory {

    public static AppSettings appSettings;

    public static AES cipher;

    static {

        appSettings = new AppSettings();


    }

    public static AppSettings getAppSettings() {
        return appSettings;
    }

    public static AES getCipher() {

        if (cipher == null) {
            cipher = new AES(appSettings.getValue("key"), appSettings.getValue("iv"));
        }

        return cipher;

    }


}
