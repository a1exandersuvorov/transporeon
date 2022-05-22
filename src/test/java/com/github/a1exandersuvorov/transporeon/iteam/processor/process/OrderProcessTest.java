package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderProcessTest {

	private final String actualText = "euwry asfdhf sghd e djsfh";

	@Autowired
	@Qualifier(ProcessName.ORDER)
	private Process<FlatFileEntry> process;

	private final FlatFileEntry entry = new FlatFileEntry();

	@BeforeEach
	public void init() {
		entry.setQualifier(ProcessName.ORDER);
		entry.setText(actualText);
	}

	@Test
	public void whenEntryQualifiedAsORDERThenTextNaturallyOrdered() {
		process.exec(entry);
		assertNotEquals(actualText, entry.getText());
		assertEquals("asfdhf djsfh e euwry sghd", entry.getText());
	}
}