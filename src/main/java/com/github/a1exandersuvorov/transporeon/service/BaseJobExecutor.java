package com.github.a1exandersuvorov.transporeon.service;

import com.github.a1exandersuvorov.transporeon.exception.JobNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BaseJobExecutor implements JobExecutor {

	@Value("${job.default}")
	private String defaultJob;

	@Autowired
	private JobLauncher jobLauncher;

	private final Map<String, Job> jobMap;

	@Autowired
	public BaseJobExecutor(List<Job> jobList) {
		this.jobMap = jobList.stream()
				.collect(Collectors.toMap(Job::getName, Function.identity()));

	}

	@EventListener(ApplicationReadyEvent.class)
	private BatchStatus execute()
			throws JobInstanceAlreadyCompleteException, JobNotFoundException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		return execute(defaultJob);
	}

	@Override
	public BatchStatus execute(String name)
			throws JobNotFoundException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		if (!StringUtils.hasText(name)) {
			throw new IllegalStateException("Job name is blank");
		}
		Job job = jobMap.get(name);
		if (job == null) {
			throw new JobNotFoundException(String.format("Job %s not found", name));
		}
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		return execution.getStatus();
	}
}
