/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package securityproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.xml.*;

/**
 *
 * @author leele
 */
public class Hashing {

    public static void getHash(File inputFile, File outputFile, String ALGO) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGO);
            messageDigest.digest(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(digestedBytes);
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {

        }

    }

}
