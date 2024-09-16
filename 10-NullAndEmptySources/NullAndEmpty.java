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
