package com.github.a1exandersuvorov.transporeon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.a1exandersuvorov.transporeon.config.Constants.JobName;
import com.github.a1exandersuvorov.transporeon.exception.JobNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseJobExecutorTest {

	@Autowired
	private JobExecutor executor;

	@Test
	public void whenJobLauncherRunFileBatchJobThenItIsCompleted()
			throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, JobNotFoundException {
		BatchStatus status = executor.execute(JobName.FILE_BATCH);
		assertEquals(BatchStatus.COMPLETED, status);
	}

	@Test
	public void whenJobLauncherRunWrongNameJobThenJobNotFoundException() {
		assertThrows(JobNotFoundException.class, () -> executor.execute("wrongJobName"));
	}

	@Test
	public void whenJobNameIsBlankThenIllegalStateException() {
		assertThrows(IllegalStateException.class, () -> executor.execute(""));
		assertThrows(IllegalStateException.class, () -> executor.execute(null));
	}
}