package com.example.user.extention;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by IBM on 2016/8/3.
 */
@Component
@ConfigurationProperties(prefix="authConf")
public class authConfig {

    private static String encryKey;//加密key


    public static String getEncryKey() {
        return encryKey;
    }

    public static void setEncryKey(String encryKey) {
        authConfig.encryKey = encryKey;
    }
}
