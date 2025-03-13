/*
 * @(#) LogItemExtTest.kt
 *
 * log-front-kotlin  Logging interface in Kotlin
 * Copyright (c) 2025 Peter Wall
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

package io.kstuff.log.test

import kotlin.test.Test

import io.kstuff.test.shouldBe

import io.kstuff.log.Level
import io.kstuff.log.LogItem
import io.kstuff.log.isDebug
import io.kstuff.log.isDebugContaining
import io.kstuff.log.isDebugMatching
import io.kstuff.log.isError
import io.kstuff.log.isErrorContaining
import io.kstuff.log.isErrorMatching
import io.kstuff.log.isInfo
import io.kstuff.log.isInfoContaining
import io.kstuff.log.isInfoMatching
import io.kstuff.log.isTrace
import io.kstuff.log.isTraceContaining
import io.kstuff.log.isTraceMatching
import io.kstuff.log.isWarning
import io.kstuff.log.isWarningContaining
import io.kstuff.log.isWarningMatching

class LogItemExtTest {

    @Test fun `should check whether LogItem is trace`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.TRACE, "Hello", null)
        logItem isTrace { it.message == "Hello" } shouldBe true
        logItem isTrace "Hello" shouldBe true
        logItem isTraceContaining  "Hell" shouldBe true
        logItem isTraceMatching Regex("^H") shouldBe true
        logItem isDebug "Hello" shouldBe false
        logItem isTrace "Goodbye" shouldBe false
        logItem isTraceMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is debug`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.DEBUG, "Hello", null)
        logItem isDebug { it.message == "Hello" } shouldBe true
        logItem isDebug "Hello" shouldBe true
        logItem isDebugContaining  "ell" shouldBe true
        logItem isDebugMatching Regex("^H") shouldBe true
        logItem isInfo "Hello" shouldBe false
        logItem isDebug "Goodbye" shouldBe false
        logItem isDebugMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is info`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.INFO, "Hello", null)
        logItem isInfo { it.message == "Hello" } shouldBe true
        logItem isInfo "Hello" shouldBe true
        logItem isInfoContaining  "He" shouldBe true
        logItem isInfoMatching Regex("^H") shouldBe true
        logItem isWarning "Hello" shouldBe false
        logItem isInfo "Goodbye" shouldBe false
        logItem isInfoMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is warning`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.WARN, "Hello", null)
        logItem isWarning { it.message == "Hello" } shouldBe true
        logItem isWarning "Hello" shouldBe true
        logItem isWarningContaining  "lo" shouldBe true
        logItem isWarningMatching Regex("^H") shouldBe true
        logItem isError "Hello" shouldBe false
        logItem isWarning "Goodbye" shouldBe false
        logItem isWarningMatching Regex("^Hello world") shouldBe false
    }

    @Test fun `should check whether LogItem is error`() {
        val logItem = LogItem(System.currentTimeMillis(), "xyz", Level.ERROR, "Hello", null)
        logItem isError { it.message == "Hello" } shouldBe true
        logItem isError "Hello" shouldBe true
        logItem isErrorContaining  "el" shouldBe true
        logItem isErrorMatching Regex("^H") shouldBe true
        logItem isWarning  "Hello" shouldBe false
        logItem isError "Goodbye" shouldBe false
        logItem isErrorMatching Regex("^Hello world") shouldBe false
    }

}
