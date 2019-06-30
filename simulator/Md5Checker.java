package simulator;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

/**
 * MD5Checker Class
 * 
 * @author  THapelo Masethe
 * @since   2019-06-29
 * @version 1.0
 */
public class Md5Checker {

    private String fileContent;
    
    /**
     * Check if the MD5 hash matches the content of the 
     * given file.
     * 
     * @param fileName The name of the file.
     * @param md5Hash MD5 hash value. 
     * @throws IOException if the file object is not created. 
     * @throws NoSuchAlgorithmException if the algorithm is incorrect.
     * @throws Exception if the file contents are wrong.
     */
    public void checkMd5Hashing(String fileName, String md5Hash) throws IOException, NoSuchAlgorithmException, Exception {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] content = new byte[(int) file.length()];
        
        fileInputStream.read(content);
        fileInputStream.close();
        this.fileContent = new String(content, "UTF-8");

        // Static getInstance method is called with hashing MD5.
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(this.fileContent.getBytes());

        // Convert message digest into hex value.
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

        // Check if if the contents are the same.
        if (!hash.equals(md5Hash)) {
            throw new Exception("Contents do not match!");
        }
    }
}