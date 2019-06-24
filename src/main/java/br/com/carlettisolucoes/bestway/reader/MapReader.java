package br.com.carlettisolucoes.bestway.reader;

import java.io.InputStream;
import java.util.List;

import br.com.carlettisolucoes.bestway.Way;

public interface MapReader {

	List<Way> read(InputStream is);

}