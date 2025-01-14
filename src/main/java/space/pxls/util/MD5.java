package space.pxls.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String compute(String input) {
        StringBuilder sb = new StringBuilder();
        input += String.valueOf(System.currentTimeMillis());
        try {
            for (byte b : MessageDigest.getInstance("md5").digest(input.getBytes(StandardCharsets.UTF_8))) {
                sb.append(String.format("%02x", b));
            }
            return sb.length() > 32 ? sb.substring(0, 32) : sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
