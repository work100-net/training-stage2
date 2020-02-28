package net.work100.training.stage2.iot.cloud.commons.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * <p>Title: EncryptionUtils</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-24 10:41
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-24   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class EncryptionUtils {
    private static final int SALT_LENGTH = 6;
    private static final String SEPARATOR = "#";

    public enum EncryptionType {
        MD5("md5"),
        SHA256("sha256");

        private String type;

        EncryptionType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }

        public static EncryptionType getEncryptionType(String type) {
            if (StringUtils.isEmpty(type)) {
                return MD5;
            }
            for (EncryptionType encryptionType : EncryptionType.values()) {
                if (encryptionType.type.equalsIgnoreCase(type)) {
                    return encryptionType;
                }
            }
            return MD5;
        }
    }

    /**
     * 加密文本
     *
     * @param encryptionType 加密方式
     * @param sourceText     原文(区分大小写)
     * @return
     */
    public static String encryptText(EncryptionType encryptionType, String sourceText) {
        String encryptedText = "";
        switch (encryptionType) {
            case MD5:
                encryptedText = DigestUtils.md5DigestAsHex(sourceText.getBytes());
                break;
            case SHA256:
                break;
            default:
                encryptedText = DigestUtils.md5DigestAsHex(sourceText.getBytes());
                break;
        }
        return encryptedText;
    }

    /**
     * 加密密码
     *
     * @param encryptionType 加密方式
     * @param sourcePassword 明文密码
     * @return
     */
    public static String encryptPassword(EncryptionType encryptionType, String sourcePassword) {
        String salt = RandomStringUtils.randomAlphanumeric(SALT_LENGTH);
        sourcePassword = String.format("%s%s", sourcePassword, salt);
        String encryptedPassword = encryptText(encryptionType, sourcePassword);
        return String.format("%s%s%s%s%s", encryptionType.getType(), SEPARATOR, salt, SEPARATOR, encryptedPassword);
    }

    /**
     * 验证加密文本
     *
     * @param encryptionType 加密类型
     * @param sourceText     原文
     * @param encryptedText  密文
     * @return
     */
    public static boolean validateEncryptText(EncryptionType encryptionType, String sourceText, String encryptedText) {
        return encryptText(encryptionType, sourceText).equals(encryptedText);
    }

    /**
     * 验证密码
     *
     * @param sourcePassword    明文密码
     * @param encryptedPassword 加密后组合串
     * @return
     */
    public static boolean validateEncryptPassword(String sourcePassword, String encryptedPassword) {
        try {
            String[] split = encryptedPassword.split(SEPARATOR);
            EncryptionType encryptionType = EncryptionType.getEncryptionType(split[0]);
            String salt = split[1];
            encryptedPassword = split[2];
            sourcePassword = String.format("%s%s", sourcePassword, salt);
            return encryptText(encryptionType, sourcePassword).equals(encryptedPassword);
        } catch (Exception ex) {
            return false;
        }
    }
}
