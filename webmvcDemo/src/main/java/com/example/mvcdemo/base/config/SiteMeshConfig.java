package com.example.mvcdemo.base.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * 
 * @ClassName: SiteMashConfig
 * @Description: sitemash 配置器
 * @author: ysc
 * @date: 2016年3月3日 
 * @time: 下午3:53:53
 *
 */
public class SiteMeshConfig extends ConfigurableSiteMeshFilter {
	private final String DEFAULT_DIR="/WEB-INF/views/layouts";

	@Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		
		
		
		//excluded 不需要渲染的路径
		builder.addExcludedPath("/static/*");
		builder.addExcludedPath("/webapi/*");
        
        //decorator 需要渲染的路径
        String DEFAULT=DEFAULT_DIR+"/default.jsp";
        builder.addDecoratorPath("/demo", DEFAULT);
        builder.addDecoratorPath("/demo/*", DEFAULT);
    }
}
