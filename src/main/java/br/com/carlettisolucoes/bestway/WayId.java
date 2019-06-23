package br.com.carlettisolucoes.bestway;

import java.io.Serializable;

public class WayId implements Serializable {
	
	private static final long serialVersionUID = 4203153761490535420L;

	Integer x;
	
	Integer y;

	public WayId(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Id [" + x + ", " + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		WayId other = (WayId) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

}