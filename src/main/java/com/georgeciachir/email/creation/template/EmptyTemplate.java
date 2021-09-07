package com.georgeciachir.email.creation.template;

import com.georgeciachir.email.creation.Draft;

import java.util.Objects;

public class EmptyTemplate implements Template {

    private EmptyTemplate() {
    }

    public static EmptyTemplate emptyTemplate() {
        return new EmptyTemplate();
    }

    @Override
    public String createContent(Draft draft, String disclaimer) {
        String body = draft.getMessage();
        if (Objects.isNull(disclaimer) || disclaimer.isEmpty()) {
            return body;
        }
        return body.concat(" and ").concat(disclaimer);
    }
}
