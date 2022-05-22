package com.github.a1exandersuvorov.transporeon.config;

import com.github.a1exandersuvorov.transporeon.config.Constants.JobName;
import com.github.a1exandersuvorov.transporeon.config.Constants.ProcessorName;
import com.github.a1exandersuvorov.transporeon.config.Constants.ReaderName;
import com.github.a1exandersuvorov.transporeon.config.Constants.WriterName;
import com.github.a1exandersuvorov.transporeon.iteam.reader.Reader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@SuppressWarnings("unused")
	@Bean
	public Job fileBatchJob(@Qualifier("step1") Step step1) {
		return jobBuilderFactory
				.get(JobName.FILE_BATCH)
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.build();
	}

	@SuppressWarnings({"unchecked", "unused"})
	@Bean
	public Step step1(@Qualifier(ReaderName.FLAT_FILE) Reader<?> reader, @Qualifier(ProcessorName.FLAT_FILE) ItemProcessor<?, ?> processor,
			@Qualifier(WriterName.CONSOLE) ItemWriter<?> writer) {
		return stepBuilderFactory.get("step1").chunk(5)
				.reader(reader.reader())
				.processor((ItemProcessor<? super Object, ?>) processor)
				.writer((ItemWriter<? super Object>) writer)
				.build();
	}
}
