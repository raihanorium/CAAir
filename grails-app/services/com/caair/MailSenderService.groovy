package com.caair

import groovy.text.SimpleTemplateEngine

import javax.activation.DataHandler
import javax.activation.DataSource
import javax.mail.Address
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class MailSenderService {

    private static final String GMAIL_SMTP = "smtp.gmail.com";
    private static final String AWS_SMTP = "email-smtp.us-east-1.amazonaws.com";
    private static final String MAIL_HOST = "mail.smtp.host";
    private static final String MAIL_PORT = "mail.smtp.port";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_SSL = "mail.smtp.ssl.enable";
    private static final String SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_MIME = "text/html; charset=utf-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String TRUE = "true";
    private static final String SMTP = "smtp";

    private String emailFrom;
    private String smtpHost;
    private String smtpPort;
    private String smtpEmail;
    private String smtpPwd;

    private void resolveMailConfiguration() {
        emailFrom = "noreply@athena.com.bd"
        smtpHost = "smtp.gmail.com"
        smtpPort = "465"
        smtpEmail = "noreply.athenamis@gmail.com"
        smtpPwd = "athena@123"
    }

    /// get evaluated mail body
    public String getMailBody(String mailTemplate, Map model) {
        SimpleTemplateEngine engine = new SimpleTemplateEngine()
        Writable template = engine.createTemplate(mailTemplate).make(model)
        mailTemplate = template.toString()
        return mailTemplate
    }

    // send mail with attachment
    public synchronized void sendMail(AppMail appMail) {
        try {

            if (appMail == null) return;
            // get session object
            Session session = getSession();
            // get message
            MimeMessage message = getMessage(session, appMail);

            // init body part
            Multipart multiPart = new MimeMultipart();
            MimeBodyPart bodyPartText = new MimeBodyPart();
            bodyPartText.setHeader(CONTENT_TYPE, MAIL_MIME);
            bodyPartText.setContent(appMail.getMailBody(), MAIL_MIME);

            // Add body parts to multi part
            multiPart.addBodyPart(bodyPartText);

            //add content part if mail has attachment
            if ((appMail.getAttachment() != null) && appMail.getAttachment().length > 0) {
                // create body part for Mail-Attachment
                MimeBodyPart bodyPartContent = new MimeBodyPart();
                bodyPartContent.setDataHandler(
                        new DataHandler(
                                new DataSource() {
                                    public String getContentType() {
                                        return appMail.getAttachmentMimeType();
                                    }

                                    public InputStream getInputStream() throws IOException {
                                        return new ByteArrayInputStream(appMail.getAttachment());
                                    }

                                    public String getName() {
                                        return null;
                                    }

                                    public OutputStream getOutputStream() throws IOException {
                                        return null;
                                    }
                                }
                        )
                );
                bodyPartContent.setFileName(appMail.getAttachmentName());
                multiPart.addBodyPart(bodyPartContent);
            }

            //set all content
            message.setContent(multiPart);
            // send mail
            Transport.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    // get message
    private MimeMessage getMessage(Session session, AppMail appMail) {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailFrom, appMail.getDisplayName()));

        Address[] address = [new InternetAddress(emailFrom)]
        message.setReplyTo(address)

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(appMail.getRecipients()));

        message.setSubject(appMail.getSubject());
        return message;
    }

    // get session object
    private Session getSession() {
        Properties props = new Properties();
        resolveMailConfiguration();

        props.put(MAIL_HOST, smtpHost);
        props.put(MAIL_PORT, smtpPort);
        if (smtpHost.equalsIgnoreCase(GMAIL_SMTP)) {     //only set when using GMAIL smtp
            props.put(SMTP_STARTTLS, TRUE);
            props.put(SMTP_AUTH, TRUE);
            props.put(SMTP_SSL, TRUE);
        } else if (smtpHost.equalsIgnoreCase(AWS_SMTP)) {
            props.put(MAIL_TRANSPORT_PROTOCOL, SMTP);
            props.put(SMTP_AUTH, TRUE);
            props.put(SMTP_STARTTLS, TRUE);
            props.put(SMTP_STARTTLS_REQUIRED, TRUE);
        }

        // authenticate session
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(smtpEmail, smtpPwd);
                    }
                }
        );
        return session;
    }
}
