class JreCondition{

@Test
@EnabledOnJre(JAVA_8)
void onlyOnJava8() {
  // ...
}
@Test
@EnabledOnJre({ JAVA_9, JAVA_10 })
void onJava9Or10() {
  // ...
}
24
@Test
@EnabledForJreRange(min = JAVA_9, max = JAVA_11)
void fromJava9to11() {
  // ...
}
@Test
@EnabledForJreRange(min = JAVA_9)
void fromJava9toCurrentJavaFeatureNumber() {
  // ...
}
@Test
@EnabledForJreRange(max = JAVA_11)
void fromJava8To11() {
  // ...
}
@Test
@DisabledOnJre(JAVA_9)
void notOnJava9() {
  // ...
}
@Test
@DisabledForJreRange(min = JAVA_9, max = JAVA_11)
void notFromJava9to11() {
  // ...
}
@Test
@DisabledForJreRange(min = JAVA_9)
void notFromJava9toCurrentJavaFeatureNumber() {
  // ...
}
@Test
@DisabledForJreRange(max = JAVA_11)
void notFromJava8to11() {
  // ...
}
}
