/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package topicmodeling.regulaizers

/**
 * Defines a prior distribution (possibly, improper) on \Phi matrix  (words over topics)
 */
trait TopicsRegularizer extends MatrixInPlaceModification {
    /**
     *
     * @param topics \Phi matrix
     * @return log prior probability of \Phi. Is used for perplexity calculation only
     */
    def apply(topics: Array[Array[Float]]): Float

    /**
     * This implementation performs a positive cut on every element
     * @param topicsCnt number of times the word was generated by a topic (n_{wt} in Vorontsov's
     *                  notation)
     * @param oldTopics
     */
    def regularize(topicsCnt: Array[Array[Float]],
                   oldTopics: Array[Array[Float]]): Unit =
        shift(topicsCnt, (x, i, j) => x(i)(j) = math.max(0f, x(i)(j)))
}
