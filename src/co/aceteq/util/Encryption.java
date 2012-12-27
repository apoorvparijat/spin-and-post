/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.aceteq.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author apoorvparijat
 */
public class Encryption {
 
    public static String getSHA(String str,int loop){
        String sha = str;
        while(loop > 0){
            System.out.println(sha);
            sha = getSHA(sha);
            loop--;
        }
        return sha;
    }
    
    private static String convToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                        buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String getSHA(String text)
    {
        String sha = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes("iso-8859-1"), 0, text.length());
            sha1hash = md.digest();
            sha = convToHex(sha1hash);
        }catch(Exception e){
            
        }
        return sha;
    }

}
