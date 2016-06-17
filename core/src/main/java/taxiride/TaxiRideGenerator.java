/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package taxiride;

import com.hazelcast.jet.container.ProcessorContext;
import com.hazelcast.jet.data.io.ConsumerOutputStream;
import com.hazelcast.jet.data.io.ProducerInputStream;
import com.hazelcast.jet.processor.ContainerProcessor;

import java.util.Iterator;

/**
 * Processor for parsing each line in input and converting it to a TaxiRidEvent
 */
public class TaxiRideGenerator implements ContainerProcessor<String, TaxiRideEvent> {

    @Override
    public boolean process(ProducerInputStream<String> inputStream,
                           ConsumerOutputStream<TaxiRideEvent> outputStream,
                           String sourceName, ProcessorContext processorContext) throws Exception {
        Iterator<String> iterator = inputStream.iterator();
        while (iterator.hasNext()) {
            TaxiRideEvent taxiRideEvent = TaxiRideEvent.fromString(iterator.next());
            outputStream.consume(taxiRideEvent);
        }
        return true;
    }
}
