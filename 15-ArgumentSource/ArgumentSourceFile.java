/*@ArgumentsSource can be used to specify a custom, reusable ArgumentsProvider. Note that an
implementation of ArgumentsProvider must be declared as either a top-level class or as a static
nested class.*/

@ParameterizedTest
@ArgumentsSource(MyArgumentsProvider.class)
void testWithArgumentsSource(String argument) {
  assertNotNull(argument);
}

public class MyArgumentsProvider implements ArgumentsProvider {
  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return Stream.of("apple", "banana").map(Arguments::of);
  }
}
