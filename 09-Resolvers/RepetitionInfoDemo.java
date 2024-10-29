import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

class RepeatedTestsDemo {
  private Logger logger;// ...
  @BeforeEach
  void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
    int currentRepetition = repetitionInfo.getCurrentRepetition();
    int totalRepetitions = repetitionInfo.getTotalRepetitions();
    String methodName = testInfo.getTestMethod().get().getName();
    logger.info(String.format("About to execute repetition %d of %d for %s", //
                                currentRepetition, totalRepetitions, methodName));
  }

  @RepeatedTest(10)
    void repeatedTest() {
  // ...
  }

  @RepeatedTest(5)
  void repeatedTestWithRepetitionInfo(RepetitionInfo repetitionInfo) {
    assertEquals(5, repetitionInfo.getTotalRepetitions());
  }

  @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
  @DisplayName("Repeat!")
  void customDisplayName(TestInfo testInfo) {
    assertEquals("Repeat! 1/1", testInfo.getDisplayName());
  }

  @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
  @DisplayName("Details...")
  void customDisplayNameWithLongPattern(TestInfo testInfo) {
    assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
  }

  @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
  void repeatedTestInGerman() {
  // ...
  }
}

/*
                     LOGS
INFO: About to execute repetition 1 of 10 for repeatedTest
INFO: About to execute repetition 2 of 10 for repeatedTest
INFO: About to execute repetition 3 of 10 for repeatedTest
INFO: About to execute repetition 4 of 10 for repeatedTest
INFO: About to execute repetition 5 of 10 for repeatedTest
INFO: About to execute repetition 6 of 10 for repeatedTest
INFO: About to execute repetition 7 of 10 for repeatedTest
INFO: About to execute repetition 8 of 10 for repeatedTest
INFO: About to execute repetition 9 of 10 for repeatedTest
INFO: About to execute repetition 10 of 10 for repeatedTest
INFO: About to execute repetition 1 of 5 for repeatedTestWithRepetitionInfo
INFO: About to execute repetition 2 of 5 for repeatedTestWithRepetitionInfo
INFO: About to execute repetition 3 of 5 for repeatedTestWithRepetitionInfo
INFO: About to execute repetition 4 of 5 for repeatedTestWithRepetitionInfo
INFO: About to execute repetition 5 of 5 for repeatedTestWithRepetitionInfo
INFO: About to execute repetition 1 of 1 for customDisplayName
INFO: About to execute repetition 1 of 1 for customDisplayNameWithLongPattern
INFO: About to execute repetition 1 of 5 for repeatedTestInGerman
INFO: About to execute repetition 2 of 5 for repeatedTestInGerman
INFO: About to execute repetition 3 of 5 for repeatedTestInGerman
INFO: About to execute repetition 4 of 5 for repeatedTestInGerman
INFO: About to execute repetition 5 of 5 for repeatedTestInGerman
*/

/*
        TEST REPORTS
├─ RepeatedTestsDemo
│ ├─ repeatedTest()
│ │ ├─ repetition 1 of 10
│ │ ├─ repetition 2 of 10
│ │ ├─ repetition 3 of 10
│ │ ├─ repetition 4 of 10
│ │ ├─ repetition 5 of 10
│ │ ├─ repetition 6 of 10
│ │ ├─ repetition 7 of 10
│ │ ├─ repetition 8 of 10
│ │ ├─ repetition 9 of 10
│ │ └─ repetition 10 of 10
│ ├─ repeatedTestWithRepetitionInfo(RepetitionInfo)
│ │ ├─ repetition 1 of 5
│ │ ├─ repetition 2 of 5
│ │ ├─ repetition 3 of 5
│ │ ├─ repetition 4 of 5
│ │ └─ repetition 5 of 5
│ ├─ Repeat!
│ │ └─ Repeat! 1/1
│ ├─ Details...
│ │ └─ Details... :: repetition 1 of 1
│ └─ repeatedTestInGerman()
│ ├─ Wiederholung 1 von 5
│ ├─ Wiederholung 2 von 5
│ ├─ Wiederholung 3 von 5
│ ├─ Wiederholung 4 von 5
│ └─ Wiederholung 5 von 5 
*/
