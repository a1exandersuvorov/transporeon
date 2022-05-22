package com.github.a1exandersuvorov.transporeon.service;

import com.github.a1exandersuvorov.transporeon.exception.JobNotFoundException;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public interface JobExecutor {

	BatchStatus execute(String name)
			throws JobNotFoundException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException;
}
