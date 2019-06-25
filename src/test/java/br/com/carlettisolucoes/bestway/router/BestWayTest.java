package br.com.carlettisolucoes.bestway.router;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.carlettisolucoes.bestway.way.Way;
import br.com.carlettisolucoes.bestway.way.WayId;

public class BestWayTest {

	@Test
	public void givenOriginAndDestinationReturnQtyAndLenghtOfBestWay() {
		List<Way> map = retrieveSerialized("./samples/map_ways.serialized");
		Way origin = map.get(map.indexOf(new Way(new WayId(0, 0))));
		Way destination = map.get(map.indexOf(new Way(new WayId(4, 4))));
		Router router = new BestWay(origin, destination);
		List<Way> ways = router.findBestRoute();
		Assert.assertEquals(9, ways.size());
		Assert.assertEquals(24, ways.get(0).getLength());
	}

	@Test
	public void givenBiggerWithOriginAndDestinationReturnQtyAndLenghtOfBestWay() {
		List<Way> map = retrieveSerialized("./samples/bigger_map_ways.serialized");
		Way origin = map.get(map.indexOf(new Way(new WayId(0, 0))));
		Way destination = map.get(map.indexOf(new Way(new WayId(30, 19))));
		Router router = new BestWay(origin, destination);
		List<Way> ways = router.findBestRoute();
		Assert.assertEquals(74, ways.size());
		Assert.assertEquals(555, ways.get(0).getLength());
	}

	private List<Way> retrieveSerialized(String objectsPath) {
		List<Way> ways = new ArrayList<>();

		try {
			FileInputStream fis = new FileInputStream(objectsPath);
			ObjectInputStream ois = new ObjectInputStream(fis);

			ways = (List<Way>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}

		return ways;
	}

}