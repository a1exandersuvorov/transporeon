package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Word in reverse order.
 */
@Component
@Qualifier(ProcessName.REVERSE)
public class ReverseProcess implements Process<FlatFileEntry> {

	@Override
	public void exec(FlatFileEntry entry) {
		var text = entry.getText().trim();
		var sbr = new StringBuilder(text);
		sbr.reverse();
		entry.setText(sbr.toString());
	}
}
