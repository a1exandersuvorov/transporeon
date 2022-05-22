package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Adding numbers given at the input.
 */
@Component
@Qualifier(ProcessName.SUM)
public class SumProcess implements Process<FlatFileEntry> {

	@Override
	public void exec(FlatFileEntry entry) {
		var text = entry.getText();
		Integer sum = Arrays.stream(text.split(" "))
				.map(String::trim)
				.mapToInt(Integer::valueOf)
				.sum();
		entry.setText(String.valueOf(sum));
	}
}
