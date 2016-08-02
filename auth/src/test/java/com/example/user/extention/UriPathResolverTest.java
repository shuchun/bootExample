package com.example.user.extention;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IBM on 2016/8/2.
 */
public class UriPathResolverTest {

    private final String testUri="http://localhost:9001/api/users/test/add/ysc";

    /**
     * 域获取测试
     */
    @Test
    public void getDomainTest(){
        String domain=UriPathResolver.getDomain(testUri);

        Assertions.assertThat(domain).isNotEmpty();
        Assertions.assertThat(domain).contains("localhost");
    }

    /**
     * 协议获取测试
     */
    @Test
    public void getSchemeTest(){
        String scheme=UriPathResolver.getScheme(testUri);

        Assertions.assertThat(scheme).isNotEmpty();
        Assertions.assertThat(scheme).isEqualTo("http");
    }

    /**
     * 端口获取测试
     */
    @Test
    public void getPortTest(){
        String port=UriPathResolver.getPort(testUri);

        Assertions.assertThat(port).isNotEmpty();
        Assertions.assertThat(port).isEqualTo("9001");
    }
}
