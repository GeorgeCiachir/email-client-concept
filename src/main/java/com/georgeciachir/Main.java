package com.georgeciachir;

import com.georgeciachir.crypto.EncryptionStrategy;
import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.email.creation.Draft;
import com.georgeciachir.email.creation.Email;
import com.georgeciachir.email.service.DefaultEmailService;
import com.georgeciachir.email.service.EmailService;
import com.georgeciachir.template.TemplateType;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.crypto.EncryptionStrategy.encryptionFor;
import static com.georgeciachir.crypto.EncryptionType.AES;
import static com.georgeciachir.crypto.EncryptionType.DES;
import static com.georgeciachir.email.creation.Draft.draftFrom;
import static com.georgeciachir.email.creation.Email.emailFrom;

public class Main {

    private static final List<Draft> DRAFTS = new ArrayList<>();
    private static final List<Email> SENT_EMAILS = new ArrayList<>();

    private static final String EXAMPLE_CONTENT = "This is the body of the email";
    private static final String EXTERNAL_EMAIL = "john.doe@outside";
    private static final String INTERNAL_EMAIL = "john.doe@company.com";

    public static void main(String[] args) {

        Draft first = draftFrom(TemplateType.NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, EncryptionStrategy.NONE);

        EncryptionStrategy encryption2 = encryptionFor(DES);
        Draft second = draftFrom(TemplateType.CLASSIC, EXAMPLE_CONTENT, INTERNAL_EMAIL, RetryPolicy.RETRY, encryption2);

        EncryptionStrategy encryption3 = encryptionFor(AES);
        Draft third = draftFrom(TemplateType.NEW_MODEL, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.RETRY, encryption3);

        EncryptionStrategy encryption4 = encryptionFor(DES).thenApply(AES);
        Draft fourth = draftFrom(TemplateType.NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, encryption4);


        DRAFTS.add(first);
        DRAFTS.add(second);
        DRAFTS.add(third);
        DRAFTS.add(fourth);

        EmailService emailService = new DefaultEmailService();

        for (Draft draft : DRAFTS) {
            Email email = emailFrom(draft);
            emailService.send(email);

            if (email.isSuccessfullySent()) {
                SENT_EMAILS.add(email);
            }
        }

        SENT_EMAILS.forEach(email -> DRAFTS.removeIf(draft -> draft.getId().equals(email.getId())));


        System.out.println();
        System.out.println("***************");
        System.out.println("Sent: ");
        SENT_EMAILS.forEach(email -> System.out.println(email.getId()));
        System.out.println();
        System.out.println("***************");
        System.out.println("Not sent:");
        DRAFTS.forEach(draft -> System.out.println(draft.getId()));

    }
}
