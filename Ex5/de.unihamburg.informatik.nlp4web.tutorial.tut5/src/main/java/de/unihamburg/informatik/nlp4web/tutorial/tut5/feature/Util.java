package de.unihamburg.informatik.nlp4web.tutorial.tut5.feature;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

public final class Util {

	/**
	 * Reads the file and returns unique lines
	 *
	 * @param fileName
	 * @return
	 */
	public final static SortedSet<String> readLines(String fileName, String splitOn) {
		SortedSet<String> lines = Collections.emptySortedSet();
		try {
			lines = FileUtils.readLines(new File(fileName)).stream().map(line -> line.split(splitOn)[0].toLowerCase())
					.collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	/**
	 * Reads the file and returns unique lines
	 *
	 * @param fileName
	 * @return
	 */
	public final static SortedSet<String> readLines(String fileName) {
		SortedSet<String> lines = Collections.emptySortedSet();
		try {
			lines = FileUtils.readLines(new File(fileName)).stream().collect(Collectors.toCollection(TreeSet::new));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

}
