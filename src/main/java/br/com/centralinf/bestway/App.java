package br.com.centralinf.bestway;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
	
	List<Way> map = new ArrayList<Way>();
	
	Way destination;

	static int MAX = 40;
	
	static int NO_WAY = 0;
	
//	static int FINISH = -1;
	
	int[][] values = new int[MAX][MAX];
	
    public static void main( String[] args ) {
    	App app = new App();
    	
    	app.readValues();
    	
    	Instant start = Instant.now();
    	for (int y = 0; y < MAX; y++) {
	    	for (int x = 0; x < MAX; x++) {
	    		Way way = app.getWay(x, y);
	    		if(way == null) {
	    			continue;
	    		}
	    		List<Way> ways = app.getNeighborhoods(x, y);
	    		ways.forEach(nw -> way.addWay(nw));
			}
    	}
    	
    	int indexDestination = app.map.indexOf(new Way(new WayId(34, 38), 0));
    	app.destination = app.map.get(indexDestination);
    	
    	Instant end = Instant.now();
    	System.out.println("create map interval: " + Duration.between(start, end));
    	
    	Way startpoint = app.map.get(0);
    	List<Way> ways = new ArrayList<>();
    	app.populateBestLength(startpoint, 0, ways);
    	
    	List<Way> route = new ArrayList<>();
    	app.findBestRoute(route, app.destination);
    	
    	System.out.println(route);
    	
    	app.writeHtml(route);
    }
    
    private synchronized void populateBestLength(Way way, int current, List<Way> ways) {
    	if(way.length > 0 && way.length < current) {
    		return;
    	}
    	System.out.println("Current: " + way);
//    	writeHtml(way);
    	way.length = current + way.factor;
    	if(way.equals(destination)) {
    		System.out.println(way.length);
    		return;
    	}
    	way.ways.forEach(w -> populateBestLength(w, way.length, ways));
	}
    
    static int lineCounter = 0;
    
    private void readValues() {
    	Instant start = Instant.now();
    	try {
    		lineCounter = 0;
			List<String> lines = Files.readAllLines(Paths.get("/imagens/map.txt"));
			lines.forEach(l -> values[lineCounter++] = Arrays.asList(l.split("\t")).stream().mapToInt(Integer::parseInt).toArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Instant end = Instant.now();
    	System.out.println("readValues interval: " + Duration.between(start, end));
	}
    
    private void findBestRoute(List<Way> bestRoute, Way way) {
    	bestRoute.add(way);
    	if(way.length == 1 || way.ways.isEmpty()) {
    		return;
    	}
    	Collections.sort(way.ways, new Comparator<Way>() {
			@Override
			public int compare(Way o1, Way o2) {
				return new Integer(o1.length).compareTo(o2.length);
			}
		});
    	findBestRoute(bestRoute, way.ways.get(0));
	}
    
    private List<Way> getNeighborhoods(int x, int y) {
    	List<Way> ways = new ArrayList<>();
    	
    	if(x + 1 < MAX) {
    		Way w = getWay(x + 1, y);
    		if(w != null) ways.add(w);
    	}
    	
    	if(y + 1 < MAX) {
    		Way w = getWay(x, y + 1);
    		if(w != null) ways.add(w);
    	}
    	
    	if(x - 1 >= 0) {
    		Way w = getWay(x - 1, y);
    		if(w != null) ways.add(w);
    	}
    	
    	if(y - 1 >= 0) {
    		Way w = getWay(x, y - 1);
    		if(w != null) ways.add(w);
    	}
    	
    	return ways;
	}
    
    private Way getWay(int x, int y) {
    	Integer factor = values[y][x];
    	if(factor == NO_WAY) {
    		return null;
    	}
    	
    	Way way = new Way(new WayId(x, y), factor);
		int index = map.indexOf(way);
		if(index == -1) {
			map.add(way);
			return way;
		} else {
			return map.get(index);
		}
	}
    
    public void writeHtml(Way highlight) {
    	writeHtml(Arrays.asList(highlight));
    }
    
    public void writeHtml(List<Way> highlights) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<table>");
    	for (int y = 0; y < MAX; y++) {
    		sb.append("<tr>");
	    	for (int x = 0; x < MAX; x++) {
	    		Way way = getWay(x, y);
	    		if(way == null) {
	    			sb.append("<td></td>");
	    			continue;
	    		}
	    		String style = "";
	    		boolean highlight = highlights != null && highlights.indexOf(way) > -1; 
	    		if(highlight) {
	    			style = "font-weight: bold; text-decoration: underline;";
	    		}

	    		int r = 220, g = 220, b = 220;
	    		if(highlight) {
    				r = 0;
    				g = 0;
    				b = 0;	    			
	    			if(way.equals(destination)) {
	    				g = 255;
	    			} else if(way.factor <= 30) {
	    				g = 180;
	    				b = 255;
	    			} else if(way.factor <= 60) {
	    				r = 255;
	    				g = 220;
	    			} else if(way.factor <= 90) {
	    				r = 255;
	    			} else {
	    				r = 200;
	    			}
	    		}
	    		style += "background-color: rgb("+r+","+g+","+b+");";
	    		
	    		sb.append("<td style=\"width: 45px; height: 45px; "+style+" \">");
	    		sb.append(way.factor + "(" + way.length + ")");
	    		sb.append("</td>");
			}
	    	sb.append("</tr>");
    	}
    	sb.append("</table>");
    	try {
			Files.write(Paths.get("/imagens/map.html"), sb.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
