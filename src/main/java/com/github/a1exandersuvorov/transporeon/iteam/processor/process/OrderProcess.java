package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Arranging a sequence of words in alphabetical order.
 */
@Component
@Qualifier(ProcessName.ORDER)
public class OrderProcess implements Process<FlatFileEntry> {

	@Override
	public void exec(FlatFileEntry entry) {
		var text = entry.getText();
		String[] strArr = text.split(" ");
		Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
		text = String.join(" ", strArr);
		entry.setText(text);
	}
}
