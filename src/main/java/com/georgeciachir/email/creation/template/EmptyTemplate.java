package com.georgeciachir.email.creation.template;

import com.georgeciachir.email.creation.DraftContent;

import java.util.Objects;

public class EmptyTemplate implements Template {

    private EmptyTemplate() {
    }

    public static EmptyTemplate emptyTemplate() {
        return new EmptyTemplate();
    }

    @Override
    public String createContent(DraftContent draftContent, String disclaimer) {
        String body = draftContent.getMessage();
        if (Objects.isNull(disclaimer) || disclaimer.isEmpty()) {
            return body;
        }
        return body.concat(" and ").concat(disclaimer);
    }
}
