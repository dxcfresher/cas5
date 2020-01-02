package com.dxc.integral.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.dxc.integral.identity.web.controller.ChangePasswordController;
import com.dxc.integral.identity.web.controller.PmsController;
import com.dxc.integral.identity.web.controller.ResetPasswordController;


@Configuration("integralConfiguration")
@PropertySource("classpath:dxc-integral.properties")
@ComponentScan(value = {"com.dxc.integral"})
@ImportResource(value = { 
		"classpath:scenarios/id-services.xml",
	    "classpath:scenarios/auth/auth-dao.xml"
	    })
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class IntegralConfiguration {

	 @Autowired
	 private CasConfigurationProperties casProperties;
	 
	 @Bean
	 public ConversionServiceFactoryBean conversionService() {
	     ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
	     Set<Converter<?, ?>> myConverters = new HashSet<>();
	     myConverters.add(new StringToDurationConverter());
	     conversionServiceFactoryBean.setConverters(myConverters);
	     return conversionServiceFactoryBean;
	 }
	 
   @Bean
   public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
       SimpleUrlHandlerMapping simpleUrlHandlerMapping
         = new SimpleUrlHandlerMapping();
       Map<String, Object> urlMap = new HashMap<>();
       urlMap.put("/pms", pmsController());
       urlMap.put("/pms/resetPassword", resetPasswordController());
       urlMap.put("/pms/changePassword", changePasswordController());
       simpleUrlHandlerMapping.setUrlMap(urlMap);
       return simpleUrlHandlerMapping;
   }
   @Bean
   public ChangePasswordController changePasswordController() {
       return new ChangePasswordController();
   }

   @Bean
   public PmsController pmsController() {
       return new PmsController();
   }
   
   @Bean
   public ResetPasswordController resetPasswordController() {
       return new ResetPasswordController();
   }
}
