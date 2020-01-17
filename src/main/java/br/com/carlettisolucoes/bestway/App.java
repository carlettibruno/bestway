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
import br.com.carlettisolucoes.bestway.writer.Html;

public class App {

	Way destination;

	// Way startpoint;

	public static void main(String[] args) {
		App app = new App();

		List<Way> map = new ArrayList<>();
		InputStream is = null;
		try {
			is = new FileInputStream(Paths.get("./samples/map_bigger.txt").toFile());
			MatrizMap reader = new TextMap(is); // TODO, create factory
			Html html = new Html(reader, app.destination, true, 1000);
			map = reader.read();

			// int indexDestination = map.indexOf(new Way(new WayId(30, 19), 0)); // TODO
			// bring from configuration file
			// int indexDestination = map.indexOf(new Way(new WayId(4, 4), 0)); // for small one TODO bring from configuration file
			int indexDestination = map.indexOf(new Way(new WayId(28, 39), 0)); // for bigger file...
			app.destination = map.get(indexDestination);
			Way startpoint = map.get(0); // TODO bring from configuration file

			// app.populateBestLength(app.startpoint, 0, ways);

			// List<Way> route = new ArrayList<>();
			// app.findBestRoute(route, app.destination);

			Router router = new BestWay(startpoint, app.destination, html);
			List<Way> route = router.findBestRoute();
			System.out.println(route);

			html.write(route);

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

}
