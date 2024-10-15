/*JUnit Jupiter annotations can be used as meta-annotations. That means that you can define your
own composed annotation that will automatically inherit the semantics of its meta-annotations.
For example, instead of copying and pasting @Tag("fast") throughout your code base (see Tagging
and Filtering), you can create a custom composed annotation named @Fast as follows. @Fast can then
be used as a drop-in replacement for @Tag("fast").*/
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
public @interface Fast {
}
