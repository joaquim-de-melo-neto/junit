/*To use a custom aggregator, implement the ArgumentsAggregator interface and register it via the
@AggregateWith annotation on a compatible parameter in the @ParameterizedTest method. The result
of the aggregation will then be provided as an argument for the corresponding parameter when the
parameterized test is invoked. Note that an implementation of ArgumentsAggregator must be
declared as either a top-level class or as a static nested class.*/

@ParameterizedTest
@CsvSource({
  "Jane, Doe, F, 1990-05-20",
  "John, Doe, M, 1990-10-22"
})
void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person)
{
  // perform assertions against person
}

public class PersonAggregator implements ArgumentsAggregator {
  @Override
  public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext
context) {
  return new Person(arguments.getString(0),
  arguments.getString(1),
  arguments.get(2, Gender.class),
  arguments.get(3, LocalDate.class));
  }
}
