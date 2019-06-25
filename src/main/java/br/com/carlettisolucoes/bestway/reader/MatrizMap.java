package br.com.carlettisolucoes.bestway.reader;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import br.com.carlettisolucoes.bestway.way.Way;
import br.com.carlettisolucoes.bestway.way.WayId;

public abstract class MatrizMap implements MapReader {

	private int[][] values;

	private int MAX_Y;

	private int MAX_X;

	private List<Way> map;

	protected abstract int[][] values();

	protected abstract Integer noWay();

	private Integer noWay;

	public MatrizMap() {
		map = new ArrayList<>();
		this.noWay = noWay();
	}

	@Override
	public List<Way> read() {
		this.values = values();
		MAX_Y = this.values.length;
		MAX_X = this.values[0].length;
		Instant start = Instant.now();
		for (int y = 0; y < MAX_Y; y++) {
			for (int x = 0; x < MAX_X; x++) {
				Way way = getWay(x, y);
				if (way == null) {
					continue;
				}
				List<Way> ways = getNeighborhoods(x, y);
				ways.forEach(nw -> way.addWay(nw));
			}
		}
		Instant end = Instant.now();
		System.out.println("create map interval: " + Duration.between(start, end));

		return map;
	}

	private List<Way> getNeighborhoods(int x, int y) {
		List<Way> ways = new ArrayList<>();

		if (x + 1 < MAX_X) {
			Way w = getWay(x + 1, y);
			if (w != null)
				ways.add(w);
		}

		if (y + 1 < MAX_Y) {
			Way w = getWay(x, y + 1);
			if (w != null)
				ways.add(w);
		}

		if (x - 1 >= 0) {
			Way w = getWay(x - 1, y);
			if (w != null)
				ways.add(w);
		}

		if (y - 1 >= 0) {
			Way w = getWay(x, y - 1);
			if (w != null)
				ways.add(w);
		}

		return ways;
	}

	public Way getWay(int x, int y) {
		Integer factor = values[y][x];
		if (factor.equals(this.noWay)) {
			return null;
		}

		Way way = new Way(new WayId(x, y), factor);
		int index = map.indexOf(way);
		if (index == -1) {
			map.add(way);
			return way;
		} else {
			return map.get(index);
		}
	}

	public int getMAX_X() {
		return MAX_X;
	}

	public int getMAX_Y() {
		return MAX_Y;
	}

}