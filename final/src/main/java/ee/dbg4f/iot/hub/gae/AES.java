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

    private Base64Text key;
    private Base64Text iv;

    public AES(Base64Text key, Base64Text iv) {
        this.key = key;
        this.iv = iv;
        checkKey();
    }

    private void checkKey() {
        if (key == null || key.getBytes().length == 0 || iv == null || iv.getBytes().length == 0) {
            throw new IllegalStateException("Key and IV not assigned");
        }
    }

    public Base64Text encrypt(String text) throws Exception {
        checkKey();
        return new Base64Text(encryptBytes(text.getBytes()));
    }

    public String decrypt(Base64Text text) throws Exception {
        checkKey();
        return new String(decryptBytes(text.getBytes()));

    }

    public byte[] encryptBytes(byte[] bytes) throws Exception {
        checkKey();
        Cipher cipher = getSunJCE();
        cipher.init(Cipher.ENCRYPT_MODE, getKey(), getIv());
        return cipher.doFinal(bytes);
    }

    private IvParameterSpec getIv() throws UnsupportedEncodingException {
        return new IvParameterSpec(iv.getBytes());
    }

    private SecretKeySpec getKey() throws UnsupportedEncodingException {
        return new SecretKeySpec(key.getBytes(), "AES");
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
