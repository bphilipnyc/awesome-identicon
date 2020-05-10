package me.bphilip.identicon;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class IdenticonTest {

    @Test
    public void test() throws IOException {

        Identicon identicon = new Identicon();
        String md5 = MD5();
        BufferedImage image = identicon.create(md5, 200);

        ImageIO.write(image, "PNG", new File("identicon.png"));
    }

    private String MD5() {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            String string = "some test string here";
            byte[] array = md.digest(string.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
