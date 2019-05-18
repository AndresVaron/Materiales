package exceptions;

import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ProtocolException extends Exception{

	private String received;
	
	private List<String> expected;
	
	public ProtocolException(String received, List<String> expected) {
		
		this.received = received;
		this.expected = expected;
		
	}
	
	public String getMessage() {
		String r = "Error de protocolo, se recibio: " + received + " pero se esperaba: ";
		for(String s: expected) {
			r+= s + " o ";
		}
		return r;
	}
	
}
