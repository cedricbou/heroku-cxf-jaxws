package it.fiddlewith.service;

import javax.jws.WebService;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@WebService
public class MyServiceImpl implements MyService {

	@Override
	public Return ask(final CallInRequest request) {
		return new Return(
			request.getSession() + "-" + request.getCallId() + ":" + Joiner.on(", ").join(Lists.transform(request.getParams(), new Function<Param, String>() { 
				@Override
				public String apply(Param arg0) {
					return arg0.getKey() + " => " + arg0.getValue();
				}
			})));
	}

}
