package br.com.carlettisolucoes.bestway.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class TextMap extends MatrizMap {

	private int count;

	private int[][] values;

	private int MAX = 40; // TODO

	private int NO_WAY = 0; // TODO

	private InputStream is;

	public TextMap(InputStream is) {
		super();
		count = 0;
		values = new int[MAX][MAX];
		this.is = is;
		readValues();
	}

	private void readValues() {
		Instant start = Instant.now();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
			String line = null;
			while ((line = br.readLine()) != null) {
				this.values[count++] = Arrays.asList(line.split("\t")).stream().mapToInt(Integer::parseInt).toArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Instant end = Instant.now();
		System.out.println("readValues interval: " + Duration.between(start, end));
	}

	@Override
	protected int[][] values() {
		return this.values;
	}

	@Override
	protected Integer noWay() {
		return this.NO_WAY;
	}

}