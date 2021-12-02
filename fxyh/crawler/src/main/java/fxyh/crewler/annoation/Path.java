package fxyh.crewler.annoation;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Path {
    String cssPath();
}
