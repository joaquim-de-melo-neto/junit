import static example.util.StringUtils.isPalindrome;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

class DynamicTestsDemo {

  private final Calculator calculator = new Calculator();

  // The following DynamicTestsDemo class demonstrates
  // several examples of test factories and dynamic tests.
  // The first method returns an invalid return type. Since an invalid return type
  // cannot be detected at compile time, a JUnitException is thrown when it is detected at runtime.
  // This will result in a JUnitException!
  @TestFactory
  List<String> dynamicTestsWithInvalidReturnType() {
    return Arrays.asList("Hello");
  }

  // The next four methods are very simple examples that demonstrate the
  // generation of a Collection, Iterable, Iterator, or Stream of DynamicTest instances. Most of these
  // examples do not really exhibit dynamic behavior but merely demonstrate the supported return types in
  // principle.
  @TestFactory
  Collection<DynamicTest> dynamicTestsFromCollection() {
    return Arrays.asList(
        dynamicTest("1st dynamic test", () -> assertTrue(isPalindrome("madam"))),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, calculator.multiply(2, 2))));
  }

  @TestFactory
  Iterable<DynamicTest> dynamicTestsFromIterable() {
    return Arrays.asList(
        dynamicTest("3rd dynamic test", () -> assertTrue(isPalindrome("madam"))),
        dynamicTest("4th dynamic test", () -> assertEquals(4, calculator.multiply(2, 2))));
  }

  @TestFactory
  Iterator<DynamicTest> dynamicTestsFromIterator() {
    return Arrays.asList(
        dynamicTest("5th dynamic test", () -> assertTrue(isPalindrome("madam"))),
        dynamicTest("6th dynamic test", () -> assertEquals(4, calculator.multiply(2, 2))))
        .iterator();
  }

  @TestFactory
  DynamicTest[] dynamicTestsFromArray() {
    return new DynamicTest[] {
        dynamicTest("7th dynamic test", () -> assertTrue(isPalindrome("madam"))),
        dynamicTest("8th dynamic test", () -> assertEquals(4, calculator.multiply(2, 2))) };
  }

  // dynamicTestsFromStream() and dynamicTestsFromIntStream() demonstrate how easy
  // it is to generate dynamic tests for a given set of strings or a range of input numbers.
  @TestFactory
  Stream<DynamicTest> dynamicTestsFromStream() {
    return Stream.of("racecar", "radar", "mom", "dad")
        .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text))));
  }

  @TestFactory
  Stream<DynamicTest> dynamicTestsFromIntStream() {

    // Generates tests for the first 10 even integers.
    return IntStream.iterate(0, n -> n + 2).limit(10)
        .mapToObj(n -> dynamicTest("test" + n, () -> assertTrue(n % 2 == 0)));
  }

  // The next method is truly dynamic in nature. generateRandomNumberOfTests()
  // implements an Iterator that generates random numbers, a display name generator, and a test executor
  // and then provides all three to DynamicTest.stream(). Although the non-deterministic behavior of
  // generateRandomNumberOfTests() is of course in conflict with test repeatability and should thus be
  // used with care, it serves to demonstrate the expressiveness and power of dynamic tests.
  @TestFactory
  Stream<DynamicTest> generateRandomNumberOfTests() {
    // Generates random positive integers between 0 and 100 until a number evenly
    // divisible by 7 is encountered.
    Iterator<Integer> inputGenerator = new Iterator<Integer>() {
      Random random = new Random();
      int current;

      @Override
      public boolean hasNext() {
        current = random.nextInt(100);
        return current % 7 != 0;
      }

      @Override
      public Integer next() {
        return current;
      }

    };
    // Generates display names like: input:5, input:37, input:85, etc.
    Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;
    // Executes tests based on the current input value.
    ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);
    // Returns a stream of dynamic tests.
    return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
  }

  // The last method generates a nested hierarchy of dynamic tests utilizing DynamicContainer.
  @TestFactory
  Stream<DynamicNode> dynamicTestsWithContainers() {
    return Stream.of("A", "B", "C")
        .map(input -> dynamicContainer("Container " + input, Stream.of(
            dynamicTest("not null", () -> assertNotNull(input)),
            dynamicContainer("properties", Stream.of(
                dynamicTest("length > 0", () -> assertTrue(input.length() > 0)),
                dynamicTest("not empty", () -> assertFalse(input.isEmpty())))))));
  }

  @TestFactory
  DynamicNode dynamicNodeSingleTest() {
    return dynamicTest("'pop' is a palindrome", () -> assertTrue(isPalindrome("pop")));
  }

  @TestFactory
  DynamicNode dynamicNodeSingleContainer() {
    return dynamicContainer("palindromes",
        Stream.of("racecar", "radar", "mom", "dad")
            .map(text -> dynamicTest(text, () -> assertTrue(isPalindrome(text)))));
  }
}
