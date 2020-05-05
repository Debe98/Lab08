package it.polito.tdp.extflightdelays.model;

public class Collegamento {

	private int originAirportId;
	private int destinationAirportId;
	private int totDistance;
	private int cnt;
	
	public Collegamento(int originAirportId, int destinationAirportId, int totDistance, int cnt) {
		super();
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.totDistance = totDistance;
		this.cnt = cnt;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public void setOriginAirportId(int originAirportId) {
		this.originAirportId = originAirportId;
	}

	public int getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(int destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public int getTotDistance() {
		return totDistance;
	}

	public void setTotDistance(int totDistance) {
		this.totDistance = totDistance;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public void reverseToAndata() {
		if (originAirportId < destinationAirportId)
			return;
		int temp = originAirportId;
		originAirportId = destinationAirportId;
		destinationAirportId = temp;
	}
	
	public void mergeAndataRitorno(Collegamento c) {
		this.totDistance += c.totDistance;
		this.cnt += c.cnt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destinationAirportId;
		result = prime * result + originAirportId;
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
		Collegamento other = (Collegamento) obj;
		if (destinationAirportId != other.destinationAirportId)
			return false;
		if (originAirportId != other.originAirportId)
			return false;
		return true;
	}
	
	
}

