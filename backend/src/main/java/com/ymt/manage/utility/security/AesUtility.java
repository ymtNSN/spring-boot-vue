package com.ymt.manage.utility.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;


/**
 * AES/CBC/PKCS7Padding
 *
 * @author yaojunguang
 */

@Service
public class AesUtility {
    /**
     * 算法名称
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 加解密算法/模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    /**
     * IV值
     */
    private static final String IV = "0102030405060708";

    /**
     * userId加密key
     */
    public static final String USER_ID_KEY = "5a7daaf2e889c1ab23f5e988129e5884";

    /**
     * 加密
     */
    private volatile Cipher cipherEncrypt;

    /**
     * 解密
     */
    private volatile Cipher cipherDecrypt;

    /**
     * 加密方法
     *
     * @param sSrc 要加密的字符串
     * @param sKey 加密密钥
     * @return 加密结果
     * @throws Exception 异常
     */
    public synchronized byte[] encryptByte(String sSrc, String sKey) throws Exception {
        // 初始化cipher
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 初始化cipher
        if (cipherEncrypt == null) {
            cipherEncrypt = Cipher.getInstance(ALGORITHM_STR, "BC");
        }
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        byte[] keyBytes = alignedKey(sKey);
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
        //lastEncryptKey = sKey;
        return cipherEncrypt.doFinal(sSrc.getBytes());
    }


    /**
     * 加密为直接字符串
     *
     * @param sSrc 加密数据
     * @param sKey 密钥
     * @return 结果
     * @throws Exception 异常
     */
    public synchronized String encryptString(String sSrc, String sKey) throws Exception {
        // 初始化cipher
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 初始化cipher
        if (cipherEncrypt == null) {
            cipherEncrypt = Cipher.getInstance(ALGORITHM_STR, "BC");
        }
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        byte[] keyBytes = alignedKey(sKey);
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
        //lastEncryptKey = sKey;
        return Base64.encode(cipherEncrypt.doFinal(sSrc.getBytes()));
    }


    /**
     * 加密方法
     *Base64
     * @param sSrc 要加密的字符串
     * @param sKey 加密密钥
     * @return 加密结果
     */
    public synchronized String encrypt(String sSrc, String sKey) throws Exception {
        // 初始化cipher
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 初始化cipher
        if (cipherEncrypt == null) {
            cipherEncrypt = Cipher.getInstance(ALGORITHM_STR, "BC");
        }
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        byte[] keyBytes = alignedKey(sKey);
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
        //lastEncryptKey = sKey;
        byte[] encryptedText = cipherEncrypt.doFinal(sSrc.getBytes());
        return Base64.encode(encryptedText);
    }

    /**
     * 解密方法
     *
     * @param sSrc 要解密的字符串
     * @param sKey 解密密钥
     * @return 解密结果
     */
    public synchronized String decrypt(String sSrc, String sKey) throws Exception {
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 初始化cipher
        if (cipherDecrypt == null) {
            cipherDecrypt = Cipher.getInstance(ALGORITHM_STR, "BC");
        }
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        byte[] keyBytes = alignedKey(sKey);
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        // 初始化cipher
        cipherDecrypt.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes()));
        //lastDecryptKey = sKey;
        //先用base64解密
        byte[] encrypted = Base64.decode(sSrc);
        byte[] original = cipherDecrypt.doFinal(encrypted);
        return new String(original);
    }

    /**
     * 对齐key为16位
     *
     * @param sKey 需要处理的key
     * @return 处理后的字节流
     */
    private byte[] alignedKey(String sKey) {
        int base = 16;
        byte[] keyBytes = sKey.getBytes();
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        return keyBytes;
    }

    public static void main(String[] args) {
        try {
            System.out.println(URLDecoder.decode("x0nV5h8hr+iBrNfmbopzsg==", "utf-8"));
            AesUtility aesUtility = new AesUtility();
            System.out.println(aesUtility.decrypt("x0nV5h8hr+iBrNfmbopzsg==", USER_ID_KEY));

            System.out.println(aesUtility.encrypt("234829", USER_ID_KEY));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}