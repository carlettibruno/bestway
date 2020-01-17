package br.com.carlettisolucoes.bestway.writer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import br.com.carlettisolucoes.bestway.reader.MatrizMap;
import br.com.carlettisolucoes.bestway.way.Way;

public class Html {

	private MatrizMap matriz;

	private Way destination;

	private boolean enable;

	private long delay;

	public Html(MatrizMap matriz, Way destionation) {
		this.matriz = matriz;
		this.destination = destionation;
	}	

	public Html(MatrizMap matriz, Way destionation, boolean enable, long delay) {
		this.matriz = matriz;
		this.destination = destionation;
		this.enable = enable;
		this.delay = delay;
	}

	public void write(Way highlight) {
		if(!enable) {
			return;
		}
		write(Arrays.asList(highlight));
	}

	public void write(List<Way> highlights) {
		if(!enable) {
			return;
		}		
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
			Thread.sleep(delay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

}
