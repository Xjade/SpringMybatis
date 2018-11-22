package com.Utils;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/11/20
 *Time:13:58
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.rpc.ServiceException;

public class TripleDesUtil {


    private static final String Algorithm = "DESede";
    private final static Logger log = LoggerFactory
            .getLogger(TripleDesUtil.class);




//    加密后获得的传如果要给别人 或者打印出来区分  需要用base64转换  转换后 要解密的话也需要用base64转之后再解密

//    前两个方法适用于24位秘钥加密
    /**
     *
     * @param src 加密内容
     * @param key 秘钥 32位长度
     * @return 加密后的内容
     * @throws Exception
     */
    public static byte[] encryptMode(String key, String src) throws ServiceException {
        try {
            SecretKey deskey = new SecretKeySpec(key.getBytes(), Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src.getBytes());
        } catch (java.security.NoSuchAlgorithmException e1) {
            log.error("3DES加密异常:", e1);
            throw new ServiceException("");
        } catch (javax.crypto.NoSuchPaddingException e2) {
            log.error("3DES加密异常:", e2);
            throw new ServiceException("");
        } catch (Exception e3) {
            log.error("3DES加密异常:", e3);
            throw new ServiceException("");
        }
    }

    /**
     *
     * @param src 密文
     * @param key 秘钥 32位长度
     * @return 解密后明文
     * @throws Exception
     */
    public static String decryptMode(String key, byte[] src) throws ServiceException {
        try {
            SecretKey deskey = new SecretKeySpec(key.getBytes(),Algorithm);
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            String returnString=new String(c1.doFinal(src));
            return returnString;
        } catch (java.security.NoSuchAlgorithmException e1) {
            throw new ServiceException("");
        } catch (javax.crypto.NoSuchPaddingException e2) {
            throw new ServiceException("");
        } catch (Exception e3) {
            throw new ServiceException("");
        }
    }





//    后两个方法适用于24位以上秘钥加密  但是只截取前面24位加密

    /**
     *
     * @param src 加密内容
     * @param key 秘钥 32位长度
     * @return 加密后的内容
     * @throws Exception
     */
    public static byte[] encryptThreeDESECB(final String key,final String src) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));

        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        final byte[] bytes = cipher.doFinal(src.getBytes());

        return bytes;

    }

    /**
     *
     * @param src 密文
     * @param key 秘钥 32位长度
     * @return 解密后明文
     * @throws Exception
     */
    public static String decryptThreeDESECB( final String key,byte[] src) throws Exception {
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        final byte[] retByte = cipher.doFinal(src);

        return new String(retByte);
    }







    // 转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer("");
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
            if (n < b.length - 1)
                hs.append(":");
        }
        return hs.toString().toUpperCase();
    }
}
