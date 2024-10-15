/*By default, the display name of a parameterized test invocation contains the invocation index and
the String representation of all arguments for that specific invocation. Each of them is preceded by
the parameter name (unless the argument is only available via an ArgumentsAccessor or
ArgumentAggregator), if present in the bytecode (for Java, test code must be compiled with the
-parameters compiler flag).
However, you can customize invocation display names via the name attribute of the
@ParameterizedTest annotation like in the following example.*/

@DisplayName("Display name of container")
@ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
@CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
void testWithCustomDisplayNames(String fruit, int rank) {
}

/*
When executing the above method using the ConsoleLauncher you will see output similar to the
following.
Display name of container
├─ 1 ==> the rank of 'apple' is 1
├─ 2 ==> the rank of 'banana' is 2
└─ 3 ==> the rank of 'lemon, lime' is 3
The following placeholders are supported within custom display names.
Placeholder Description
{displayName} -------- the display name of the method
{index} -------------- the current invocation index (1-based)
{arguments} ---------- the complete, comma-separated arguments list
{argumentsWithNames} - the complete, comma-separated arguments list with parameter names 
{0}, {1}, … ---------- an individual argument
*/
