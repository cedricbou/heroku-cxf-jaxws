package it.fiddlewith.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlRootElement;

@WebService
public interface MyService {

	@XmlRootElement
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

	@WebMethod
	public Return ask(final @WebParam Param[] params);
}
