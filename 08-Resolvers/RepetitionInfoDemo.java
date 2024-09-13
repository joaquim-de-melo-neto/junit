class RepeatedTestsDemo {
  private Logger logger = // ...
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
  @RepeatedTest(value = 1, name = "{displayName}
{currentRepetition}/{totalRepetitions}")
  @DisplayName("Repeat!")
  void customDisplayName(TestInfo testInfo) {
  assertEquals("Repeat! 1/1", testInfo.getDisplayName());
  }
  @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
  @DisplayName("Details...")
  void customDisplayNameWithLongPattern(TestInfo testInfo) {
  assertEquals("Details... :: repetition 1 of 1", testInfo.getDisplayName());
  }
  @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von
{totalRepetitions}")
  void repeatedTestInGerman() {
  // ...
  }
}

/*
                    OUTPUT
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
