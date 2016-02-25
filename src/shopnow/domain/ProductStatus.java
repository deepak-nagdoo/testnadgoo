package shopnow.domain;

public class ProductStatus {
	private int productstatus_id;
	private String status;
	public ProductStatus() {
		// TODO Auto-generated constructor stub
	}
	public int getProductstatus_id() {
		return productstatus_id;
	}
	public void setProductstatus_id(int productstatus_id) {
		this.productstatus_id = productstatus_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductStatus [productstatus_id=" + productstatus_id + ", status=" + status + "]";
	}
	
}