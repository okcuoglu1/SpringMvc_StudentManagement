package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml de configuration yapmak yerine bu classı kullanıyoruz.
//dispatcher servletin tanımlanması ve configuration ile başlıyoruz.
//AbstractAnnotationConfigDispatcherServletInitializer bu class dispatcher servletn baslatılması ve
// icinde bulundugu methodlar ile config ayarlarının bulunduğu dosyanın yerinin belirtilmesini sağlar.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
Dispatcher: Servlet WebAppContext->Controller,Handler Mapping,View Resolver
            Root WebAppContext->services,repositories
*/




    @Override //db'ye erişim(hibernate) için config class
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override //Controller,Handler Mapping,View Resolver ile ilgili config class
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override//Hangi url ile gelen istekler servlet tarafından karşılanacak
    protected String[] getServletMappings() {
        return new String[]{"/"}; //bütün isteklerimizi dispatcher servlet tarafından karşılanmasını istedik.
    }
}
