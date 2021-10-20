package ufps.centrosrecreativos.api.mail;

public interface EmailService {

	public void sendMessageWithAttachment(String asunto, String mensaje);

	public void sendMessageWithError(Exception error);

	public void sendMessageWithWarning(String warning, Class<?> logger);

}
