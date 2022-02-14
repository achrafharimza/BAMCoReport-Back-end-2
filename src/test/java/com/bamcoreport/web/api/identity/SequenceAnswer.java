package com.bamcoreport.web.api.identity;


import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Iterator;
import java.util.List;

 public class SequenceAnswer<T> implements Answer<T> {

        private Iterator<T> resultIterator;

        // the last element is always returned once the iterator is exhausted, as with thenReturn()
        private T last;

        public SequenceAnswer(List<T> results) {
            this.resultIterator = results.iterator();
            this.last = results.get(results.size() - 1);
        }

        @Override
        public T answer(InvocationOnMock invocation) throws Throwable {
            if (resultIterator.hasNext()) {
                return resultIterator.next();
            }
            return last;
        }
    }


