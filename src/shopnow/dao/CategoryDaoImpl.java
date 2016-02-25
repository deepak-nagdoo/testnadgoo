package shopnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopnow.domain.Category;
import shopnow.domain.ClientFile;
import shopnow.domain.Product;
import shopnow.domain.SubCategory;
import shopnow.util.Util;

public class CategoryDaoImpl implements CategoryDao {

/*	@Resource(lookup = "java:jboss/datasources/SNS")
	private DataSource datasource;
*/	private Map<Integer, Category> categorymap = new HashMap<>();
	private Map<Integer, SubCategory> subcategorymap = new HashMap<>();
	private Map<Integer, String> productstatusmap = new HashMap<>();
	private List<Product> productlist = new ArrayList<>();
	private Connection connection = null;

	@Override
	public Map<Integer, String> getAllProductStatus() {
		String query = "select * from productstatus";
		ResultSet resultSet = select(query);
		try {
			
			while (resultSet.next()) {
				productstatusmap.put(resultSet.getInt("productstatus_id"), resultSet.getString("status"));
			}
			return productstatusmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public Map<Integer, Category> getAllCatogary() {
		String query = "select * from category";
		ResultSet resultSet = select(query);
		try {
			while (resultSet.next()) {
				Category category = new Category();
				category.setCategory_id(resultSet.getInt("category_id"));
				category.setCategory(resultSet.getString("category"));
				categorymap.put(resultSet.getInt("category_id"), category);
			}
			return categorymap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	

	}
		
	public Map<Integer, SubCategory> getSubCategory() {
		String query = "select * from subcategory";
		ResultSet resultSet = select(query);
		try {
			while (resultSet.next()) {
				SubCategory subCategory = new SubCategory();
				subCategory.setSubcategory_id(resultSet.getInt("subcategory_id"));
				subCategory.setSubcategory(resultSet.getString("subcategory"));
				subCategory.setCategory_id(resultSet.getInt("category_id"));
				subcategorymap.put(subCategory.getSubcategory_id(), subCategory);
			}
			return subcategorymap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	

	}

	public ResultSet select(String sql) {
		ResultSet resultSet = null;
		try {
			// Connection connection = connectionUtil.getConnection();
			// connection = datasource.getConnection();
			connection = Util.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			return resultSet;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		String query = "select * from product";
		getAllProductStatus();
		getSubCategory();
		categorymap = getAllCatogary();
		ResultSet resultSet = select(query);
		try {
			while (resultSet.next()) {
				Product product = new Product();
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProductname(resultSet.getString("productname"));
				product.setDescription(resultSet.getString("description"));
				product.setImg(resultSet.getString("img"));
				product.setPrice(resultSet.getFloat("price"));
				product.getProductStatus().setProductstatus_id(resultSet.getInt("productstatus_id"));
				product.getCategory().setCategory_id(resultSet.getInt("category_id"));
				product.getProductStatus().setStatus(productstatusmap.get(product.getProductStatus().getProductstatus_id()));
				product.getCategory().setCategory(categorymap.get(product.getCategory().getCategory_id()).getCategory());
				product.getSubCategory().setSubcategory_id(resultSet.getInt("subcategory_id"));
				product.setSubCategory(subcategorymap.get(product.getSubCategory().getSubcategory_id()));
				productlist.add(product);
			}
			return productlist;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Override
	public Map<Integer, SubCategory> getSubcategorymap() {
		// TODO Auto-generated method stub
		return getSubCategory();
	}

	@Override
	public String uploadFileClientDetail(ClientFile clientFileObj) {
		// TODO Auto-generated method stub
		String uploadFileQuery = "Insert into clientfile (clientName,clientContact,clientAddress,clientFilePath) values ('"+clientFileObj.getClientName()+"','"+clientFileObj.getClientContact()+"','"+clientFileObj.getClientAddress()+"','"+clientFileObj.getClientFilePath()+"')";
		int check = update(uploadFileQuery);
		if(check>0) return "success";
		else return null;
	}

	
	public int update(String query) {
		connection = Util.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			return preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}