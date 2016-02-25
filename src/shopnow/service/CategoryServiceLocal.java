package shopnow.service;

import java.util.List;

import javax.ejb.Local;

import shopnow.domain.Category;
import shopnow.domain.ClientFile;
import shopnow.domain.Product;
import shopnow.domain.SubCategory;

@Local
public interface CategoryServiceLocal {

	List<Category> getAllCategory();

	List<Product> getAllProduct();

	List<Product> getProductByCategoryId(int categoryId);

	List<SubCategory> getSubCategory(int categoryId);

	List<Product> getProductByCategoryId(List<SubCategory> subcategroys);

	List<Product> getProductByCategoryId(SubCategory subcategory);

	String setUploadOrderDetail(ClientFile clientFileObj);

}
