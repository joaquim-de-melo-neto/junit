class TestInterfaceDemo implements TestLifecycleLogger,
  TimeExecutionLogger, TestInterfaceDynamicTestsDemo {
  @Test
  void isEqualValue() {
  assertEquals(1, "a".length(), "is always equal");
  }
}
