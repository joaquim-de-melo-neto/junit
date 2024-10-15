@ParameterizedTest
@NullSource
@EmptySource
@ValueSource(strings = { " ", " ", "\t", "\n" })
void nullEmptyAndBlankStrings(String text) {
  assertTrue(text == null || text.trim().isEmpty());
}
//Making use of the composed @NullAndEmptySource annotation simplifies the above as follows.
@ParameterizedTest
@NullAndEmptySource
@ValueSource(strings = { " ", " ", "\t", "\n" })
void nullEmptyAndBlankStrings(String text) {
  assertTrue(text == null || text.trim().isEmpty());
}

/*Both variants of the nullEmptyAndBlankStrings(String) parameterized test method
result in six invocations: 1 for null, 1 for the empty string, and 4 for the explicit
blank strings supplied via @ValueSource.*/
