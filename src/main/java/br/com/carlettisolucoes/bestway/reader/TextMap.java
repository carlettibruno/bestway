package br.com.carlettisolucoes.bestway.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class TextMap extends MatrizMap {

	private int lineCounter;

	private List<String> lines;

	private int[][] values;

	private int MAX = 40; // TODO

	public TextMap() {
		super();
		lineCounter = 0;
		values = new int[MAX][MAX];
		readValues();
	}

	private void readValues() {
		Instant start = Instant.now();
		try {
			lineCounter = 0;
			this.lines = Files.readAllLines(Paths.get("./samples/map.txt"));
			lines.forEach(l -> this.values[lineCounter++] = Arrays.asList(l.split("\t")).stream()
					.mapToInt(Integer::parseInt).toArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instant end = Instant.now();
		System.out.println("readValues interval: " + Duration.between(start, end));
	}

	@Override
	protected int[][] values() {
		return this.values;
	}

}