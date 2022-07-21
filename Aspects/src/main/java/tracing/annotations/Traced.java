package tracing.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Target(value = {ElementType.TYPE})
@Inherited
public @interface Traced {
}
