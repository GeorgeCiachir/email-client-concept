package com.georgeciachir;

import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.email.creation.Draft;
import com.georgeciachir.email.creation.Email;
import com.georgeciachir.email.creation.template.HtmlTemplateType;
import com.georgeciachir.email.creation.template.TemplateType;
import com.georgeciachir.email.service.DefaultEmailService;
import com.georgeciachir.email.service.EmailService;
import com.georgeciachir.infrastructure.crypto.Encryption;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.georgeciachir.email.creation.Draft.htmlTemplatedDraft;
import static com.georgeciachir.email.creation.Email.emailFrom;
import static com.georgeciachir.infrastructure.crypto.Encryption.encrypt;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.AES;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.DES;

public class Main {

    private static final List<Draft> DRAFTS = new CopyOnWriteArrayList<>();
    private static final List<Email> SENT_EMAILS = new ArrayList<>();

    private static final String EXAMPLE_CONTENT = "This is the body of the email";
    private static final String EXTERNAL_EMAIL = "john.doe@outside";
    private static final String INTERNAL_EMAIL = "john.doe@company.com";

    public static void main(String[] args) {

        Draft first = Draft.nonTemplatedDraft(TemplateType.NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, Encryption.NONE);

        Encryption encryption2 = encrypt(DES);
        Draft second = htmlTemplatedDraft(TemplateType.HTML, HtmlTemplateType.CLASSIC, EXAMPLE_CONTENT, INTERNAL_EMAIL, RetryPolicy.RETRY, encryption2);

        Encryption encryption3 = encrypt(AES);
        Draft third = htmlTemplatedDraft(TemplateType.HTML, HtmlTemplateType.NEW_MODEL, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.RETRY, encryption3);

        Encryption encryption4 = encrypt(DES).thenEncrypt(AES);
        Draft fourth = Draft.nonTemplatedDraft(TemplateType.NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, encryption4);

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
                DRAFTS.remove(draft);
            }
        }


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
