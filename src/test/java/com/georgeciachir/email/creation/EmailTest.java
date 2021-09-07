package com.georgeciachir.email.creation;

import com.georgeciachir.crypto.AESEncryptor;
import com.georgeciachir.crypto.DESEncryptor;
import com.georgeciachir.crypto.EncryptionStrategy;
import com.georgeciachir.email.client.RetryPolicy;
import com.georgeciachir.resourcelocator.FolderResourceLocator;
import com.georgeciachir.resourcelocator.ResourceLocator;
import com.georgeciachir.template.HTMLClassicTemplate;
import com.georgeciachir.template.TemplateType;
import com.georgeciachir.testframework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static com.georgeciachir.crypto.EncryptionStrategy.encryptionFor;
import static com.georgeciachir.crypto.EncryptionType.AES;
import static com.georgeciachir.crypto.EncryptionType.DES;
import static com.georgeciachir.email.creation.Draft.draftFrom;
import static com.georgeciachir.email.creation.Email.emailFrom;
import static com.georgeciachir.testframework.Assert.assertEquals;
import static com.georgeciachir.testframework.Assert.assertFalse;
import static com.georgeciachir.testframework.Assert.assertTrue;

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
        EncryptionStrategy encryptionStrategy = encryptionFor(DES).thenApply(AES);
        Draft draft = draftFrom(TemplateType.NONE, EXAMPLE_CONTENT, EXTERNAL_EMAIL, RetryPolicy.NONE, encryptionStrategy);

        //when
        Email email = emailFrom(draft);

        //then
        String expectedContent = EXAMPLE_CONTENT.concat(" and ").concat(DISCLAIMER);
        String expectedAfterFirstEncryption = new DESEncryptor().encrypt(expectedContent);
        String expectedAfterSecondEncryption = new AESEncryptor().encrypt(expectedAfterFirstEncryption);
        assertEquals(expectedAfterSecondEncryption, email.getEncryptedContent());
        assertTrue(email.isExternalEmail());
    }

    private void testEmailCreationWithClassicTemplate() {
        //given
        EncryptionStrategy encryptionStrategy = encryptionFor(DES).thenApply(AES);
        Draft draft = draftFrom(TemplateType.CLASSIC, EXAMPLE_CONTENT, INTERNAL_EMAIL, RetryPolicy.NONE, encryptionStrategy);

        //when
        Email email = emailFrom(draft);

        //then
        String contentBeforeEncryption = createContentWithHTMLClassicTemplateWithoutDisclaimer();
        String expectedAfterFirstEncryption = new DESEncryptor().encrypt(contentBeforeEncryption);
        String expectedAfterSecondEncryption = new AESEncryptor().encrypt(expectedAfterFirstEncryption);
        assertEquals(expectedAfterSecondEncryption, email.getEncryptedContent());
        assertFalse(email.isExternalEmail());
    }

    private String createContentWithHTMLClassicTemplateWithoutDisclaimer() {
        ResourceLocator resourceLocator = new FolderResourceLocator();
        return HTMLClassicTemplate.builder(resourceLocator)
                .withBody(EXAMPLE_CONTENT)
                .build()
                .getContent();
    }
}
