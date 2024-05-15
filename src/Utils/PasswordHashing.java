/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHashing {
    
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;
    private static final String SECRET_KEY = "PBKDF2WithHmacSHA256"
;
    public static String hashPassword(String password) {
        char[] passwordChars = password.toCharArray();
        byte[] salt = generateSalt();
        
        PBEKeySpec spec = new PBEKeySpec(passwordChars, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(passwordChars, Character.MIN_VALUE);
        
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        String[] parts = hashedPassword.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);
        
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, hash.length * 8);
        
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_KEY);
            byte[] testHash = skf.generateSecret(spec).getEncoded();
            int diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error verifying password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

//    public static void main(String[] args) {
//        String password = "password123";
//        String hashedPassword = hashPassword(password);
//        System.out.println("Hashed password: " + hashedPassword);
//
//        // Kiểm tra mật khẩu có trùng khớp với mật khẩu gốc không
//        boolean isMatch = verifyPassword(password, hashedPassword);
//        System.out.println("Password match: " + isMatch);
//    }
}

