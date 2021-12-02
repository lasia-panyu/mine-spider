package fxyh.crewler.annoation;


import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MineDo {
    MineType[] value();
}
