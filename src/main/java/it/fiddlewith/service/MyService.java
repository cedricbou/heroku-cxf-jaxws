package it.fiddlewith.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;

@WebService
public interface MyService {

	@XmlRootElement
	public static class CallInRequest {
		private String callId;
		private String session;
		
		private List<Param> params;
		
		protected CallInRequest() {
			callId = "";
			session = "";
			params = new ArrayList<Param>();
		}

		public CallInRequest(final String callId, final String session, final List<Param> params) {
			this.callId = callId;
			this.session = session;
			this.params = Lists.newArrayList(params);			
		}

		public String getCallId() {
			return callId;
		}

		public void setCallId(String callId) {
			this.callId = callId;
		}

		public String getSession() {
			return session;
		}

		public void setSession(String session) {
			this.session = session;
		}

		public List<Param> getParams() {
			return params;
		}

		public void setParams(List<Param> params) {
			this.params = params;
		}

	}
	
	public static class Param {
		private String key;
		private String value;

		protected Param() {
			key = "";
			value = "";
		}

		public Param(final String key, final String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	@XmlRootElement
	public static class Return {
		private String text;
		
		public Return(final String text) {
			this.text = text;
		}
		
		protected Return() {
			this.text = "";
		}

		public String getText() { return text; }
		
		public void setText(final String text) { this.text = text; }
	}

	@WebMethod(operationName = "ask")
	@WebResult(name = "callInResponse", partName = "myResponse")
	public Return ask(final @WebParam(name = "callInRequest") CallInRequest request);
}
