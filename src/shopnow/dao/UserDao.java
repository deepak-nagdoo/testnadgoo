package shopnow.dao;

import java.util.List;
import java.util.Map;

import shopnow.domain.Order;
import shopnow.domain.OrderList;
import shopnow.domain.User;

public interface UserDao {

	Map<Integer, String> getAllRole();

	User validateUser(String username, String password);

	int getOrderCount();

	String placeOrderList(List<OrderList> orderListlist);

	String placeOrder(Order order);

	List<Order> getUserOrders(User user);

	Map<Integer, String> getAllOrderStatus();

	String cancelOrder(Order order);

	boolean registerNewUser(User regUser);

	boolean updateUser(User user);
	
}
