package br.com.carlettisolucoes.bestway.router;

import java.util.ArrayList;
import java.util.List;

import br.com.carlettisolucoes.bestway.way.Way;

public class BestWay implements Router {

	private Way origin, destination;

	public BestWay(Way origin, Way destination) {
		this.origin = origin;
		this.destination = destination;
	}

	@Override
	public List<Way> findBestRoute() {
		populateBestLength(this.origin, 0, new ArrayList<>());
		List<Way> route = new ArrayList<>();
		findBestRoute(route, this.destination);
		return route;
	}

	private synchronized void populateBestLength(Way way, int current, List<Way> ways) {
		int newLength = (current + way.getFactor());
		if (way.getLength() > 0 && way.getLength() < newLength) {
			return;
		}
		System.out.println("Current: " + way);
		// writeHtml(way);
		way.setLength(newLength);
		if (way.equals(destination)) {
			System.out.println(way.getLength());
			return;
		}
		way.getWays().forEach(w -> populateBestLength(w, way.getLength(), ways));
	}

	private void findBestRoute(List<Way> bestRoute, Way way) {
		bestRoute.add(way);
		if (way.equals(this.origin)) {
			return;
		}
		// writeHtml(way);
		findBestRoute(bestRoute, way.getBestWay());
	}

}