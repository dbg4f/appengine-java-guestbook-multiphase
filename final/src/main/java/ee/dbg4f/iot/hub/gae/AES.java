package ee.dbg4f.iot.hub.gae;

import com.google.appengine.repackaged.com.google.api.client.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by dmitri on 31.12.16.
 */
public class AES {

    private String key;
    private String iv;


    public AES(String key, String iv) {
        this.key = key;
        this.iv = iv;
        checkKey();
    }

    private void checkKey() {
        if (key == null || key.length() == 0 || iv == null || iv.length() == 0) {
            throw new IllegalStateException("Key and IV not assigned");
        }
    }

    public String encrypt(String text) throws Exception {
        checkKey();
        return new String(Base64.encodeBase64(encryptBytes(text)));
    }

    public String decrypt(String text) throws Exception {
        checkKey();
        return new String(decryptBytes(Base64.decodeBase64(text)));

    }

    public byte[] encryptBytes(String plainText) throws Exception {
        checkKey();
        Cipher cipher = getSunJCE();
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), getIv());
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    private IvParameterSpec getIv() throws UnsupportedEncodingException {
        return new IvParameterSpec(iv.getBytes("UTF-8"));
    }

    private SecretKeySpec getKey() throws UnsupportedEncodingException {
        return new SecretKeySpec(key.getBytes("UTF-8"), "AES");
    }

    public String decryptBytes(byte[] text) throws Exception{
        checkKey();
        Cipher cipher = getSunJCE();
        cipher.init(Cipher.DECRYPT_MODE, getKey(), getIv());
        return new String(cipher.doFinal(text),"UTF-8");
    }

    private Cipher getSunJCE() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        return Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    }


}
