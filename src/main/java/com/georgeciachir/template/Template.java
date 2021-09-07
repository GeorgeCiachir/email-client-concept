package com.georgeciachir.template;

import com.georgeciachir.email.creation.Draft;

public interface Template {

    String createContent(Draft draft, String disclaimer);
}
