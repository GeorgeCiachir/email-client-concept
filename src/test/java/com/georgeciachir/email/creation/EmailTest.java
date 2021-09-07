package com.georgeciachir.email.creation;

import com.georgeciachir.infrastructure.crypto.Encryption;
import com.georgeciachir.infrastructure.crypto.encryptor.AESEncryptor;
import com.georgeciachir.infrastructure.crypto.encryptor.DESEncryptor;
import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.infrastructure.resourcelocator.FolderResourceLocator;
import com.georgeciachir.infrastructure.resourcelocator.ResourceLocator;
import com.georgeciachir.infrastructure.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.infrastructure.crypto.Encryption.encrypt;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.AES;
import static com.georgeciachir.infrastructure.crypto.EncryptionType.DES;
import static com.georgeciachir.email.creation.Draft.htmlTemplatedDraft;
import static com.georgeciachir.email.creation.Email.emailFrom;
import static com.georgeciachir.email.creation.template.HtmlTemplate.htmlTemplate;
import static com.georgeciachir.email.creation.template.HtmlTemplateType.CLASSIC;
import static com.georgeciachir.email.creation.template.TemplateType.HTML;
import static com.georgeciachir.email.creation.template.TemplateType.NONE;
import static com.georgeciachir.infrastructure.Assert.assertEquals;
import static com.georgeciachir.infrastructure.Assert.assertFalse;
import static com.georgeciachir.infrastructure.Assert.assertTrue;

public class EmailTest {

    private static final String EXAMPLE_CONTENT = "This is the body of the email";
    private static final String EXTERNAL_EMAIL = "john.doe@outside";
    private static final String INTERNAL_EMAIL = "john.doe@company.com";
    private static final String DISCLAIMER = "This is the disclaimer";

    //TODO: create an annotation processor to read a @Test annotation from each method
    public List<TestCase> getTests() {
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("testEmailCreationWithoutTemplate", this::testEmailCreationWithoutTemplate));
        testCases.add(new TestCase("testEmailCreationWithClassicTemplate", this::testEmailCreationWithClassicTemplate));
        return testCases;
    }

    private void testEmailCreationWithoutTemplate() {
        //given
        Encryption encryption = encrypt(DES).thenEncrypt(AES);
        Draft draft = Draft.nonTemplatedDraft(NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, encryption);

        //when
        Email email = emailFrom(draft);

        //then
        String expectedContent = EXAMPLE_CONTENT.concat(" and ").concat(DISCLAIMER);
        String expectedAfterFirstEncryption = new DESEncryptor().apply(expectedContent);
        String expectedAfterSecondEncryption = new AESEncryptor().apply(expectedAfterFirstEncryption);
        assertEquals(expectedAfterSecondEncryption, email.getEncryptedContent());
        assertTrue(email.isExternalEmail());
    }

    private void testEmailCreationWithClassicTemplate() {
        //given
        Encryption encryption = encrypt(DES).thenEncrypt(AES);
        Draft draft = htmlTemplatedDraft(HTML, CLASSIC, EXAMPLE_CONTENT, INTERNAL_EMAIL, RetryPolicy.NONE, encryption);

        //when
        Email email = emailFrom(draft);

        //then
        String contentBeforeEncryption = createContentWithHTMLClassicTemplateWithoutDisclaimer(draft);
        String expectedAfterFirstEncryption = new DESEncryptor().apply(contentBeforeEncryption);
        String expectedAfterSecondEncryption = new AESEncryptor().apply(expectedAfterFirstEncryption);
        assertEquals(expectedAfterSecondEncryption, email.getEncryptedContent());
        assertFalse(email.isExternalEmail());
    }

    private String createContentWithHTMLClassicTemplateWithoutDisclaimer(Draft draft) {
        ResourceLocator resourceLocator = new FolderResourceLocator();
        return htmlTemplate(resourceLocator)
                .createContent(draft, "");
    }
}
