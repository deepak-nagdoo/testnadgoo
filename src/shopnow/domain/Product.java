package shopnow.domain;

public class Product {
	private int product_id;
	private String productname;
	private float price;
	private String description;
	private String img;
	private ProductStatus productStatus= new ProductStatus();
	private Category category= new Category();
	private SubCategory subCategory = new SubCategory();
	private int quantity;
	
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public ProductStatus getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(ProductStatus productStatus) {
		this.productStatus = productStatus;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", productname=" + productname + ", price=" + price
				+ ", description=" + description + ", img=" + img + ", productStatus=" + productStatus + ", category="
				+ category + ", subCategory=" + subCategory + ", quantity=" + quantity + "]";
	}
	
	
}
