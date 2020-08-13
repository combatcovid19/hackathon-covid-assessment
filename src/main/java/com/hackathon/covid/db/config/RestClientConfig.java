package com.hackathon.covid.db.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClientConfig {

private static final Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

public RestTemplate createRestTemplate(String certFilePath, String restServiceCertPwd) throws Exception {
RestTemplate restTemplate = new RestTemplate(
httpComponentsClientHttpRequestFactory(certFilePath, restServiceCertPwd));
return restTemplate;

}

public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(String certFilePath,
String restServiceCertPwd) throws Exception {
HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf(certFilePath, restServiceCertPwd))
.setMaxConnTotal(1000).setMaxConnPerRoute(100).build();
HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
httpClient);
return httpComponentsClientHttpRequestFactory;
}

public SSLConnectionSocketFactory sslsf(String certFilePath, String restServiceCertPwd) {
SSLContext sslcontext = null;
KeyStore trustStore = null;
InputStream instream = null;

try {
trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
instream = new FileInputStream(new File(certFilePath));
trustStore.load(instream, restServiceCertPwd.toCharArray());
sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
} catch (Exception exception) {
logger.error("Exception occured", exception, null);

} finally {
if (instream != null) {
try {
instream.close();
} catch (IOException e) {
logger.error("IOException occured", e, null);
}
}
}
SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);
return sslsf;
}

}

