Write an email client for sending emails with different characteristics, with a basic public contract like the one below:

```
public interface EmailService {
    void send(Email email);
}
```

The emails can be sent as plain text or as HTML and emails, which are sent outside the company servers need to be logged and have a disclaimer added to the end. 
The emails could also be encrypted with AES or DES, or even both at the same time. 
In case there are problems when sending the emails a retry mechanism should be set up with three times retry.

Design a simple client and write a short program inside the standard void main(String[] args) function that would implement the following scenarios:

- sending a plain text email to an outside resource, with a disclaimer added at the end, unencrypted and no retry

- sending an HTML email to an internal server (so without the disclaimer), encrypted with DES, with the retry functionality

- sending an HTML email to an outside resource, with a disclaimer added at the end and encrypted with AES with retries in case of errors

- sending a plain text email to an outside resource and encrypted first with DES and then with AES

Do not provide real implementations for the encryption.
