# log-front-testk

[![Build Status](https://github.com/pwall567/log-front-testk/actions/workflows/build.yml/badge.svg)](https://github.com/pwall567/log-front-testk/actions/workflows/build.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/static/v1?label=Kotlin&message=v2.0.21&color=7f52ff&logo=kotlin&logoColor=7f52ff)](https://github.com/JetBrains/kotlin/releases/tag/v2.0.21)
[![Maven Central](https://img.shields.io/maven-central/v/io.kstuff/log-front-testk?label=Maven%20Central)](https://central.sonatype.com/artifact/io.jstuff/log-front-testk)

Testing functions for the [`log-front-kotlin`](https://github.com/pwall567/log-front-kotlin) logging library.

## Background

When testing a Kotlin application that uses logging, a common requirement is to confirm that logging messages were
output as expected.
This library provides a simple mechanism for testing log output from applications that use the
[`log-front-kotlin`](https://github.com/pwall567/log-front-kotlin) logging library.

## Quick Start

The `LogList` class stores a list of all or selected logging messages output using `log-front-kotlin`.
For example:
```kotlin
        LogList().use { list ->
            val log = getLogger("xxx")
            // code that outputs log message to "log"
            list shouldHaveInfo "Account created"
        }
```

`LogList` makes use of the `LogListener` mechanism in `log-front-kotlin`.
Because `LogListener` implements `AutoCloseable`, the easiest way of using it is within a `use` block; that way the
`LogList` will exist (and be supplied with log events) only for the duration of the test code.

## Reference

### `LogList`

The simplest form of `LogList` intercepts log events from all `Logger` instances:
```kotlin
        val logList = LogList()
```

But sometimes it can be preferable to intercept log events for a selected set of `Logger`s; perhaps just one.
A single `Logger` may be identified by name:
```kotlin
        val logList = LogList("xxx")
```
Or by class:
```kotlin
        val logList = LogList(AccountsService::class)
```
Or for more complex cases, a [`StringMatcher`](https://github.com/pwall567/string-matcher) may be supplied:
```kotlin
        val logList = LogList(StringMatcher.wildcard("Accounts*"))
```

The `LogList` implements `List<LogItem>`, so it is possible to iterate through the list to locate a particular item.
But the more convenient way of examining the log output is to use one of the `shouldHaveXxxx` infix functions:

| Name                        | Argument               | Throws `AssertionError` if the list does not contain...          |
|-----------------------------|------------------------|------------------------------------------------------------------|
| `shouldHaveTrace`           | `Any`                  | ...a TRACE event with the specified message object               |
| `shouldHaveDebug`           | `Any`                  | ...a DEBUG event with the specified message object               |
| `shouldHaveInfo`            | `Any`                  | ...an INFO event with the specified message object               |
| `shouldHaveWarn`            | `Any`                  | ...a WARN event with the specified message object                |
| `shouldHaveError`           | `Any`                  | ...an ERROR event with the specified message object              |
| `shouldHaveTrace`           | `(LogItem) -> Boolean` | ...a TRACE event matching the specified lambda                   |
| `shouldHaveDebug`           | `(LogItem) -> Boolean` | ...a DEBUG event matching the specified lambda                   |
| `shouldHaveInfo`            | `(LogItem) -> Boolean` | ...an INFO event matching the specified lambda                   |
| `shouldHaveWarn`            | `(LogItem) -> Boolean` | ...a WARN event matching the specified lambda                    |
| `shouldHaveError`           | `(LogItem) -> Boolean` | ...an ERROR event matching the specified lambda                  |
| `shouldHaveTraceContaining` | `String`               | ...a TRACE event with a message containing the specified string  |
| `shouldHaveDebugContaining` | `String`               | ...a DEBUG event with a message containing the specified string  |
| `shouldHaveInfoContaining`  | `String`               | ...an INFO event with a message containing the specified string  |
| `shouldHaveWarnContaining`  | `String`               | ...a WARN event with a message containing the specified string   |
| `shouldHaveErrorContaining` | `String`               | ...an ERROR event with a message containing the specified string |
| `shouldHaveTraceMatching`   | `Regex`                | ...a TRACE event with a message matching the specified `Regex`   |
| `shouldHaveDebugMatching`   | `Regex`                | ...a DEBUG event with a message matching the specified `Regex`   |
| `shouldHaveInfoMatching`    | `Regex`                | ...an INFO event with a message matching the specified `Regex`   |
| `shouldHaveWarnMatching`    | `Regex`                | ...a WARN event with a message matching the specified `Regex`    |
| `shouldHaveErrorMatching`   | `Regex`                | ...an ERROR event with a message matching the specified `Regex`  |

### `LogItem`

For those needing to perform more detailed checks on the log entries, the `LogList` stores log events as `LogItem`s,
which contain the following:

| Name        | Type         | Description                                           |
|-------------|--------------|-------------------------------------------------------|
| `time`      | `Long`       | the time in milliseconds from the standard epoch      |
| `name`      | `String`     | the `Logger` name                                     |
| `level`     | `Level`      | the level of the logging event                        |
| `message`   | `Any?`       | the log message                                       |
| `throwable` | `Throwable?` | the `Throwable` associated with the log event, if any |

All of the logging functions take a message in the form of an `Any?`, as described in the documentation for
[`log-front-kotlin`](https://github.com/pwall567/log-front-kotlin).
The `Any?` form is provided in the `LogItem`, allowing the consumer of the `LogItem` to take advantage of any inherent
structure in the message.

A convenience property `messageString` gets the string form of the message (`null` will be returned as an empty string).

In addition to the usual `toString()` method, `LogItem` has three overloaded versions:

- `toString(separator: Char)`
- `toString(zoneId: ZoneId)`
- `toString(separator: Char, zoneId: ZoneId)`

The `toString()` is intended mainly for debugging, and these additional functions allow the separator (the default is
space) and the `ZoneId` to be used when formatting the time (the default is the current default time zone) to be
specified.

There are also a number of extension functions on `LogItem` for checking the log output:

- `LogItem.isTrace(test: (LogItem) -> Boolean)`
- `LogItem.isTrace(message: Any?)`
- `LogItem.isTraceContaining(text: String)`
- `LogItem.isTraceMatching(regex: Regex)`
- `LogItem.isDebug(test: (LogItem) -> Boolean)`
- `LogItem.isDebug(message: Any?)`
- `LogItem.isDebugContaining(text: String)`
- `LogItem.isDebugMatching(regex: Regex)`
- `LogItem.isInfo(test: (LogItem) -> Boolean)`
- `LogItem.isInfo(message: Any?)`
- `LogItem.isInfoContaining(text: String)`
- `LogItem.isInfoMatching(regex: Regex)`
- `LogItem.isWarning(test: (LogItem) -> Boolean)`
- `LogItem.isWarning(message: Any?)`
- `LogItem.isWarningContaining(text: String)`
- `LogItem.isWarningMatching(regex: Regex)`
- `LogItem.isError(test: (LogItem) -> Boolean)`
- `LogItem.isError(message: Any?)`
- `LogItem.isErrorContaining(text: String)`
- `LogItem.isErrorMatching(regex: Regex)`

These infix functions all return `Boolean`, and they test whether a `LogItem` matches the specified test.
The most general-purpose form is the one that takes a lambda:
```kotlin
    if (logItem isError { it.message == "Bad request" && it.throwable == null }) {
        // found it
    }
```
Or to check just the message:
```kotlin
    if (logItem isInfo "OK") {
        // do something
    }
```
Very often, the message contains variable data, and the test just needs to check the constant portion:
```kotlin
    if (logItem isDebugContaining "Balance=") {
        // do something
    }
```
Or to perform a similar test using a `Regex`:
```kotlin
    if (logItem isWarningMatching Regex("Balance [0-9]{1,8}\\.[0-9]{2} DR")) {
        // do something
    }
```

## Dependency Specification

The latest version of the library is 6.2 (the version number of this library matches the version of `log-front-kotlin`
to which it relates), and it may be obtained from the Maven Central repository.
(The following dependency declarations assume that the library will be included for test purposes; this is expected to
be its principal use.)

### Maven
```xml
    <dependency>
      <groupId>io.kstuff</groupId>
      <artifactId>log-front-testk</artifactId>
      <version>6.2</version>
      <scope>test</scope>
    </dependency>
```
### Gradle
```groovy
    testImplementation 'io.kstuff:log-front-testk:6.2'
```
### Gradle (kts)
```kotlin
    testImplementation("io.kstuff:log-front-testk:6.2")
```

Peter Wall

2025-02-14
