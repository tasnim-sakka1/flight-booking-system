package com.iit.flightbooking.services;

public interface EmailService {
    void sendTicketEmail(String to, String subject, String body);
    void sendEmailWithAttachment(
            String to,
            String subject,
            String body,
            byte[] attachmentData,
            String attachmentFilename
    );
}
