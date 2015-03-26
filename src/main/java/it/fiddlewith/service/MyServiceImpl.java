package it.fiddlewith.service;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@WebService
public class MyServiceImpl implements MyService {

	@Resource
	private WebServiceContext wsctx;

	@Override
	public CallSIResponse ask(CallSIClient request) {
		doAuthentication();
		return new CallSIResponse("OK", request.getContext().getServiceCode()
				+ "-"
				+ request.getContext().getSession()
				+ ":"
				+ Joiner.on(", ").join(
						Lists.transform(request.getParameters(),
								new Function<Param, String>() {
									@Override
									public String apply(Param arg0) {
										return arg0.getName() + " => "
												+ arg0.getValue();
									}
								})), "");
	}

	private void doAuthentication() {

		MessageContext mctx = wsctx.getMessageContext();
		@SuppressWarnings("rawtypes")
		Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);

		@SuppressWarnings("rawtypes")
		ArrayList list = (ArrayList) http_headers.get("Authorization");
		if (list == null || list.size() == 0) {
			throw new RuntimeException(
					"Authentication failed! This WS needs BASIC Authentication!");
		}

		String userpass = (String) list.get(0);
		userpass = userpass.substring(5);
		byte[] buf = Base64.decodeBase64(userpass.getBytes());
		String credentials = new String(buf);

		String username = null;
		String password = null;
		int p = credentials.indexOf(":");
		if (p > -1) {
			username = credentials.substring(0, p);
			password = credentials.substring(p + 1);
		} else {
			throw new RuntimeException(
					"There was an error while decoding the Authentication!");
		}
		// This should be changed to a DB / Ldap authentication check
		if (username.equals("admin") && password.equals("myadmin!")) {
			System.out
					.println("============== Authentication OK =============");
			return;
		} else {
			throw new RuntimeException(
					"Authentication failed! Wrong username / password!");
		}

	}

}
