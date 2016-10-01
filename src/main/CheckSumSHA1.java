/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * http://www.rgagnon.com/javadetails/java-0416.html
 */
package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ingvalle
 */
public class CheckSumSHA1 {

    public static void main(String[] args) {
        try {
            String datafile = "/Users/ingvalle/Downloads/ticket.xml";
            String checkSHA1Sum = getSHA1Checksum(datafile);
            System.out.println("checkSum: " + checkSHA1Sum);
        } catch (NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(CheckSumSHA1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static byte[] createChecksum(String fileName) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        final InputStream is = new FileInputStream(fileName);
        final byte[] buffer = new byte[1024];
        final MessageDigest digest = MessageDigest.getInstance("SHA1");
        int size;
        while ((size = is.read(buffer)) != -1) {
            digest.update(buffer, 0, size);
        }
        return digest.digest();
    }

    private static String getSHA1Checksum(String fileName) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        final byte[] bytes = createChecksum(fileName);
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            result += Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1);
        }

        return result;
    }

}
