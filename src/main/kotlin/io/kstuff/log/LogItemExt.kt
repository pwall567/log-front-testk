/*
 * @(#) LogItemExt.kt
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

/**
 * A log item, as used by the [LogList] class.  This is an effectively immutable object.
 */
typealias LogItem = io.jstuff.log.LogItem

/**
 * Test whether a [LogItem] has level `TRACE` and matches the specified test.
 */
infix fun LogItem.isTrace(test: (LogItem) -> Boolean): Boolean = level == Level.TRACE && test(this)

/**
 * Test whether a [LogItem] has level `TRACE` and the specified message.
 */
infix fun LogItem.isTrace(message: Any?): Boolean = isTrace { it.message == message }

/**
 * Test whether a [LogItem] has level `TRACE` and the message contains the specified text.
 */
infix fun LogItem.isTraceContaining(text: String): Boolean = isTrace { it.messageString.contains(text)  }

/**
 * Test whether a [LogItem] has level `TRACE` and the message matches the specified [Regex].
 */
infix fun LogItem.isTraceMatching(regex: Regex): Boolean = isTrace { regex.containsMatchIn(it.messageString) }

/**
 * Test whether a [LogItem] has level `DEBUG` and matches the specified test.
 */
infix fun LogItem.isDebug(test: (LogItem) -> Boolean): Boolean = level == Level.DEBUG && test(this)

/**
 * Test whether a [LogItem] has level `DEBUG` and the specified message.
 */
infix fun LogItem.isDebug(message: Any?): Boolean = isDebug { it.message == message }

/**
 * Test whether a [LogItem] has level `DEBUG` and the message contains the specified text.
 */
infix fun LogItem.isDebugContaining(text: String): Boolean = isDebug { it.messageString.contains(text)  }

/**
 * Test whether a [LogItem] has level `DEBUG` and the message matches the specified [Regex].
 */
infix fun LogItem.isDebugMatching(regex: Regex): Boolean = isDebug { regex.containsMatchIn(it.messageString) }

/**
 * Test whether a [LogItem] has level `INFO` and matches the specified test.
 */
infix fun LogItem.isInfo(test: (LogItem) -> Boolean): Boolean = level == Level.INFO && test(this)

/**
 * Test whether a [LogItem] has level `INFO` and the specified message.
 */
infix fun LogItem.isInfo(message: Any?): Boolean = isInfo { it.message == message }

/**
 * Test whether a [LogItem] has level `INFO` and the message contains the specified text.
 */
infix fun LogItem.isInfoContaining(text: String): Boolean = isInfo { it.messageString.contains(text) }

/**
 * Test whether a [LogItem] has level `INFO` and the message matches the specified [Regex].
 */
infix fun LogItem.isInfoMatching(regex: Regex): Boolean = isInfo { regex.containsMatchIn(it.messageString) }

/**
 * Test whether a [LogItem] has level `WARN` and matches the specified test.
 */
infix fun LogItem.isWarning(test: (LogItem) -> Boolean): Boolean = level == Level.WARN && test(this)

/**
 * Test whether a [LogItem] has level `WARN` and the specified message.
 */
infix fun LogItem.isWarning(message: Any?): Boolean = isWarning { it.message == message }

/**
 * Test whether a [LogItem] has level `WARN` and the message contains the specified text.
 */
infix fun LogItem.isWarningContaining(text: String): Boolean = isWarning { it.messageString.contains(text) }

/**
 * Test whether a [LogItem] has level `WARN` and the message matches the specified [Regex].
 */
infix fun LogItem.isWarningMatching(regex: Regex): Boolean = isWarning { regex.containsMatchIn(it.messageString) }

/**
 * Test whether a [LogItem] has level `ERROR` and matches the specified test.
 */
infix fun LogItem.isError(test: (LogItem) -> Boolean): Boolean = level == Level.ERROR && test(this)

/**
 * Test whether a [LogItem] has level `ERROR` and the specified message.
 */
infix fun LogItem.isError(message: Any?): Boolean = isError { it.message == message }

/**
 * Test whether a [LogItem] has level `ERROR` and the message contains the specified text.
 */
infix fun LogItem.isErrorContaining(text: String): Boolean = isError { it.messageString.contains(text) }

/**
 * Test whether a [LogItem] has level `ERROR` and the message matches the specified [Regex].
 */
infix fun LogItem.isErrorMatching(regex: Regex): Boolean = isError { regex.containsMatchIn(it.messageString) }
