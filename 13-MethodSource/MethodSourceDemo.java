/*@MethodSource allows you to refer to one or more factory methods of the test class or external
classes.
Factory methods within the test class must be static unless the test class is annotated with
@TestInstance(Lifecycle.PER_CLASS); whereas, factory methods in external classes must always be
static. In addition, such factory methods must not accept any arguments.
Each factory method must generate a stream of arguments, and each set of arguments within the
stream will be provided as the physical arguments for individual invocations of the annotated
@ParameterizedTest method*/

@ParameterizedTest
@MethodSource("stringProvider")
void testWithExplicitLocalMethodSource(String argument) {
  assertNotNull(argument);
}
static Stream<String> stringProvider() {
  return Stream.of("apple", "banana");
}

/*If you do not explicitly provide a factory method name via @MethodSource, JUnit Jupiter will search
for a factory method that has the same name as the current @ParameterizedTest method by
convention. This is demonstrated in the following example.*/

@ParameterizedTest
@MethodSource
void testWithDefaultLocalMethodSource(String argument) {
  assertNotNull(argument);
}
static Stream<String> testWithDefaultLocalMethodSource() {
  return Stream.of("apple", "banana");
}

/*If a parameterized test method declares multiple parameters, you need to return a collection,
stream, or array of Arguments instances or object arrays as shown below (see the Javadoc for
@MethodSource for further details on supported return types). Note that arguments(Object…) is a
static factory method defined in the Arguments interface. In addition, Arguments.of(Object…) may be
used as an alternative to arguments(Object…).*/

@ParameterizedTest
@MethodSource("stringIntAndListProvider")
void testWithMultiArgMethodSource(String str, int num, List<String> list) {
  assertEquals(5, str.length());
  assertTrue(num >=1 && num <=2);
  assertEquals(2, list.size());
}
static Stream<Arguments> stringIntAndListProvider() {
  return Stream.of(
  arguments("apple", 1, Arrays.asList("a", "b")),
  arguments("lemon", 2, Arrays.asList("x", "y"))
  );
}

/*An external, static factory method can be referenced by providing its fully qualified method name
as demonstrated in the following example.*/
  
package example;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
class ExternalMethodSourceDemo {
  @ParameterizedTest
  @MethodSource("example.StringsProviders#tinyStrings")
  void testWithExternalMethodSource(String tinyString) {
  // test with tiny string
  }
}

class StringsProviders {
  static Stream<String> tinyStrings() {
  return Stream.of(".", "oo", "OOO");
  }
}
