package me.bphilip.identicon;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

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
            String string = "shameless_plug_for_getblueberry.com";
            byte[] array = md.digest(string.getBytes(Charset.forName("UTF-8")));
            StringBuilder sb = new StringBuilder();
            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        }
        catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
