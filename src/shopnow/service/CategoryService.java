package shopnow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.PDLOverrideSupported;

import shopnow.dao.CategoryDao;
import shopnow.dao.CategoryDaoImpl;
import shopnow.domain.Category;
import shopnow.domain.ClientFile;
import shopnow.domain.Product;
import shopnow.domain.SubCategory;

@Stateless
@LocalBean
public class CategoryService implements CategoryServiceLocal {

	@Inject
	private CategoryDao categoryDao = new CategoryDaoImpl();

	private Map<Integer, Category> categorymap = new HashMap<>();
	private Map<Integer, SubCategory> subcategorymap = new HashMap<>();
	private List<Product> productlist = new ArrayList<>();

	public CategoryService() {
		// TODO Auto-generated constructor stub
		categorymap = categoryDao.getAllCatogary();
		productlist = categoryDao.getAllProduct();
		subcategorymap = categoryDao.getSubcategorymap();
		for (Map.Entry<Integer, Category> cat : categorymap.entrySet()) {
			for(Map.Entry<Integer, SubCategory> subcat : subcategorymap.entrySet()){
				if(subcat.getValue().getCategory_id()==cat.getValue().getCategory_id())
				cat.getValue().getSubcategorylist().add(subcat.getValue());
			}
		}
	}

	@Override
	public List<Category> getAllCategory() {
		if (categorymap!=null ||categorymap.size() != 0) {
			List<Category> categories = new ArrayList<>();
			for (Entry<Integer, Category> entry : categorymap.entrySet()) {
				Category category = new Category();
				category.setCategory_id(entry.getKey());
				category.setCategory(entry.getValue().getCategory());
				category.setSubcategorylist(entry.getValue().getSubcategorylist());
				categories.add(category);
			}
			return categories;
		}
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return categoryDao.getAllProduct();
	}

	@Override
	public List<Product> getProductByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		List<Product> productlistbycategory = new ArrayList<>();
		for (Product product : productlist) {
			if (product.getCategory().getCategory_id() == categoryId) {
				productlistbycategory.add(product);
			}
		}
		return productlistbycategory;
	}

	public Map<Integer, String> getAllProductStatus() {
		// TODO Auto-generated method stub
		return categoryDao.getAllProductStatus();
	}

	@Override
	public List<SubCategory> getSubCategory(int id) {
		// TODO Auto-generated method stub
		Map<Integer, SubCategory> map = subcategorymap;
		List<SubCategory> list = new ArrayList<>();
		for (Entry<Integer, SubCategory> entry : map.entrySet()) {
			if (entry.getValue().getCategory_id() == id)
				list.add(entry.getValue());
		}
		if (list.size() <= 0)
			return null;
		else
			return list;
	}

	@Override
	public List<Product> getProductByCategoryId(List<SubCategory> subcategroys) {
		// TODO Auto-generated method stub
		Map<Integer, SubCategory> map = new HashMap<>();
		for (SubCategory subCategory : subcategroys) {
			map.put(subCategory.getSubcategory_id(), subCategory);
		}
		List<Product> productlistbycategory = new ArrayList<>();
		for (Product product : productlist) {
			if (product.getSubCategory().getSubcategory_id() == product.getSubCategory().getSubcategory_id())
				productlistbycategory.add(product);
		}
		return productlistbycategory;
	}

	@Override
	public List<Product> getProductByCategoryId(SubCategory subcategory) {
		// TODO Auto-generated method stub
		List<Product> productlistbycategory = new ArrayList<>();
		System.out.println("subcat---->"+subcategory);
		System.out.println("productlist--->"+productlist);
		for (Product product : productlist) {
			if (product.getCategory().getCategory_id() == subcategory.getCategory_id() && product.getSubCategory().getSubcategory_id()==subcategory.getSubcategory_id()) {
				productlistbycategory.add(product);
			}
		}
		return productlistbycategory;
	}

	@Override
	public String setUploadOrderDetail(ClientFile clientFileObj) {
		// TODO Auto-generated method stub
		String check = categoryDao.uploadFileClientDetail(clientFileObj);
		return check;
	}
}
