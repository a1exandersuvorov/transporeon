package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SumProcessTest {

	private String actualText = "-5 0 21 444";

	@Autowired
	@Qualifier(ProcessName.SUM)
	private Process<FlatFileEntry> process;

	private final FlatFileEntry entry = new FlatFileEntry();

	@BeforeEach
	public void init() {
		entry.setQualifier(ProcessName.SUM);
		entry.setText(actualText);
	}

	@Test
	public void whenEntryQualifiedAsSUMThenEntryTextIsSummed() {
		process.exec(entry);
		assertNotEquals(actualText, entry.getText());
		assertEquals("460", entry.getText());
	}

	@Test
	public void whenEntryQualifiedAsSUMAndThereIsNonNumberCharacterThenNumberFormatException() {
		actualText = "-5 a 21 444";
		entry.setText(actualText);
		assertThrows(NumberFormatException.class, () -> process.exec(entry));
	}
}