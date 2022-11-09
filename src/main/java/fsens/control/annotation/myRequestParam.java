package fsens.control.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface myRequestParam {
    String value() default "";
    boolean required() default true;
}
