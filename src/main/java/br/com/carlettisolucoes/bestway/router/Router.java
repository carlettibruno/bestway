package br.com.carlettisolucoes.bestway.router;

import java.util.List;

import br.com.carlettisolucoes.bestway.way.Way;

public interface Router {

	List<Way> findBestRoute();

}