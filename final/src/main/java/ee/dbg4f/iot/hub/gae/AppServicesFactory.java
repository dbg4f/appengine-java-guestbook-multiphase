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
            cipher = new AES(new Base64Text(appSettings.getValue("key")), new Base64Text(appSettings.getValue("iv")));
        }

        return cipher;

    }


}
