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
		
		protected CallSIClient() {
			this.context = new Context();
			this.parameters = new ArrayList<Param>();
		}

		public CallSIClient(final String serviceCode, final String session, final List<Param> params) {
			this.context = new Context(serviceCode, session);
			this.parameters = Lists.newArrayList(params);			
		}

		public Context getContext() {
			return context;
		}

		public void setContext(Context context) {
			this.context = context;
		}

		public List<Param> getParameters() {
			return parameters;
		}

		public void setParameters(List<Param> params) {
			this.parameters = params;
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
			this.returnCode = "";
			this.message = "";
			this.number = "";		}

		public String getReturnCode() {
			return returnCode;
		}

		public void setReturnCode(String returnCode) {
			this.returnCode = returnCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		
	}

	@WebMethod(operationName = "callSIL")
	@WebResult(name = "callSIRespons", partName = "myResponse")
	public CallSIResponse ask(final @WebParam(name = "callSIRequest") CallSIClient request);
}
