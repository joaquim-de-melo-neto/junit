public interface TestInterfaceDynamicTestsDemo {
    @TestFactory
    default Stream<DynamicTest> dynamicTestsForPalindromes() {
    return Stream.of("racecar", "radar", "mom", "dad")
    .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
    }
  }
    

