package com.github.a1exandersuvorov.transporeon.model;

import lombok.Data;

@Data
public class FlatFileEntry {

	private String qualifier;
	private String text;

	@Override
	public String toString() {
		return String.format("%s(%s, %s)", FlatFileEntry.class.getSimpleName(), qualifier, text.substring(0, Math.min(text.length(), 10)));
	}
}
