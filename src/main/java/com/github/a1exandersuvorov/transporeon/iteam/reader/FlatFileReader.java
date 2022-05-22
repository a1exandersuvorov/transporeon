package com.github.a1exandersuvorov.transporeon.iteam.reader;

import com.github.a1exandersuvorov.transporeon.config.Constants.ReaderName;
import com.github.a1exandersuvorov.transporeon.model.FlatFileEntry;
import java.io.InputStream;
import lombok.SneakyThrows;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.SuffixRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Qualifier(ReaderName.FLAT_FILE)
public class FlatFileReader implements Reader<FlatFileEntry> {

	private final String lineFeedCharacter = "\n";

	@Value("${file.name}")
	private String fileName;

	@SneakyThrows
	@Override
	public FlatFileItemReader<FlatFileEntry> reader() {
		FlatFileItemReader<FlatFileEntry> reader = new FlatFileItemReader<>();
		if (!StringUtils.hasText(fileName)) {
			return null;
		}
		InputStream inputStream = new ClassPathResource(fileName).getInputStream();
		reader.setResource(new InputStreamResource(inputStream));
		reader.setLineMapper(new DefaultLineMapper<>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("qualifier", "text");
						setDelimiter(lineFeedCharacter);
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {
					{
						setTargetType(FlatFileEntry.class);
					}
				});
			}
		});
		CustomSuffixRecordSeparatorPolicy recordSeparatorPolicy = new CustomSuffixRecordSeparatorPolicy();
		recordSeparatorPolicy.setSuffix(lineFeedCharacter);
		reader.setRecordSeparatorPolicy(recordSeparatorPolicy);
		return reader;
	}

	private class CustomSuffixRecordSeparatorPolicy extends SuffixRecordSeparatorPolicy {

		@Override
		public boolean isEndOfRecord(String line) {
			return line.contains(lineFeedCharacter);
		}

		@Override
		public String preProcess(String line) {
			line = super.preProcess(line);
			return line + lineFeedCharacter;
		}

		@Override
		public String postProcess(String record) {
			return record;
		}
	}
}
