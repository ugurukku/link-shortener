package com.ugurukku.linkshortener.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

import static com.ugurukku.linkshortener.model.constants.LocaleConstants.LOCALES;


@Configuration
public class LocaleResolverConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(LOCALES);
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

}
