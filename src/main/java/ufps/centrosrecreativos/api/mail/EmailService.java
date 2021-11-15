package ufps.centrosrecreativos.api.mail;

public interface EmailService {

	public void sendMessage(String asunto, String destino, String mensaje);

	public void sendMessageWithError(Exception error);

	public void sendMessageWithWarning(String warning, Class<?> logger);

}
