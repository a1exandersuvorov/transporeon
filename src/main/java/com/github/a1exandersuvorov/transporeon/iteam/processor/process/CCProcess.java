package com.github.a1exandersuvorov.transporeon.iteam.processor.process;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A combination of words in CamelCase style.
 */
@Component
@Qualifier(ProcessName.CC)
public class CCProcess implements Process<FlatFileEntry> {

	@Override
	public void exec(FlatFileEntry entry) {
		var text = entry.getText();
		String[] strArr = text.split(" ");
		StringBuilder sb = new StringBuilder(strArr[0]);
		for (int i = 1; i < strArr.length; i++) {
			sb.append(strArr[i].trim().substring(0, 1).toUpperCase()).append(strArr[i].trim().substring(1));
		}
		entry.setText(sb.toString());
	}
}
