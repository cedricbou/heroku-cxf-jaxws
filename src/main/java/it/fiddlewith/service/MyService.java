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

	public static class Context {
		private String serviceCode;
		private String session;

		protected Context() {
			serviceCode = "";
			session = "";
		}

		public Context(final String serviceCode, final String session) {
			this.serviceCode = serviceCode;
			this.session = session;
		}

		public String getServiceCode() {
			return serviceCode;
		}

		public void setServiceCode(String serviceCode) {
			this.serviceCode = serviceCode;
		}

		public String getSession() {
			return session;
		}

		public void setSession(String session) {
			this.session = session;
		}
	}

	@XmlRootElement
	public static class CallSIClient {
		private Context context;
		private List<Param> parameters;
		
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
		private String name;
		private String value;

		protected Param() {
			name = "";
			value = "";
		}

		public Param(final String name, final String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	@XmlRootElement
	public static class CallSIResponse {
		private String returnCode;
		private String message;
		private String number;
		
		public CallSIResponse(final String returnCode, final String message, final String number) {
			this.returnCode = returnCode;
			this.message = message;
			this.number = number;
		}
		
		protected CallSIResponse() {
			this.text = "";
		}

		public String getMessage() { return message; }
		
		public void setMessage(final String message) { this.message = message; }

		public String getMessage() { return message; }
		
		public void setMessage(final String message) { this.message = message; }

		
	}

	@WebMethod(operationName = "ask")
	@WebResult(name = "callInResponse", partName = "myResponse")
	public Return ask(final @WebParam(name = "callInRequest") CallInRequest request);
}
