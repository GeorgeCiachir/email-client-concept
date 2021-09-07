package com.georgeciachir.template;

import java.util.Objects;

public class EmptyTemplate implements Template {

    private EmptyTemplate() {
    }

    public static EmptyTemplate emptyTemplate() {
        return new EmptyTemplate();
    }

    @Override
    public String createContent(TemplateType type, String content, String disclaimer) {
        String body = Objects.isNull(content) ? "" : content;
        if (Objects.isNull(disclaimer) || disclaimer.isEmpty()) {
            return content;
        }
        return body.concat(" and ").concat(disclaimer);
    }
}
