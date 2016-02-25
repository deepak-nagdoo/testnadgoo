package shopnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shopnow.domain.Order;
import shopnow.domain.OrderList;
import shopnow.domain.Product;
import shopnow.domain.User;
import shopnow.util.Util;

public class UserDaoImpl implements UserDao {

	private Map<Integer, String> rolemap = new HashMap<>();
	private Map<Integer, String> orderstatusmap = new HashMap<>();
	private CategoryDao categoryDao = new CategoryDaoImpl();
	private Connection connection = null;

	public ResultSet select(String sql) {
		ResultSet resultSet = null;
		try {
			// Connection connection = connectionUtil.getConnection();
			// connection = datasource.getConnection();
			connection = Util.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public Map<Integer, String> getAllRole() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM ROLE";
		Connection connection = Util.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				rolemap.put(resultSet.getInt("role_id"), resultSet.getString("role"));
			}
			return rolemap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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

	@Override
	public Map<Integer, String> getAllOrderStatus() {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM `orderstatus`";
		Connection connection = Util.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderstatusmap.put(resultSet.getInt("orderstatus_id"), resultSet.getString("status"));
			}
			return orderstatusmap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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

	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		getAllRole();
		User user = new User();
		String query = "SELECT * FROM user where username='" + username + "' and password='" + password + "'";
		ResultSet resultSet = select(query);
		try {
			while (resultSet.next()) {
				user.setUser_id(resultSet.getInt("user_id"));
				user.setFirstname(resultSet.getString("firstname"));
				user.setLastname(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setMobile(resultSet.getString("mobile"));
				user.setAlternatemobile(resultSet.getString("alternatemobile"));
				user.setUsername(username);
				user.setPassword(password);
				user.getAddress().setAddress_id(resultSet.getInt("address_id"));
				user.getRole().setRole_id(resultSet.getInt("role_id"));
				user.getRole().setRole(rolemap.get(user.getRole().getRole_id()));
			}
			resultSet.close();
			String address = "select * from address where address_id=" + user.getAddress().getAddress_id();
			resultSet = select(address);
			while (resultSet.next()) {
				user.getAddress().setColony(resultSet.getString("colony"));
				user.getAddress().setLandmark(resultSet.getString("landmark"));
				user.getAddress().setStreetname(resultSet.getString("streetname"));
				user.getAddress().setAddress(resultSet.getString("address"));
			}
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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

	@Override
	public int getOrderCount() {
		// TODO Auto-generated method stub
		String query = "SELECT count(order_id) COUNT FROM `order`";
		ResultSet resultSet = select(query);
		int count = 0;
		try {
			while (resultSet.next()) {
				count = resultSet.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return count;
	}

	@Override
	public String placeOrderList(List<OrderList> orderListlist) {
		// TODO Auto-generated method stub
		List<Integer> countlist = new ArrayList<>();
		for (OrderList orderList : orderListlist) {
			String query = "insert into orderlist(product_id,user_id,ordernumber,orderstatus_id,quantity,sum,date) values("
					+ orderList.getProduct().getProduct_id() + "," + orderList.getUser().getUser_id() + ",'"
					+ orderList.getOrderNumber() + "',1," + orderList.getProduct().getQuantity() + ","
					+ orderList.getSum() + ",'" + orderList.getDate() + "')";
			countlist.add(update(query));
		}
		for (Integer integer : countlist) {
			if (integer == 0 || integer == -1)
				return "fail";
		}
		return "success";
	}

	@Override
	public String placeOrder(Order order) {
		// TODO Auto-generated method stub
		String query = "insert into `order` (ordernumber,total,user_id,remark,date,orderstatus_id,usercontact,deliveryaddress) values('" + order.getOrdernumber()
				+ "'," + order.getTotal() + "," + order.getUser().getUser_id() + ",'','" + order.getDate() + "',1,'"+order.getOrderContact()+"','"+order.getOrderAddress()+"')";
		int integer = update(query);
		if (integer == 0 || integer == -1)
			return "fail";
		else
			return "success";
	}

	public int update(String query) {
		Connection connection = Util.getConnection();
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

	@Override
	public List<Order> getUserOrders(User user) {
		// TODO Auto-generated method stub
		String getallorder = "select * from `order` where user_id=" + user.getUser_id() + " order by order_id desc";
		List<Order> orders = new ArrayList<>();
		List<Product> products = categoryDao.getAllProduct();
		Map<Integer, Product> productmap = new HashMap<>();
		for (Product product : products) {
			productmap.put(product.getProduct_id(), product);
		}
		getAllOrderStatus();
		ResultSet resultSet = select(getallorder);
		try {
			while (resultSet.next()) {
				Order order = new Order();
				order.setOrder_id(resultSet.getInt("order_id"));
				order.setOrdernumber(resultSet.getString("ordernumber"));
				order.setRemark(resultSet.getString("remark"));
				order.setTotal(resultSet.getFloat("total"));
				order.setDate(resultSet.getString("date"));
				order.getOrderStatus().setOrderStatus_id(resultSet.getInt("orderstatus_id"));
				order.getOrderStatus().setStatus(orderstatusmap.get(order.getOrderStatus().getOrderStatus_id()));
				order.setUser(user);
				orders.add(order);
			}
			resultSet.close();
			String getallorderlistbyuser = "SELECT * FROM `orderlist` where user_id=" + user.getUser_id();
			resultSet = select(getallorderlistbyuser);
			List<OrderList> orderLists = new ArrayList<>();
			while (resultSet.next()) {
				OrderList orderList = new OrderList();
				orderList.setOrderList_id(resultSet.getInt("orderlist_id"));
				orderList.setDate(resultSet.getString("date"));
				orderList.setOrderNumber(resultSet.getString("ordernumber"));
				orderList.setQuantity(resultSet.getInt("quantity"));
				orderList.setSum(resultSet.getFloat("sum"));
				orderList.setUser(user);
				orderList.getOrderStatus().setOrderStatus_id(resultSet.getInt("orderstatus_id"));
				orderList.getProduct().setProduct_id(resultSet.getInt("product_id"));
				orderList.setProduct(productmap.get(orderList.getProduct().getProduct_id()));
				orderList.getOrderStatus()
						.setStatus(orderstatusmap.get(orderList.getOrderStatus().getOrderStatus_id()));
				orderLists.add(orderList);
			}
			for (Order order : orders) {
				for (OrderList orderList : orderLists) {
					orderList.setProduct(productmap.get(orderList.getProduct().getProduct_id()));
					if(order.getOrdernumber().equals(orderList.getOrderNumber())){
						order.getOrderlistlist().add(orderList);
					}
				}
			}
			return orders;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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

	@Override
	public String cancelOrder(Order order) {
		String changeStatus = "update `order` set orderstatus_id=4 where order_id="+order.getOrder_id();
		update(changeStatus);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean registerNewUser(User regUser) {
		// TODO Auto-generated method stub
		String regUserQuary = "INSERT INTO USER (firstname,email,mobile,username,`password`) VALUES ('"+regUser.getFirstname()+"','"+regUser.getEmail()+"','"+regUser.getMobile()+"','"+regUser.getUsername()+"','"+regUser.getPassword()+"')";
		String getUserid = "SELECT user_id from user where email='"+regUser.getEmail()+"'";
		int check = update(regUserQuary);
		ResultSet resultSet = select(getUserid);
		try {
			int userid =0;
			while (resultSet.next()) {
				userid = resultSet.getInt("user_id");
			}
			if(userid!=0 || check >0){
				String insertaddress = "INSERT INTO address (`address`,user_id) VALUE ('"+regUser.getAddress().getAddress()+"','"+userid+"')";
				update(insertaddress);
				return true;
			}else return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		String updateUserQuery = "update user set firstname='"+user.getFirstname()+"',email='"+user.getEmail()+"',mobile='"+user.getMobile()+"' where user_id="+user.getUser_id();
		String updateAddressQuery = "update address set address='"+user.getAddress().getAddress()+"' WHERE USER_ID="+user.getUser_id();
		int ucheck = update(updateUserQuery);
		if(ucheck>0){
			update(updateAddressQuery);
			return true;
		}else {
			return false;
		}
	}
}
