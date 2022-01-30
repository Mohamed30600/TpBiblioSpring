package fr.diginamic.webmvc01.execptions;

/**
 * on creer une exception pour gere les erreur sur les clients
 * @author Mohamed
 *
 */

public class ErrorClient extends Exception{

	public ErrorClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ErrorClient(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
