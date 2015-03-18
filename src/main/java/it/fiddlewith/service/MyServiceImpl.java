package it.fiddlewith.service;

import java.util.Arrays;

import javax.jws.WebService;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@WebService
public class MyServiceImpl implements MyService {

	@Override
	public Return ask(Param[] params) {
		return new Return(
			Joiner.on(", ").join(Lists.transform(Arrays.asList(params), new Function<Param, String>() { 
				@Override
				public String apply(Param arg0) {
					return arg0.getKey() + " => " + arg0.getValue();
				}
			})));
	}

}
