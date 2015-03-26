package it.fiddlewith.service;

import javax.jws.WebService;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@WebService
public class MyServiceImpl implements MyService {

	
	@Override
	public CallSIResponse ask(CallSIClient request) {
		return new CallSIResponse("OK", 
			request.getContext().getServiceCode() 
				+ "-" + request.getContext().getSession() 
				+ ":" 
				+ Joiner.on(", ")
					.join(Lists.transform(request.getParameters(), new Function<Param, String>() { 
				@Override
				public String apply(Param arg0) {
					return arg0.getName() + " => " + arg0.getValue();
				}
			})), "");
	}

}
