package br.com.carlettisolucoes.bestway;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Way implements Serializable, Comparable<Way> {

	private static final long serialVersionUID = 5577974199722246435L;

	private WayId id;

	private int factor;

	private int length;

	private List<Way> ways;

	public Way(WayId id) {
		super();
		this.id = id;
	}

	public Way(WayId id, int factor) {
		super();
		this.id = id;
		this.factor = factor;
	}

	public void addWay(Way way) {
		if (ways == null) {
			ways = new ArrayList<>();
			ways.add(way);
		} else {
			int indexOf = ways.indexOf(way);
			if (indexOf == -1) {
				ways.add(way);
			}
		}
	}

	public Way getBestWay() {
		Collections.sort(this.getWays());
		return this.getWays().get(0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Way other = (Way) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " f=" + factor + " len=" + length + "; ways=" + (ways != null ? ways.size() : 0) + "]";
	}

	public WayId getId() {
		return id;
	}

	public void setId(WayId id) {
		this.id = id;
	}

	public int getFactor() {
		return factor;
	}

	public void setFactor(int factor) {
		this.factor = factor;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public List<Way> getWays() {
		return ways;
	}

	public void setWays(List<Way> ways) {
		this.ways = ways;
	}

	@Override
	public int compareTo(Way o) {
		return new Integer(this.getLength()).compareTo(o.getLength());
	}

}