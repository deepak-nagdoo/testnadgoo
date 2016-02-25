package shopnow.service;

import java.util.List;

import javax.ejb.Local;

import shopnow.domain.Order;
import shopnow.domain.OrderList;
import shopnow.domain.User;

@Local
public interface UserServiceLocal {

	User validateUser(String username, String password);

	int getOrderCount();

	String placeOrderlist(List<OrderList> orderListlist);

	String placeOrder(Order order);

	List<Order> getuserorders(User user);

	String cancelOrder(Order order);

	boolean registerNewUser(User registerUser);

	boolean updateUser(User user);

}
