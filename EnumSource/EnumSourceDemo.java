@ParameterizedTest
@EnumSource(ChronoUnit.class)
void testWithEnumSource(TemporalUnit unit) {
  assertNotNull(unit);
}

/*The annotation’s value attribute is optional. When omitted, the declared type of the first method
parameter is used. The test will fail if it does not reference an enum type. Thus, the value attribute
is required in the above example because the method parameter is declared as TemporalUnit, i.e. the
interface implemented by ChronoUnit, which isn’t an enum type. Changing the method parameter
type to ChronoUnit allows you to omit the explicit enum type from the annotation as follows.*/

@ParameterizedTest
@EnumSource
void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
  assertNotNull(unit);
}

/*The annotation provides an optional names attribute that lets you specify which constants shall be
used, like in the following example. If omitted, all constants will be used.*/

@ParameterizedTest
@EnumSource(names = { "DAYS", "HOURS" })
void testWithEnumSourceInclude(ChronoUnit unit) {
  assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
}

/*The @EnumSource annotation also provides an optional mode attribute that enables fine-grained
control over which constants are passed to the test method. For example, you can exclude names
from the enum constant pool or specify regular expressions as in the following examples.*/

@ParameterizedTest
@EnumSource(mode = EXCLUDE, names = { "ERAS", "FOREVER" })
void testWithEnumSourceExclude(ChronoUnit unit) {
  assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
}
@ParameterizedTest
@EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
void testWithEnumSourceRegex(ChronoUnit unit) {
  assertTrue(unit.name().endsWith("DAYS"));
}
