/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.stream.ml.examples

import org.apache.flink.api.scala._
import org.apache.flink.ml.recommendation.ALS
import org.apache.flink.stream.ml.recommendation._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

object FactorModel {
  def main(args: Array[String]) {

    val strEnv = StreamExecutionEnvironment.getExecutionEnvironment

    val stream : DataStream[(Int, Int)] = strEnv.fromElements((0,0))

    val env = ExecutionEnvironment.getExecutionEnvironment

    val input = env.fromElements((0,0,1.0))

    val model = ALS().setImplicit(true)

    model.fit(input)

    val result  = model.predictStream(stream)

    result.print()

    strEnv.execute("Factor model using FlinkML")
  }
}
