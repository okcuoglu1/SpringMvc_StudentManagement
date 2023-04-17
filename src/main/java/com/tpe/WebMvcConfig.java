package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc //MVC için config ayarlarını etkinleştirmek için
public class WebMvcConfig implements WebMvcConfigurer {

    //Controllerdan dispatcher servlet'e dosyanın string bir view ismi döner. Bu dosyanın çözümlenmesini : viewresolver yapar.
    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class); //JavaStandartTagLibrary : JSP dosyaları içinde kendine has tagleri ile daha az kod yazmamızı sağlar.
        resolver.setPrefix("/WEB-INF/views/");//view dosyalarının yerini belirtiyoruz.
        resolver.setSuffix(".jsp"); //view dosyalarının uzantısı
        return resolver;
    }

    //Statik olan kaynakların dispatchera gönderilmesine gerek yok.Web serverdan git gel yapabilir.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").//bu pathdeki kaynakları statik olarak sun
                addResourceLocations("/resources/").//kaynakların yeri
                setCachePeriod(0);//cacheleme için belirli bir periyod süresi verilebilir.
    }

}
