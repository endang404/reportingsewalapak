package com.web.reporting.config;

import java.net.URI;

import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


public class CustomHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {
	@Override
	protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {

		if (HttpMethod.GET.equals(httpMethod)) {
			return new HttpEntityEnclosingGetRequestBase(uri);
		}
		return super.createHttpUriRequest(httpMethod, uri);
	}
}
