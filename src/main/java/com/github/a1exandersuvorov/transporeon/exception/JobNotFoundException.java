package com.github.a1exandersuvorov.transporeon.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JobNotFoundException extends Exception {

	public JobNotFoundException(String message) {
		super(message);
	}
}
