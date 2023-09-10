package com.web.reporting.config;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.springframework.http.HttpMethod;

public class HttpEntityEnclosingGetRequestBase extends HttpEntityEnclosingRequestBase {

	public HttpEntityEnclosingGetRequestBase(final URI uri) {
		super.setURI(uri);
	}

	@Override
	public String getMethod() {
		return HttpMethod.GET.name();
	}

}
