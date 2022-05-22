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
class CCProcessTest {

	private final String actualText = "asafd lksdl fkjdhf khjhk lshdfghywe";

	@Autowired
	@Qualifier(ProcessName.CC)
	private Process<FlatFileEntry> process;

	private final FlatFileEntry entry = new FlatFileEntry();

	@BeforeEach
	public void init() {
		entry.setQualifier(ProcessName.CC);
		entry.setText(actualText);
	}

	@Test
	public void whenEntryQualifiedAsCCThenTextChangedToCamelCase() {
		process.exec(entry);
		assertNotEquals(actualText, entry.getText());
		assertEquals("asafdLksdlFkjdhfKhjhkLshdfghywe", entry.getText());
	}
}