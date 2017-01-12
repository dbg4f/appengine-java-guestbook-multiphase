package ee.dbg4f.iot.hub.gae;

import com.google.appengine.repackaged.com.google.api.client.util.Base64;

/**
 * Created by dmitri on 12.01.17.
 */
public class Base64Text {

    private byte[] bytes;
    private String encoded;

    public Base64Text(byte[] bytes) {
        this.bytes = bytes;
        encoded = new String(Base64.encodeBase64(bytes));
    }

    public Base64Text(String encoded) {
        this.encoded = encoded;
        bytes = Base64.decodeBase64(encoded);
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getEncoded() {
        return encoded;
    }
}
