package ufps.centrosrecreativos.api.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.Table;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String usernameFrom;

	@Value("${spring.mail.notificaciones}")
	private String notificacionesTo;

	@Value("${spring.mail.name}")
	private String nameFrom;

	@Async
	@Override
	public void sendMessageWithAttachment(String asunto, String mensaje) {

		MimeMessage message = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(notificacionesTo);
			helper.setSubject(asunto);
			helper.setText(mensaje, true);

			helper.setFrom(usernameFrom, nameFrom);

			emailSender.send(message);
		} catch (MessagingException | UnsupportedEncodingException | MailException e) {
			e.printStackTrace();
		}
	}

	@Async
	@Override
	public void sendMessageWithError(Exception error) {

		MimeMessage message = emailSender.createMimeMessage();

		String textError = "<h1>INFORME DE ERROR</h1>";

		textError += "<p>MENSAJE: " + error.getMessage() + "</p>";

		StackTraceElement[] stacks = error.getStackTrace();

		if (stacks != null) {
			for (int i = 0; i < stacks.length; i++) {
				textError += i + ": " + stacks[i].toString() + "<br>";
			}
		}

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(notificacionesTo);
			helper.setSubject("Informe de Error Mipres");
			helper.setText(textError, true);

			helper.setFrom(usernameFrom, nameFrom);

			emailSender.send(message);
		} catch (MessagingException | UnsupportedEncodingException | MailException e) {
			e.printStackTrace();
		}
	}

	@Async
	@Override
	public void sendMessageWithWarning(String warning, Class<?> clase) {
		MimeMessage message = emailSender.createMimeMessage();

		String textWarning = "<h1>INFORME DE WARNING</h1>";

		textWarning += "<p>MENSAJE: " + warning + "<p>";
		textWarning += "<p>ORIGEN: " + clase.getName() + "</p>";

		try {

			Class<?> c = clase.getClass();
			Table table = c.getAnnotation(Table.class);
			String tableName = table.name();

			textWarning += "<p>TABLE: " + tableName + "</p>";

		} catch (Exception e) {
		}

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(notificacionesTo);
			helper.setSubject("Informe de Error Mipres");
			helper.setText(textWarning, true);

			helper.setFrom(usernameFrom, nameFrom);

			emailSender.send(message);
		} catch (MessagingException | UnsupportedEncodingException | MailException e) {
			e.printStackTrace();
		}
	}
}
