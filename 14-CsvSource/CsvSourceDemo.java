@ParameterizedTest
@CsvSource({
  "apple, 1",
  "banana, 2",
  "'lemon, lime', 0xF1"
})
void testWithCsvSource(String fruit, int rank) {
  assertNotNull(fruit);
  assertNotEquals(0, rank);
}

/*The default delimiter is a comma (,), but you can use another character by setting the delimiter
attribute. Alternatively, the delimiterString attribute allows you to use a String delimiter instead of
a single character. However, both delimiter attributes cannot be set simultaneously.
@CsvSource uses a single quote ' as its quote character. See the 'lemon, lime' value in the example
above and in the table below. An empty, quoted value '' results in an empty String unless the
emptyValue attribute is set; whereas, an entirely empty value is interpreted as a null reference. By
specifying one or more nullValues, a custom value can be interpreted as a null reference (see the
NIL example in the table below). An ArgumentConversionException is thrown if the target type of a
null reference is a primitive type.

  An unquoted empty value will always be converted to a null reference regardless
  of any custom values configured via the nullValues attribute.*/

/*
Example Input  Resulting Argument List
@CsvSource({ "apple, banana" }) -------------------------------------- "apple", "banana"
@CsvSource({ "apple, 'lemon, lime'" }) ------------------------------- "apple", "lemon, lime"
@CsvSource({ "apple, ''" }) ------------------------------------------ "apple", ""
@CsvSource({ "apple, " }) -------------------------------------------- "apple", null
@CsvSource(value = { "apple, banana, NIL" },nullValues = "NIL") ------ "apple", "banana", null
*/  
