package shopnow.dao;

import java.util.List;
import java.util.Map;

import shopnow.domain.Category;
import shopnow.domain.ClientFile;
import shopnow.domain.Product;
import shopnow.domain.SubCategory;

public interface CategoryDao {

	Map<Integer, Category> getAllCatogary();

	List<Product> getAllProduct();

	Map<Integer, String> getAllProductStatus();

	Map<Integer, SubCategory> getSubcategorymap();

	String uploadFileClientDetail(ClientFile clientFileObj);

}
