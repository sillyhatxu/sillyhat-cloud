package com.sillyhat.cloud.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
		log.info(".......................Start zuul Server Finish.......................");
	}

	@Bean
	public ZuulFallbackProvider zuulFallbackProvider() {
		return new ZuulFallbackProvider() {
			@Override
			public String getRoute() {
				return "*";
			}

			@Override
			public ClientHttpResponse fallbackResponse() {
				return new ClientHttpResponse() {
					@Override
					public HttpStatus getStatusCode() throws IOException {
						return HttpStatus.OK;
					}

					@Override
					public int getRawStatusCode() throws IOException {
						return 408;
					}

					@Override
					public String getStatusText() throws IOException {
						return "Request Timeout";
					}

					@Override
					public void close() {

					}

					@Override
					public InputStream getBody() throws IOException {
						return new ByteArrayInputStream("{\"msg\":\"Request Timeout\",\"ret\":-408}".getBytes());
					}

					@Override
					public HttpHeaders getHeaders() {
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);
						return headers;
					}
				};
			}
		};
	}

}
