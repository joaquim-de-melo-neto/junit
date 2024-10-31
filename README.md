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

## Test Classes and Methods

**Test Class**: any top-level class, static member class, or @Nested class that contains at least one test
method. Test classes must not be abstract and must have a single constructor.\
**Test Method**: any instance method that is directly annotated or meta-annotated with @Test,
@RepeatedTest, @ParameterizedTest, @TestFactory, or @TestTemplate.\
**Lifecycle Method**: any method that is directly annotated or meta-annotated with @BeforeAll,
@AfterAll, @BeforeEach, or @AfterEach.

Test methods and lifecycle methods may be declared locally within the current test class, inherited
from superclasses, or inherited from interfaces (see Test Interfaces and Default Methods). In
addition, test methods and lifecycle methods must not be abstract and must not return a value.

Test classes, test methods, and lifecycle methods are not required to be public, but
they must not be private

## Display Names

JUnit Jupiter supports custom display name generators that can be configured via the
@DisplayNameGeneration annotation. Values provided via @DisplayName annotations always take
precedence over display names generated by a DisplayNameGenerator.

You can use the junit.jupiter.displayname.generator.default configuration parameter to specify
the fully qualified class name of the DisplayNameGenerator you would like to use by default. Just like
for display name generators configured via the @DisplayNameGeneration annotation, the supplied
class has to implement the DisplayNameGenerator interface. The default display name generator will
be used for all tests unless the @DisplayNameGeneration annotation is present on an enclosing test
class or test interface. Values provided via @DisplayName annotations always take precedence over
display names generated by a DisplayNameGenerator

For example, to use the ReplaceUnderscores display name generator by default, you should set the
configuration parameter to the corresponding fully qualified class name (e.g., in
src/test/resources/junit-platform.properties):

```
junit.jupiter.displayname.generator.default = org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
```

## Assertions

> Contrary to declarative timeouts, the various assertTimeoutPreemptively() methods
> in the Assertions class execute the provided executable or supplier in a different
> thread than that of the calling code. This behavior can lead to undesirable side
> effects if the code that is executed within the executable or supplier relies on
> java.lang.ThreadLocal storage.
> 
> One common example of this is the transactional testing support in the Spring
> Framework. Specifically, Spring’s testing support binds transaction state to the
> current thread (via a ThreadLocal) before a test method is invoked. Consequently, if
> an executable or supplier provided to assertTimeoutPreemptively() invokes Springmanaged components that > participate in transactions, any actions taken by those
> components will not be rolled back with the test-managed transaction. On the
> contrary, such actions will be committed to the persistent store (e.g., relational
> database) even though the test-managed transaction is rolled back.
> Similar side effects may be encountered with other frameworks that rely on
> ThreadLocal storage.

## Disabling Tests

