package cts.com.error;

public class Errorinfo {

	private String uri,message;
	public Errorinfo(String uri, String message) {
		super();
		this.uri = uri;
		this.message = message;
	}
	public String getUri() {
		return uri;
	}

	public String getMessage() {
		return message;
	}
}
