class OperatingSystemClassDemo{

@Test
@EnabledOnOs(MAC)
void onlyOnMacOs() {
  // ...
}
@TestOnMac
void testOnMac() {
  // ...
}
@Test
@EnabledOnOs({ LINUX, MAC })
void onLinuxOrMac() {
  // ...
}
@Test
@DisabledOnOs(WINDOWS)
void notOnWindows() {
  // ...
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@EnabledOnOs(MAC)
@interface TestOnMac {
}
}