@Disabled may be declared without providing a reason; however, the JUnit team
recommends that developers provide a short explanation for why a test class or
test method has been disabled. Consequently, the above examples both show the
use of a reason — for example, @Disabled("Disabled until bug #42 has been
resolved"). Some development teams even require the presence of issue tracking
numbers in the reason for automated traceability, etc.

## Conditional Test Execution

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
> method. If a conditional annotation is directly present, indirectly present, or metapresent multiple times on a given element, only the first such annotation
> discovered by JUnit will be used; any additional declarations will be silently
> ignored. Note, however, that each conditional annotation may be used in
> conjunction with other conditional annotations in the
> org.junit.jupiter.api.condition package.

> As of JUnit Jupiter 5.6, @EnabledIfEnvironmentVariable and
> @DisabledIfEnvironmentVariable are repeatable annotations. Consequently, these
> annotations may be declared multiple times on a test interface, test class, or test
> method. Specifically, these annotations will be found if they are directly present,
> indirectly present, or meta-present on a given element.


## Test Execution Order

To control the order in which test methods are executed, annotate your test class or test interface
with @TestMethodOrder and specify the desired MethodOrderer implementation. You can implement
your own custom MethodOrderer or use one of the following built-in MethodOrderer implementations. \
• **Alphanumeric:** sorts test methods alphanumerically based on their names and formal parameter lists. \
• **OrderAnnotation:** sorts test methods numerically based on values specified via the @Order annotation. \
• **Random:** orders test methods pseudo-randomly and supports configuration of a custom seed.

## Test Instance Lifecycle

In order to allow individual test methods to be executed in isolation and to avoid unexpected side
effects due to mutable test instance state, JUnit creates a new instance of each test class before
executing each test method (see Test Classes and Methods). This "per-method" test instance lifecycle
is the default behavior in JUnit Jupiter and is analogous to all previous versions of JUnit.
> Please note that the test class will still be instantiated if a given test method is disabled via a condition (e.g., @Disabled, @DisabledOnOs, etc.) even when the "permethod" test
> instance lifecycle mode is active. 

If you would prefer that JUnit Jupiter execute all test methods on the same test instance, annotate your test class with @TestInstance(Lifecycle.PER_CLASS). When using this mode, a new test instance will be created once per test class. Thus, if your test methods rely on state stored in instance variables, you may need to reset that state in @BeforeEach or @AfterEach methods. The "per-class" mode has some additional benefits over the default "per-method" mode. Specifically, with the "per-class" mode it becomes possible to declare @BeforeAll and @AfterAll on non-static methods as well as on interface default methods. The "per-class" mode therefore also makes it possible to use @BeforeAll and @AfterAll methods in @Nested test classes. 

Note, however, that setting the default test instance lifecycle mode via the JUnit Platform configuration file is a more robust solution since the configuration file can be checked into a version control system along with your project and can therefore be used within IDEs and your
build software.

To set the default test instance lifecycle mode to Lifecycle.PER_CLASS via the JUnit Platform configuration file, create a file named junit-platform.properties in the root of the class path (e.g.,
src/test/resources) with the following content.

```junit.jupiter.testinstance.lifecycle.default = per_class```

## Nested Classes

> Only non-static nested classes (i.e. inner classes) can serve as @Nested 
> test classes. Nesting can be arbitrarily deep, and those inner classes 
> are considered to be full members of the test class family with one 
> exception: @BeforeAll and @AfterAll methods do not work by default. The 
> reason is that Java does not allow static members in inner classes. 
> However, this restriction can be circumvented by annotating a @Nested 
> test class with @TestInstance(Lifecycle.PER_CLASS) (see Test Instance Lifecycle)

## Resolvers and Dependency Injection

In all prior JUnit versions, test constructors or methods were not allowed to have parameters (at least not with the standard Runner implementations). As one of the major changes in JUnit Jupiter,
both test constructors and methods are now permitted to have parameters. This allows for greater flexibility and enables Dependency Injection for constructors and methods.

ParameterResolver defines the API for test extensions that wish to dynamically resolve parameters at
runtime. If a test class constructor, a test method, or a lifecycle method (see Test Classes and Methods) accepts a parameter, the parameter must be resolved at runtime by a registered ParameterResolver.

There are currently three built-in resolvers that are registered automatically.

- TestInfoParameterResolver: if a constructor or method parameter is of type TestInfo, the TestInfoParameterResolver will supply an instance of TestInfo corresponding to the current container or test as the value for the parameter. The TestInfo can then be used to retrieve information about the current container or test such as the display name, the test class, the test method, and associated tags. The display name is either a technical name, such as the name of the test class or test method, or a custom name configured via @DisplayName.

- RepetitionInfoParameterResolver: if a method parameter in a @RepeatedTest, @BeforeEach, or @AfterEach method is of type RepetitionInfo, the RepetitionInfoParameterResolver will supply an
instance of RepetitionInfo. RepetitionInfo can then be used to retrieve information about the current repetition and the total number of repetitions for the corresponding @RepeatedTest.
Note, however, that RepetitionInfoParameterResolver is not registered outside the context of a @RepeatedTest. See Repeated Test Examples.

- TestReporterParameterResolver: if a constructor or method parameter is of type TestReporter, the TestReporterParameterResolver will supply an instance of TestReporter. The TestReporter can be
used to publish additional data about the current test run. The data can be consumed via the reportingEntryPublished() method in a TestExecutionListener, allowing it to be viewed in IDEs or included in reports.

In JUnit Jupiter you should use TestReporter where you used to print information to stdout or stderr in JUnit 4. Using @RunWith(JUnitPlatform.class) will output all reported entries to stdout. In addition, some IDEs print report entries to stdout or display them in the user interface for test results.

> Other parameter resolvers must be explicitly enabled by registering appropriate extensions via @ExtendWith.

Check out the RandomParametersExtension for an example of a custom ParameterResolver. While not
intended to be production-ready, it demonstrates the simplicity and expressiveness of both the
extension model and the parameter resolution process. MyRandomParametersTest demonstrates how
to inject random values into @Test methods

## Test Interface and Default Methods

JUnit Jupiter allows @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate,
@BeforeEach, and @AfterEach to be declared on interface default methods. @BeforeAll and @AfterAll
can either be declared on static methods in a test interface or on interface default methods if the
test interface or test class is annotated with @TestInstance(Lifecycle.PER_CLASS) (see Test Instance
Lifecycle). Here are some examples.

@ExtendWith and @Tag can be declared on a test interface so that classes that implement the interface
automatically inherit its tags and extensions. See Before and After Test Execution Callbacks for the
source code of the TimingExtension.

Running the TestInterfaceDemo results in output similar to the following:
```
INFO example.TestLifecycleLogger - Before all tests
INFO example.TestLifecycleLogger - About to execute [dynamicTestsForPalindromes()]
INFO example.TimingExtension - Method [dynamicTestsForPalindromes] took 19 ms.
INFO example.TestLifecycleLogger - Finished executing [dynamicTestsForPalindromes()]
INFO example.TestLifecycleLogger - About to execute [isEqualValue()]
INFO example.TimingExtension - Method [isEqualValue] took 1 ms.
INFO example.TestLifecycleLogger - Finished executing [isEqualValue()]
INFO example.TestLifecycleLogger - After all tests
```

## Repeated Tests

JUnit Jupiter provides the ability to repeat a test a specified number of times by annotating a
method with @RepeatedTest and specifying the total number of repetitions desired. Each invocation
of a repeated test behaves like the execution of a regular @Test method with full support for the
same lifecycle callbacks and extensions.
The following example demonstrates how to declare a test named repeatedTest() that will be
automatically repeated 10 times.

```
@RepeatedTest(10)
void repeatedTest() {
  // ...
}
```

In addition to specifying the number of repetitions, a custom display name can be configured for
each repetition via the name attribute of the @RepeatedTest annotation. Furthermore, the display
name can be a pattern composed of a combination of static text and dynamic placeholders. The
following placeholders are currently supported.

- {displayName}: display name of the @RepeatedTest method
- {currentRepetition}: the current repetition count
- {totalRepetitions}: the total number of repetitions

The default display name for a given repetition is generated based on the following pattern:
"repetition {currentRepetition} of {totalRepetitions}". Thus, the display names for individual
repetitions of the previous repeatedTest() example would be: repetition 1 of 10, repetition 2 of
10, etc. If you would like the display name of the @RepeatedTest method included in the name of
each repetition, you can define your own custom pattern or use the predefined
RepeatedTest.LONG_DISPLAY_NAME pattern. The latter is equal to "{displayName} :: repetition
{currentRepetition} of {totalRepetitions}" which results in display names for individual
repetitions like repeatedTest() :: repetition 1 of 10, repeatedTest() :: repetition 2 of 10, etc.
In order to retrieve information about the current repetition and the total number of repetitions
programmatically, a developer can choose to have an instance of RepetitionInfo injected into a
@RepeatedTest, @BeforeEach, or @AfterEach method.


## Parameterized Tests

Parameterized tests make it possible to run a test multiple times with different arguments. They are declared just like regular @Test methods but use the @ParameterizedTest annotation instead. In addition, you must declare at least one source that will provide the arguments for each invocation
and then consume the arguments in the test method. 

In order to use parameterized tests you need to add a dependency on the **junit-jupiter-params** artifact. Please refer to Dependency Metadata for details. 

Parameterized test methods typically consume arguments directly from the configured source (see
Sources of Arguments) following a one-to-one correlation between argument source index and
method parameter index (see examples in @CsvSource). However, a parameterized test method
may also choose to aggregate arguments from the source into a single object passed to the method
(see Argument Aggregation). Additional arguments may also be provided by a ParameterResolver
(e.g., to obtain an instance of TestInfo, TestReporter, etc.). Specifically, a parameterized test method
must declare formal parameters according to the following rules.

- Zero or more indexed arguments must be declared first.
- Zero or more aggregators must be declared next.
- Zero or more arguments supplied by a ParameterResolver must be declared last.

In this context, an indexed argument is an argument for a given index in the Arguments provided by
an ArgumentsProvider that is passed as an argument to the parameterized method at the same index
in the method’s formal parameter list. An aggregator is any parameter of type ArgumentsAccessor or
any parameter annotated with @AggregateWith.

## Argument Conversion
### Widening Conversion
JUnit Jupiter supports Widening Primitive Conversion for arguments supplied to a
@ParameterizedTest. For example, a parameterized test annotated with @ValueSource(ints = { 1, 2,
3 }) can be declared to accept not only an argument of type int but also an argument of type long,
float, or double.

### Implicit Conversion
To support use cases like @CsvSource, JUnit Jupiter provides a number of built-in implicit type
converters. The conversion process depends on the declared type of each method parameter.
For example, if a @ParameterizedTest declares a parameter of type TimeUnit and the actual type
supplied by the declared source is a String, the string will be automatically converted into the
corresponding TimeUnit enum constant.

```
@ParameterizedTest
@ValueSource(strings = "SECONDS")
void testWithImplicitArgumentConversion(ChronoUnit argument) {
  assertNotNull(argument.name());
}
```

> String instances are implicitly converted to the several target types. See documentation.

### Fallback String-to-Object Conversion
In addition to implicit conversion from strings to the target types listed in the above table, JUnit
Jupiter also provides a fallback mechanism for automatic conversion from a String to a given target
type if the target type declares exactly one suitable factory method or a factory constructor as
defined below.
- factory method: a non-private, static method declared in the target type that accepts a single
String argument and returns an instance of the target type. The name of the method can be
arbitrary and need not follow any particular convention.
- factory constructor: a non-private constructor in the target type that accepts a single String
argument. Note that the target type must be declared as either a top-level class or as a static
nested class.

> If multiple factory methods are discovered, they will be ignored. If a factory
> method and a factory constructor are discovered, the factory method will be used
> instead of the constructor.

For example, in the following @ParameterizedTest method, the Book argument will be created by
invoking the Book.fromTitle(String) factory method and passing "42 Cats" as the title of the book.

```
@ParameterizedTest
@ValueSource(strings = "42 Cats")
void testWithImplicitFallbackArgumentConversion(Book book) {
  assertEquals("42 Cats", book.getTitle());
}
public class Book {
  private final String title;
  private Book(String title) {
    this.title = title;
  }
  public static Book fromTitle(String title) { // factory method
    return new Book(title);
  }
  public String getTitle() {
    return this.title;
  }
}
```

Instead of relying on implicit argument conversion you may explicitly specify an ArgumentConverter
to use for a certain parameter using the @ConvertWith annotation like in the following example. Note
that an implementation of ArgumentConverter must be declared as either a top-level class or as a
static nested class.

```
@ParameterizedTest
@EnumSource(ChronoUnit.class)
void testWithExplicitArgumentConversion(@ConvertWith(ToStringArgumentConverter.class) String argument) {
    assertNotNull(ChronoUnit.valueOf(argument));
}
```

```
public class ToStringArgumentConverter extends SimpleArgumentConverter {
  @Override
  protected Object convert(Object source, Class<?> targetType) {
    assertEquals(String.class, targetType, "Can only convert to String");
    if (source instanceof Enum<?>) {
      return ((Enum<?>) source).name();
    }
    return String.valueOf(source);
  }
}
```

Explicit argument converters are meant to be implemented by test and extension authors. Thus,
junit-jupiter-params only provides a single explicit argument converter that may also serve as a
reference implementation: JavaTimeArgumentConverter. It is used via the composed annotation
JavaTimeConversionPattern.

```
@ParameterizedTest
@ValueSource(strings = { "01.01.2017", "31.12.2017" })
void testWithExplicitJavaTimeConverter(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
  assertEquals(2017, argument.getYear());
}
```

To apply the same timeout to all test methods within a test class and all of its @Nested classes, you
can declare the @Timeout annotation at the class level. It will then be applied to all test, test factory,
and test template methods within that class and its @Nested classes unless overridden by a @Timeout
annotation on a specific method or @Nested class. Please note that @Timeout annotations declared at
the class level are not applied to lifecycle methods.  

Declaring @Timeout on a @TestFactory method checks that the factory method returns within the
specified duration but does not verify the execution time of each individual DynamicTest generated
by the factory. Please use assertTimeout() or assertTimeoutPreemptively() for that purpose.
If @Timeout is present on a @TestTemplate method — for example, a @RepeatedTest or
@ParameterizedTest — each invocation will have the given timeout applied to it.
