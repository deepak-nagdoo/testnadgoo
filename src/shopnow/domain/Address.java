package shopnow.domain;

public class Address {
	private int address_id;
	private String streetname;
	private String colony;
	private String landmark;
	private String address;
	
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", streetname=" + streetname + ", colony=" + colony + ", landmark="
				+ landmark + ", address=" + address + "]";
	}
	
}
