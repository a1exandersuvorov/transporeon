package com.github.a1exandersuvorov.transporeon.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class JobName {

		public static final String FILE_BATCH = "fileBatchJob";
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ProcessName {

		public static final String CC = "CC";
		public static final String ORDER = "ORDER";
		public static final String REVERSE = "REVERSE";
		public static final String SUM = "SUM";
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ProcessorName {

		public static final String FLAT_FILE = "flatFileJobProcessor";
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ReaderName {

		public static final String FLAT_FILE = "file";
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class WriterName {

		public static final String CONSOLE = "console";
	}
}
