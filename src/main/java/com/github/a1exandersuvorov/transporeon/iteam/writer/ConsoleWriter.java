package com.github.a1exandersuvorov.transporeon.iteam.writer;

import com.github.a1exandersuvorov.transporeon.config.Constants.WriterName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(WriterName.CONSOLE)
public class ConsoleWriter implements ItemWriter<FlatFileEntry> {

	@Override
	public void write(List<? extends FlatFileEntry> list) {
		list.forEach((entry) -> System.out.println(entry.getText()));
	}
}
