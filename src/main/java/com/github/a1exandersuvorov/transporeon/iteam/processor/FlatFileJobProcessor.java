package com.github.a1exandersuvorov.transporeon.iteam.processor;

import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessorName;
import com.github.a1exandersuvorov.transporeon.iteam.processor.process.Process;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(ProcessorName.FLAT_FILE)
@Slf4j
public class FlatFileJobProcessor implements ItemProcessor<FlatFileEntry, FlatFileEntry> {

	private Map<String, Process<FlatFileEntry>> processMap;

	@Autowired
	private List<Process<FlatFileEntry>> processList;

	@PostConstruct
	public void init() {
		processMap = processList.stream()
				.collect(Collectors.toMap(reader -> reader.getClass().getAnnotation(Qualifier.class).value(), Function.identity()));
	}

	@Override
	public FlatFileEntry process(FlatFileEntry entry) {
		try {
			processMap.get(entry.getQualifier()).exec(entry);
			return entry;
		} catch (Exception e) {
			log.error("{} {} {}", entry, e.getClass().getCanonicalName(), e.getMessage());
		}
		return null;
	}
}
