package com.community.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.community.web.util.CommonUtil;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${upload.path.image.default.post}")
	private String uploadPostImagePath;
	
	@Value("${upload.path.image.linux.post}") 
	private String uploadPostImagePathForLinux;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (CommonUtil.isWindows()) {
			registry.addResourceHandler(uploadPostImagePath + "/**")
					.addResourceLocations("file://" + uploadPostImagePath)
					.setCachePeriod(3600)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		} else if (CommonUtil.isUnix()) {
			registry.addResourceHandler(uploadPostImagePath + "/**")
					.addResourceLocations("file:" + uploadPostImagePathForLinux)
					.setCachePeriod(3600)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		}
	}
}