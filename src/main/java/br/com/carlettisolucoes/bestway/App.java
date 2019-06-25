package br.com.carlettisolucoes.bestway;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.carlettisolucoes.bestway.reader.MatrizMap;
import br.com.carlettisolucoes.bestway.reader.TextMap;
import br.com.carlettisolucoes.bestway.router.BestWay;
import br.com.carlettisolucoes.bestway.router.Router;
import br.com.carlettisolucoes.bestway.way.Way;
import br.com.carlettisolucoes.bestway.way.WayId;

public class App {

	Way destination;

	// Way startpoint;

	public static void main(String[] args) {
		App app = new App();

		List<Way> map = new ArrayList<>();
		InputStream is = null;
		try {
			is = new FileInputStream(Paths.get("./samples/map.txt").toFile());
			MatrizMap reader = new TextMap(is); // TODO, create factory
			map = reader.read();

			// int indexDestination = map.indexOf(new Way(new WayId(30, 19), 0)); // TODO
			// bring from configuration file
			int indexDestination = map.indexOf(new Way(new WayId(4, 4), 0)); // TODO bring from configuration file
			app.destination = map.get(indexDestination);
			Way startpoint = map.get(0); // TODO bring from configuration file

			// app.populateBestLength(app.startpoint, 0, ways);

			// List<Way> route = new ArrayList<>();
			// app.findBestRoute(route, app.destination);

			Router router = new BestWay(startpoint, app.destination);
			List<Way> route = router.findBestRoute();
			System.out.println(route);

			app.writeHtml(route, reader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void writeHtml(Way highlight, MatrizMap matriz) {
		writeHtml(Arrays.asList(highlight), matriz);
	}

	public void writeHtml(List<Way> highlights, MatrizMap matriz) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for (int y = 0; y < matriz.getMAX_Y(); y++) {
			sb.append("<tr>");
			for (int x = 0; x < matriz.getMAX_X(); x++) {
				Way way = matriz.getWay(x, y);
				if (way == null) {
					sb.append("<td></td>");
					continue;
				}
				String style = "";
				boolean highlight = highlights != null && highlights.indexOf(way) > -1;
				if (highlight) {
					style = "font-weight: bold; text-decoration: underline;";
				}

				int r = 220, g = 220, b = 220;
				if (highlight) {
					r = 0;
					g = 0;
					b = 0;
					if (way.equals(this.destination)) {
						g = 255;
					} else if (way.getFactor() <= 30) {
						g = 180;
						b = 255;
					} else if (way.getFactor() <= 60) {
						r = 255;
						g = 220;
					} else if (way.getFactor() <= 90) {
						r = 255;
					} else {
						r = 200;
					}
				}
				style += "background-color: rgb(" + r + "," + g + "," + b + ");";

				sb.append("<td style=\"width: 45px; height: 45px; " + style + " \">");
				sb.append(way.getFactor() + "(" + way.getLength() + ")");
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		try {
			Files.write(Paths.get("./samples/map.html"), sb.toString().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
