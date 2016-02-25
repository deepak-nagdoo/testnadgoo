package shopnow.domain;

public class SubCategory {

	private int subcategory_id;
	private String subcategory;
	private int category_id;
	
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "SubCategory [subcategory_id=" + subcategory_id + ", subcategory=" + subcategory + ", category_id="
				+ category_id + "]";
	}

	
}
