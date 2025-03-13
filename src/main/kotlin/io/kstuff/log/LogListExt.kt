/*
 * @(#) LogListExt.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2024, 2025 Peter Wall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.kstuff.log

import kotlin.reflect.KClass

/**
 * An implementation of [LogListener] that stores log items in a list.
 */
typealias LogList = io.jstuff.log.LogList

/**
 * Create a [LogList] that filters by [KClass] name.
 */
fun LogList(kClass: KClass<*>): LogList = LogList(kClass.qualifiedName)

/**
 * Filter list by name.
 */
fun Iterable<LogItem>.subList(name: String): List<LogItem> = filter { it.name == name }

/**
 * Filter list by [KClass].
 */
fun Iterable<LogItem>.subList(kClass: KClass<*>): List<LogItem> = kClass.qualifiedName.let { name ->
    filter { it.name == name }
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry matching the given predicate.
 */
infix fun Iterable<LogItem>.shouldHaveTrace(block: (LogItem) -> Boolean) {
    if (!any { item -> item isTrace { block(it) } })
        throw AssertionError("LogList does not contain matching TRACE\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveTrace(text: String) {
    if (!any { it isTrace text })
        throw AssertionError("LogList does not contain TRACE $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveTraceContaining(text: String) {
    if (!any { it isTraceContaining text })
        throw AssertionError("LogList does not contain TRACE containing $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `TRACE` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveTraceMatching(regex: Regex) {
    if (!any { it isTraceMatching regex })
        throw AssertionError("LogList does not contain TRACE $regex\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry matching the given predicate.
 */
infix fun Iterable<LogItem>.shouldHaveDebug(block: (LogItem) -> Boolean) {
    if (!any { item -> item isDebug { block(it) } })
        throw AssertionError("LogList does not contain matching DEBUG\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveDebug(text: String) {
    if (!any { it isDebug text })
        throw AssertionError("LogList does not contain DEBUG $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveDebugContaining(text: String) {
    if (!any { it isDebugContaining  text })
        throw AssertionError("LogList does not contain DEBUG containing $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `DEBUG` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveDebugMatching(regex: Regex) {
    if (!any { it isDebugMatching regex })
        throw AssertionError("LogList does not contain DEBUG $regex\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry matching the given predicate.
 */
infix fun Iterable<LogItem>.shouldHaveInfo(block: (LogItem) -> Boolean) {
    if (!any { item -> item isInfo { block(it) } })
        throw AssertionError("LogList does not contain matching INFO\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveInfo(text: String) {
    if (!any { it isInfo text })
        throw AssertionError("LogList does not contain INFO $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveInfoContaining(text: String) {
    if (!any { it isInfoContaining text })
        throw AssertionError("LogList does not contain INFO containing $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `INFO` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveInfoMatching(regex: Regex) {
    if (!any { it isInfoMatching regex })
        throw AssertionError("LogList does not contain INFO $regex\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry matching the given predicate.
 */
infix fun Iterable<LogItem>.shouldHaveWarning(block: (LogItem) -> Boolean) {
    if (!any { item -> item isWarning { block(it) } })
        throw AssertionError("LogList does not contain matching WARN\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveWarning(text: String) {
    if (!any { it isWarning text })
        throw AssertionError("LogList does not contain WARN $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveWarningContaining(text: String) {
    if (!any { it isWarningContaining text })
        throw AssertionError("LogList does not contain WARN containing $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has a `WARN` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveWarningMatching(regex: Regex) {
    if (!any { it isWarningMatching regex })
        throw AssertionError("LogList does not contain WARN $regex\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry matching the given predicate.
 */
infix fun Iterable<LogItem>.shouldHaveError(block: (LogItem) -> Boolean) {
    if (!any { item -> item isError { block(it) } })
        throw AssertionError("LogList does not contain matching ERROR\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry with the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveError(text: String) {
    if (!any { it isError text })
        throw AssertionError("LogList does not contain ERROR $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry containing the specified text.
 */
infix fun Iterable<LogItem>.shouldHaveErrorContaining(text: String) {
    if (!any { it isErrorContaining text })
        throw AssertionError("LogList does not contain ERROR containing $text\n$lines")
}

/**
 * Test whether a collection of [LogItem] has an `ERROR` entry matching the specified [Regex].
 */
infix fun Iterable<LogItem>.shouldHaveErrorMatching(regex: Regex) {
    if (!any { it isErrorMatching regex })
        throw AssertionError("LogList does not contain ERROR $regex\n$lines")
}

/**
 * Get the collection of [LogItem] as a displayable string.
 */
val Iterable<LogItem>.lines: String
    get() = "log lines:\n" + joinToString(separator = "\n")
