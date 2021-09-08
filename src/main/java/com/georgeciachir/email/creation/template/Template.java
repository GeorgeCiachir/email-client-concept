package com.georgeciachir.email.creation.template;

import com.georgeciachir.email.creation.DraftContent;

public interface Template {

    String createContent(DraftContent draftContent, String disclaimer);
}
