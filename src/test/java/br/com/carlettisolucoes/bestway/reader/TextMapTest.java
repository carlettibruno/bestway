package br.com.carlettisolucoes.bestway.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.carlettisolucoes.bestway.way.Way;

public class TextMapTest {

	@Test
	public void givenTextReturnQtyPositionsAndCorrectWays() {
		InputStream is = buildInputStream();
		try {
			MapReader reader = new TextMap(is);
			List<Way> ways = reader.read();
			Assert.assertEquals(22, ways.size());
			Assert.assertEquals(2, ways.get(0).getWays().size());
			Assert.assertEquals(4, ways.get(5).getWays().size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void givenBigTextReturnQtyPositionsAndCorrectWays() {
		InputStream is = buildBiggerInputStream();
		try {
			MapReader reader = new TextMap(is);
			List<Way> ways = reader.read();
			Assert.assertEquals(667, ways.size());
			Assert.assertEquals(2, ways.get(0).getWays().size());
			Assert.assertEquals(3, ways.get(3).getWays().size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private InputStream buildInputStream() {
		StringBuilder text = new StringBuilder();
		text.append("1	1	1	0	2");
		text.append(System.lineSeparator());
		text.append("4	2	6	1	1");
		text.append(System.lineSeparator());
		text.append("1	1	1	0	2");
		text.append(System.lineSeparator());
		text.append("4	3	5	2	9");
		text.append(System.lineSeparator());
		text.append("1	1	1	0	2");
		ByteArrayInputStream bais = new ByteArrayInputStream(text.toString().getBytes());
		return bais;
	}

	private InputStream buildBiggerInputStream() {
		StringBuilder text = new StringBuilder();
		text.append(
				"1	1	1	1	1	1	50	60	70	80	18	1	1	1	11	1	1	1	1	1	99	1	1	1	99	1	1	1	1	1	18	1	1	1	1	1	1	1	1	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	0	0	0	0	0	0	0	11	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	1	1	1	1	1	1	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	120	0	0	1	0	0	0	0	1	0	10	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	1	1	1	1	1	1	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"99	0	10	0	0	0	0	0	0	1	1	0	14	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	0	0	0	0	1	1	1	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	99	0	1	1	1	1	1	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	1	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	0	0	0	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	1	1	1	1	0	0	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	1	0	0	1	0	0	1	1	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	1	0	0	1	0	0	1	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"99	0	1	0	1	0	1	1	1	1	1	1	1	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	1	1	1	1	1	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"99	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"50	1	1	1	1	1	1	1	99	1	1	1	1	1	1	1	1	1	1	1	50	1	99	1	1	1	50	1	1	1	98	1	1	1	50	1	1	1	1	50");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	1	0	0	0	0	0	1	1	1	1	1	1	87	1	97	1	88	1	1	1	1	1	1	1	1	1	57	1	1	1	1	1");
		text.append(System.lineSeparator());
		text.append(
				"1	1	1	10	10	19	1	1	1	1	18	1	1	1	76	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	0	0	0	0	0	0	0	11	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	1	1	1	1	1	1	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	12	0	0	1	0	0	0	0	1	0	10	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	1	1	1	1	1	1	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	10	0	0	0	0	0	0	1	1	0	14	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	0	0	0	0	0	1	1	1	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	2	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	14	0	1	1	1	1	1	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	0	0	0	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	1	1	1	1	0	0	1	0	0	1	0	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	1	0	0	1	0	0	1	1	1	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	5	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	1	0	0	1	0	0	1	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	0	1	0	1	1	1	77	1	1	1	1	23	1	1	1	1	1	67	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	1	1	99	1	1	1	1	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"99	1	1	1	1	1	1	1	1	1	1	1	1	1	78	1	1	1	1	1	45	1	1	1	54	1	1	1	1	1	1	1	1	1	1	1	1	1	1	99");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	1	0	1	0	1	0	1	0	0	0	18	0	0	0	1	0	0	0	0	1");
		text.append(System.lineSeparator());
		text.append(
				"1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	65	1	1	1	18	1	1	1	1	1	1	1	1	1");
		return new ByteArrayInputStream(text.toString().getBytes());
	}

}