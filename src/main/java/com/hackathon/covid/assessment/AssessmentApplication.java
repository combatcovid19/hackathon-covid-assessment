package com.hackathon.covid.assessment;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.covid.db.config.RestClientConfig;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "com.hackathon.*" })
@EnableSwagger2
@Slf4j
@EnableScheduling
public class AssessmentApplication {
@Autowired
RestClientConfig restClientConfig;

public static void main(String[] args) {
SpringApplication.run(AssessmentApplication.class, args);
}

@Bean
public Docket swaggerApi() {
return new Docket(DocumentationType.SWAGGER_2).select()
.apis(RequestHandlerSelectors.basePackage("com.hackathon.covid.assessment.controller")).build();
}




@Bean
ObjectMapper objectMapper() {
ObjectMapper objectMapper = new ObjectMapper();
objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
return objectMapper;
}
@Bean
public RestTemplate serviceRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
       @Override public boolean isTrusted(X509Certificate[] x509Certificates, String s)
                       throws CertificateException {
           return true;
       }
   };

   SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                   .loadTrustMaterial(null, acceptingTrustStrategy)
                   .build();

   SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

   CloseableHttpClient httpClient = HttpClients.custom()
                   .setSSLSocketFactory(csf)
                   .build();

   HttpComponentsClientHttpRequestFactory requestFactory =
                   new HttpComponentsClientHttpRequestFactory();

   requestFactory.setHttpClient(httpClient);
   return new RestTemplate(requestFactory);
   
}
@Bean
public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);
   
    mailSender.setUsername("combatcovid43@gmail.com");
    mailSender.setPassword("codeforcovid19");
   
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");
   
    return mailSender;
}



}

