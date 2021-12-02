package fxyh.crewler.annoation;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Inherited
@Target({ElementType.TYPE,FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mine {
    String crewlerUrl();

}
