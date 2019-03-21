package br.com.centralinf.bestway;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Way implements Serializable {
	
	private static final long serialVersionUID = 5577974199722246435L;
	
	WayId id;
	
	int factor;
	
	transient int length;
	
	List<Way> ways;

	public Way(WayId id, int factor) {
		super();
		this.id = id;
		this.factor = factor;
	}
	
	public void addWay(Way way) {
		if(ways == null) {
			ways = new ArrayList<>();
			ways.add(way);
		} else {
			int indexOf = ways.indexOf(way);
			if(indexOf == -1) {
				ways.add(way);
			}
		}
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
		return id + " f=" + factor + "; ways=" + (ways != null ? ways.size() : 0) + "]";
	}
	
}