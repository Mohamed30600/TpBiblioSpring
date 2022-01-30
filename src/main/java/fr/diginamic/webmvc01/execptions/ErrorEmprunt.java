package fr.diginamic.webmvc01.execptions;

/**
 * on creer une exception pour gere les erreur sur les clients
 * @author Mohamed
 *
 */

public class ErrorEmprunt extends Exception{

	public ErrorEmprunt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorEmprunt(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ErrorEmprunt(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ErrorEmprunt(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ErrorEmprunt(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
