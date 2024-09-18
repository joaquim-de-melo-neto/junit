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
