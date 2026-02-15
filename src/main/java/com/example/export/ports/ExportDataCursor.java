package com.example.export.ports;

import java.util.Iterator;

public interface ExportDataCursor<T> extends Iterator<T>, AutoCloseable {
    @Override
    void close();
}
