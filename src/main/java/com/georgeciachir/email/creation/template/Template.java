package com.georgeciachir.email.creation.template;

import com.georgeciachir.email.creation.Draft;

public interface Template {

    String createContent(Draft draft, String disclaimer);
}
