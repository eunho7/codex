package com.example.export.infrastructure.data;

import com.example.export.ports.ExportDataCursor;

import java.util.Iterator;
import java.util.List;

public class InMemoryExportDataCursor<T> implements ExportDataCursor<T> {

    private final Iterator<T> iterator;

    public InMemoryExportDataCursor(List<T> rows) {
        this.iterator = rows.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }

    @Override
    public void close() {
    }
}
