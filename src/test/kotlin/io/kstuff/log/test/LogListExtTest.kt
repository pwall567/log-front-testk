/*
 * @(#) LogListExtTest.kt
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
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

import io.kstuff.test.shouldBe
import io.kstuff.test.shouldBeNonNull
import io.kstuff.test.shouldContain
import io.kstuff.test.shouldThrow

import io.kstuff.log.Level
import io.kstuff.log.LogItem
import io.kstuff.log.LogList
import io.kstuff.log.getLogger
import io.kstuff.log.shouldHaveDebug
import io.kstuff.log.shouldHaveDebugContaining
import io.kstuff.log.shouldHaveDebugMatching
import io.kstuff.log.shouldHaveError
import io.kstuff.log.shouldHaveErrorContaining
import io.kstuff.log.shouldHaveErrorMatching
import io.kstuff.log.shouldHaveInfo
import io.kstuff.log.shouldHaveInfoContaining
import io.kstuff.log.shouldHaveInfoMatching
import io.kstuff.log.shouldHaveTrace
import io.kstuff.log.shouldHaveTraceContaining
import io.kstuff.log.shouldHaveTraceMatching
import io.kstuff.log.shouldHaveWarning
import io.kstuff.log.shouldHaveWarningContaining
import io.kstuff.log.shouldHaveWarningMatching
import io.kstuff.log.subList
import io.kstuff.test.shouldBeSameInstance

class LogListExtTest {

    @Test fun `should construct LogItem`() {
        val localDate: LocalDate = LocalDate.now()
        val localTime: LocalTime = LocalTime.of(12, 34, 56, 789000000)
        val zonedDateTime: ZonedDateTime = ZonedDateTime.of(localDate, localTime, zone)
        val time = zonedDateTime.toInstant().toEpochMilli()
        with(LogItem(time, name, level, text, null)) {
            this.time shouldBe time
            this.name shouldBe Companion.name
            this.level shouldBe Companion.level
            this.message shouldBe text
            throwable shouldBe null
        }
        val throwable = Throwable("Error text")
        with(LogItem(time, name, level, text, throwable)) {
            this.time shouldBe time
            this.name shouldBe Companion.name
            this.level shouldBe Companion.level
            this.message shouldBe text
            this.throwable shouldBeSameInstance throwable
        }
    }

    @Test fun `should check whether LogList contains TRACE item matching test`() {
        LogList().use { list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list shouldHaveTrace { it.message == "Trace message" }
            shouldThrow<AssertionError> { list shouldHaveTrace { it.message == "Wrong" } }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain matching TRACE"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item with given message`() {
        LogList().use { list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list shouldHaveTrace "Trace message"
            shouldThrow<AssertionError> { list shouldHaveTrace "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item containing string`() {
        LogList().use { list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list shouldHaveTraceContaining "mess"
            shouldThrow<AssertionError> { list shouldHaveTraceContaining "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains TRACE item matching Regex`() {
        LogList().use { list ->
            val logger = getLogger("LogTrace", Level.TRACE)
            logger.trace { "Trace message" }
            list shouldHaveTraceMatching Regex("Trace")
            shouldThrow<AssertionError> { list shouldHaveTraceMatching Regex("Wrong") }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain TRACE Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item matching test`() {
        LogList().use { list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list shouldHaveDebug { it.message == "Debug message" }
            shouldThrow<AssertionError> { list shouldHaveDebug { it.message == "Wrong" } }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain matching DEBUG"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item with given message`() {
        LogList().use { list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list shouldHaveDebug "Debug message"
            shouldThrow<AssertionError> { list shouldHaveDebug "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item containing string`() {
        LogList().use { list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list shouldHaveDebugContaining "bug"
            shouldThrow<AssertionError> { list shouldHaveDebugContaining "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains DEBUG item matching Regex`() {
        LogList().use { list ->
            val logger = getLogger("LogDebug", Level.DEBUG)
            logger.debug { "Debug message" }
            list shouldHaveDebugMatching Regex("Debug")
            shouldThrow<AssertionError> { list shouldHaveDebugMatching Regex("Wrong") }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain DEBUG Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item matching test`() {
        LogList().use { list ->
            companionObjectLogger.info { "Message" }
            list shouldHaveInfo { it.message == "Message" }
            shouldThrow<AssertionError> { list shouldHaveInfo { it.message == "Wrong" } }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain matching INFO"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item with given message`() {
        LogList().use { list ->
            companionObjectLogger.info { "Message" }
            list shouldHaveInfo "Message"
            shouldThrow<AssertionError> { list shouldHaveInfo "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item containing string`() {
        LogList().use { list ->
            companionObjectLogger.info { "Message" }
            list shouldHaveInfoContaining "sage"
            shouldThrow<AssertionError> { list shouldHaveInfoContaining "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains INFO item matching Regex`() {
        LogList().use { list ->
            companionObjectLogger.info { "Message" }
            list shouldHaveInfoMatching Regex("Mess")
            shouldThrow<AssertionError> { list shouldHaveInfoMatching Regex("Wrong") }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain INFO Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item matching test`() {
        LogList().use { list ->
            companionObjectLogger.warn { "Error message" }
            list shouldHaveWarning { it.message == "Error message" }
            shouldThrow<AssertionError> { list shouldHaveWarning { it.message == "Wrong" } }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain matching WARN"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item with given message`() {
        LogList().use { list ->
            companionObjectLogger.warn { "Warning message" }
            list shouldHaveWarning "Warning message"
            shouldThrow<AssertionError> { list shouldHaveWarning "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item containing string`() {
        LogList().use { list ->
            companionObjectLogger.warn { "Warning message" }
            list shouldHaveWarningContaining "age"
            shouldThrow<AssertionError> { list shouldHaveWarningContaining "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains WARN item matching Regex`() {
        LogList().use { list ->
            companionObjectLogger.warn { "Warning message" }
            list shouldHaveWarningMatching Regex("Warning")
            shouldThrow<AssertionError> { list shouldHaveWarningMatching Regex("Wrong") }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain WARN Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item matching test`() {
        LogList().use { list ->
            companionObjectLogger.error { "Error message" }
            list shouldHaveError { it.message == "Error message" }
            shouldThrow<AssertionError> { list shouldHaveError { it.message == "Wrong" } }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain matching ERROR"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item with given message`() {
        LogList().use { list ->
            companionObjectLogger.error { "Error message" }
            list shouldHaveError "Error message"
            shouldThrow<AssertionError> { list shouldHaveError "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item containing string`() {
        LogList().use { list ->
            companionObjectLogger.error { "Error message" }
            list shouldHaveErrorContaining "Err"
            shouldThrow<AssertionError> { list shouldHaveErrorContaining "Wrong" }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR containing Wrong"
                }
            }
        }
    }

    @Test fun `should check whether LogList contains ERROR item matching Regex`() {
        LogList().use { list ->
            companionObjectLogger.error { "Error message" }
            list shouldHaveErrorMatching Regex("Error")
            shouldThrow<AssertionError> { list shouldHaveErrorMatching Regex("Wrong") }.let {
                it.message.let { message ->
                    message.shouldBeNonNull()
                    message shouldContain "LogList does not contain ERROR Wrong"
                }
            }
        }
    }

    @Test fun `should get sub-list of LogList`() {
        LogList().use { list ->
            val logger = getLogger("TestLog")
            logger.info { "Message" }
            list.subList("TestLog") shouldHaveInfo "Message"
            list.subList("OtherLog") shouldBe emptyList()
        }
    }

    @Test fun `should get sub-list of LogList using KClass`() {
        LogList().use { list ->
            companionObjectLogger.info("Good")
            val otherLogger = getLogger("Test2")
            otherLogger.info("Another")
            list.subList(LogListExtTest::class) shouldHaveInfo "Good"
            list.subList("Test2") shouldHaveInfo "Another"
        }
    }

    @Test fun `should create a filtering LogList using KClass`() {
        LogList(LogListExtTest::class).use { list ->
            companionObjectLogger.info("Good")
            val otherLogger = getLogger("Test2")
            otherLogger.info("Another")
            list.size shouldBe 1
            list shouldHaveInfo "Good"
        }
    }

    @Suppress("ConstPropertyName")
    companion object {
        val companionObjectLogger = getLogger()
        val zone: ZoneId = ZoneId.systemDefault()
        const val name = "DummyName"
        val level = Level.INFO
        const val text = "Dummy text"
    }

}
