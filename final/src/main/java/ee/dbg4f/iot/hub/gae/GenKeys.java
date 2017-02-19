package ee.dbg4f.iot.hub.gae;

import java.util.Random;

/**
 * Created by dmitri on 12.01.17.
 */
public class GenKeys {

    public static void main(String[] args) throws Exception {

        byte[] bytes = new Base64Text("HpqEnAbNHMkHf6F10+HSNw==").getBytes();
        for (int i=0; i<bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
        System.out.println("");

        bytes = new Base64Text("HpqEnAbNHMkHf6F10-HSNw==").getBytes();
        for (int i=0; i<bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }

        System.out.println("");

        byte key[] = new byte[16];

        generateAndShow(key);

        generateAndShow(key);

        decode("i2FcCFdj6TgqZyqTaDQK/g==", "aUzcCjsw1uXA5DIYTfJLrA==", "n/HL/nA3MnyoIxagH2m54Q==");
        decode("i2FcCFdj6TgqZyqTaDQK/g==", "aUzcCjsw1uXA5DIYTfJLrA==", "w1jSXfbsZaBUDoFJzvGxJQ==");

    }

    private static void generateAndShow(byte[] key) {
        new Random(System.currentTimeMillis()).nextBytes(key);

        for (byte v : key) {
            System.out.print(String.format("%02x ", (int)((0xFF)& v)));
        }

        System.out.println("new Base64Text(key) = " + new Base64Text(key).getEncoded());
    }

    private static void decode(String key, String iv, String encoded) throws Exception {
        AES aes = new AES(new Base64Text(key), new Base64Text(iv));
        String res = aes.decrypt(new Base64Text(encoded));
        System.out.println("res = " + res);
        System.out.println("res = " + res.trim());
        Base64Text res2 = aes.encrypt(align("{\"test\":123}", 16));
        System.out.println("res2 = " + res2.getEncoded());
    }

    private static String align(String s, int length) {

        int len = s.length();

        int remain = len % length;

        if (remain != 0) {
            s += new String(new char[length - remain]);
        }


        return s;
    }

}
