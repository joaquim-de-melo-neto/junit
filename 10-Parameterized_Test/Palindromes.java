@ParameterizedTest
@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
void palindromes(String candidate) {
  assertTrue(StringUtils.isPalindrome(candidate));
}
