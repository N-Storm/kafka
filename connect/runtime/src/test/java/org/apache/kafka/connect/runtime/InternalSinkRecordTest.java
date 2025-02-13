/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.kafka.connect.runtime;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.connect.header.Header;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class InternalSinkRecordTest {

    @Test
    public void testNewRecordHeaders() {
        SinkRecord sinkRecord = new SinkRecord("test-topic", 0, null, null, null, null, 10);
        ConsumerRecord<byte[], byte[]> consumerRecord = new ConsumerRecord<>("test-topic", 0, 10, null, null);
        InternalSinkRecord internalSinkRecord = new InternalSinkRecord(consumerRecord, sinkRecord);
        assertTrue(internalSinkRecord.headers().isEmpty());
        assertTrue(sinkRecord.headers().isEmpty());

        SinkRecord newRecord = internalSinkRecord.newRecord("test-topic", 0, null, null, null,
                null, null, Collections.singletonList(mock(Header.class)));
        assertEquals(1, newRecord.headers().size());
    }
}
