@ParameterizedTest
@CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
void testWithCsvFileSource(String country, int reference) {
  assertNotNull(country);
  assertNotEquals(0, reference);
}

/*The default delimiter is a comma (,), but you can use another character by setting the delimiter
attribute. Alternatively, the delimiterString attribute allows you to use a String delimiter instead of
a single character. However, both delimiter attributes cannot be set simultaneously.

Comments in CSV files
Any line beginning with a # symbol will be interpreted as a comment and will be
ignored.

# two-column.csv
Country, reference
Sweden, 1
Poland, 2
"United States of America", 3

  In contrast to the syntax used in @CsvSource, @CsvFileSource uses a double quote " as the quote
character. See the "United States of America" value in the example above. An empty, quoted value
"" results in an empty String unless the emptyValue attribute is set; whereas, an entirely empty value
is interpreted as a null reference. By specifying one or more nullValues, a custom value can be
interpreted as a null reference. An ArgumentConversionException is thrown if the target type of a null
reference is a primitive type.

An unquoted empty value will always be converted to a null reference regardless
of any custom values configured via the nullValues attribute.*/
