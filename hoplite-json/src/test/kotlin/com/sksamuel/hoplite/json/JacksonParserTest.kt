package com.sksamuel.hoplite.json

import com.sksamuel.hoplite.BooleanValue
import com.sksamuel.hoplite.DoubleValue
import com.sksamuel.hoplite.ListValue
import com.sksamuel.hoplite.LongValue
import com.sksamuel.hoplite.MapValue
import com.sksamuel.hoplite.Pos
import com.sksamuel.hoplite.StringValue
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec

class JacksonParserTest : FunSpec() {
  init {

    test("parsing basic json") {
      JacksonParser.load(javaClass.getResourceAsStream("/basic.json")) shouldBe
          MapValue(
              mapOf(
                  "a" to StringValue(value = "hello", pos = Pos.LineColPos(line = 2, col = 15)),
                  "b" to LongValue(value = 1, pos = Pos.LineColPos(line = 3, col = 9)),
                  "c" to BooleanValue(value = true, pos = Pos.LineColPos(line = 4, col = 12)),
                  "d" to DoubleValue(value = 2.3, pos = Pos.LineColPos(line = 5, col = 11))
              ),
              pos = Pos.RangePos(start = -1, end = -1)
          )
    }

    test("parsing nested basic arrays") {
      JacksonParser.load(javaClass.getResourceAsStream("/nested_basic_arrays.json")) shouldBe
          MapValue(
              mapOf(
                  "a" to StringValue(value = "hello", pos = Pos.LineColPos(line = 2, col = 15)),
                  "b" to ListValue(
                      values = listOf(
                          StringValue(value = "x", pos = Pos.LineColPos(line = 4, col = 8)),
                          StringValue(value = "y", pos = Pos.LineColPos(line = 5, col = 8)),
                          StringValue(value = "z", pos = Pos.LineColPos(line = 6, col = 8))
                      ),
                      pos = Pos.RangePos(start = -1, end = -1)
                  )
              ),
              pos = Pos.RangePos(start = -1, end = -1)
          )
    }
  }
}