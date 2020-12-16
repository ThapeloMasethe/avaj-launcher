package za.co.tmasethe.util;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

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

        int bytes = fileInputStream.read(content);
        fileInputStream.close();
        this.fileContent = new String(content, StandardCharsets.UTF_8);

        // Static getInstance method is called with hashing MD5.
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(this.fileContent.getBytes());

        // Convert message digest into hex value.
        byte[] digest = messageDigest.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

        // Check if if the contents are the same.
        if (!hash.equals(md5Hash)) {
            throw new Exception("ERROR: The hashed value does not match content of your input file!");
        }
    }
}
