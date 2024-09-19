/*By default, each argument provided to a @ParameterizedTest method corresponds to a single method
parameter. Consequently, argument sources which are expected to supply a large number of
arguments can lead to large method signatures.
In such cases, an ArgumentsAccessor can be used instead of multiple parameters. Using this API, you
can access the provided arguments through a single argument passed to your test method. In
addition, type conversion is supported as discussed in Implicit Conversion.*/

@ParameterizedTest
@CsvSource({
  "Jane, Doe, F, 1990-05-20",
  "John, Doe, M, 1990-10-22"
})
void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
  Person person = new Person(arguments.getString(0),
  arguments.getString(1),
  arguments.get(2, Gender.class),
  arguments.get(3, LocalDate.class));
  if (person.getFirstName().equals("Jane")) {
  assertEquals(Gender.F, person.getGender());
  }
  else {
  assertEquals(Gender.M, person.getGender());
  }
  assertEquals("Doe", person.getLastName());
  assertEquals(1990, person.getDateOfBirth().getYear());
}
