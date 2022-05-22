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
class ReverseProcessTest {

	private final String actualText = "ysdftu";

	@Autowired
	@Qualifier(ProcessName.REVERSE)
	private Process<FlatFileEntry> process;

	private final FlatFileEntry entry = new FlatFileEntry();

	@BeforeEach
	public void init() {
		entry.setQualifier(ProcessName.REVERSE);
		entry.setText(actualText);
	}

	@Test
	public void whenEntryQualifiedAsREVERSEThenEntryTextIsReversed() {
		process.exec(entry);
		assertNotEquals(actualText, entry.getText());
		assertEquals("utfdsy", entry.getText());
	}
}