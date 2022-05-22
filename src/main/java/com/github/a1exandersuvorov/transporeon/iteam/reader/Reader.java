package com.github.a1exandersuvorov.transporeon.iteam.reader;

import org.springframework.batch.item.ItemReader;

public interface Reader<T> {

	ItemReader<T> reader();
}
