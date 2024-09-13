# JUnit
Annotation | Description
-- | --
@Test | Denotes that a method is a test method. Unlike JUnit 4’s @Test annotation, this annotation does not declare any attributes, since test extensions in JUnit Jupiter operate based on their own dedicated annotations. Such methods are inherited unless they are overridden.
@ParameterizedTest | Denotes that a method is a parameterized test. Such methods are inherited unless they are overridden. 
@RepeatedTest | Denotes that a method is a test template for a repeated test. Such methods are inherited unless they are overridden.
@TestFactory | Denotes that a method is a test factory for dynamic tests. Such methods are inherited unless they are overridden.
@TestTemplate | Denotes that a method is a template for test cases designed to be invoked multiple times depending on the number of invocation contexts returned by the registered providers. Such methods are inherited unless they are overridden.
@TestMethodOrder | Used to configure the test method execution order for the annotated test class; similar to JUnit 4’s @FixMethodOrder. Such annotations are inherited.
@TestInstance | Used to configure the test instance lifecycle for the annotated test class. Such annotations are inherited.
@DisplayName | Declares a custom display name for the test class or test method. Such annotations are not inherited.
@DisplayNameGeneration | Declares a custom display name generator for the test class. Such annotations are inherited.
@BeforeEach | Denotes that the annotated method should be executed before each @Test, @RepeatedTest, @ParameterizedTest, or @TestFactory method in the current class; analogous to JUnit 4’s @Before. Such methods are inherited unless they are overridden.
@AfterEach | Denotes that the annotated method should be executed after each @Test, @RepeatedTest, @ParameterizedTest, or @TestFactory method in the current class; analogous to JUnit 4’s @After. Such methods are inherited unless they are overridden.
@BeforeAll | Denotes that the annotated method should be executed before all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class; analogous to JUnit 4’s @BeforeClass. Such methods are inherited (unless they are hidden or overridden) and must be static (unless the "per-class" test instance lifecycle is used).
@AfterAll | Denotes that the annotated method should be executed after all @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class; analogous to JUnit 4’s @AfterClass. Such methods are inherited (unless they are hidden or overridden) and must be static (unless the "per-class" test instance lifecycle is used).
@Nested | Denotes that the annotated class is a non-static nested test class. @BeforeAll and @AfterAll methods cannot be used directly in a @Nested test class unless the "per-class" test instance lifecycle is used. Such annotations are not inherited.
@Tag | Used to declare tags for filtering tests, either at the class or method level; analogous to test groups in TestNG or Categories in JUnit 4. Such annotations are inherited at the class level but not at the method level.
@Disabled | Used to disable a test class or test method; analogous to JUnit 4’s @Ignore. Such annotations are not inherited. 
@Timeout | Used to fail a test, test factory, test template, or lifecycle method if its execution exceeds a given duration. Such annotations are inherited.
@ExtendWith | Used to register extensions declaratively. Such annotations are inherited.
@RegisterExtension | Used to register extension programmatically via fields. Such fields are inherited unless they are shadowed.
@TempDir | Used to supply a temporary directory via field injection or parameter injection in a lifecycle method or test method; located in the org.junit.jupiter.api.io package.

**Test Class**: any top-level class, static member class, or @Nested class that contains at least one test
method. Test classes must not be abstract and must have a single constructor.\
**Test Method**: any instance method that is directly annotated or meta-annotated with @Test,
@RepeatedTest, @ParameterizedTest, @TestFactory, or @TestTemplate.\
**Lifecycle Method**: any method that is directly annotated or meta-annotated with @BeforeAll,
@AfterAll, @BeforeEach, or @AfterEach.

---

@Disabled may be declared without providing a reason; however, the JUnit team
recommends that developers provide a short explanation for why a test class or
test method has been disabled. Consequently, the above examples both show the
use of a reason — for example, @Disabled("Disabled until bug #42 has been
resolved"). Some development teams even require the presence of issue tracking
numbers in the reason for automated traceability, etc.

---

The ExecutionCondition extension API in JUnit Jupiter allows developers to either enable or disable a
container or test based on certain conditions programmatically. The simplest example of such a
condition is the built-in DisabledCondition which supports the @Disabled annotation (see Disabling
Tests). In addition to @Disabled, JUnit Jupiter also supports several other annotation-based
conditions in the org.junit.jupiter.api.condition package that allow developers to enable or
disable containers and tests declaratively. When multiple ExecutionCondition extensions are
registered, a container or test is disabled as soon as one of the conditions returns disabled.
See ExecutionCondition and the following sections for details.

> Composed Annotations \
> Note that any of the conditional annotations listed in the following sections may
> also be used as a meta-annotation in order to create a custom composed
> annotation. For example, the @TestOnMac annotation in the @EnabledOnOs demo
> shows how you can combine @Test and @EnabledOnOs in a single, reusable
> annotation.

> Unless otherwise stated, each of the conditional annotations listed in the following
> sections can only be declared once on a given test interface, test class, or test
> method. If a conditional annotation is directly present, indirectly present, or metapresent multiple times on a given element, only the > first such annotation
> discovered by JUnit will be used; any additional declarations will be silently
> ignored. Note, however, that each conditional annotation may be used in
> conjunction with other conditional annotations in the
> org.junit.jupiter.api.condition package.

---

To control the order in which test methods are executed, annotate your test class or test interface
with @TestMethodOrder and specify the desired MethodOrderer implementation. You can implement
your own custom MethodOrderer or use one of the following built-in MethodOrderer implementations. \
• **Alphanumeric:** sorts test methods alphanumerically based on their names and formal parameter lists. \
• **OrderAnnotation:** sorts test methods numerically based on values specified via the @Order annotation. \
• **Random:** orders test methods pseudo-randomly and supports configuration of a custom seed.

---


