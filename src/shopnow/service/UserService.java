package shopnow.service;

import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import shopnow.dao.UserDao;
import shopnow.dao.UserDaoImpl;
import shopnow.domain.Order;
import shopnow.domain.OrderList;
import shopnow.domain.User;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceLocal {

    /**
     * Default constructor. 
     */
	UserDao userDao = new UserDaoImpl();
	
    public UserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.validateUser(username,password);
	}

	public Map<Integer, String> getAllRole() {
		// TODO Auto-generated method stub
		return userDao.getAllRole();
	}

	@Override
	public int getOrderCount() {
		// TODO Auto-generated method stub
		return userDao.getOrderCount();
	}

	@Override
	public String placeOrderlist(List<OrderList> orderListlist) {
		// TODO Auto-generated method stub
		return userDao.placeOrderList(orderListlist);
	}

	@Override
	public String placeOrder(Order order) {
		// TODO Auto-generated method stub
		return userDao.placeOrder(order);
	}

	@Override
	public List<Order> getuserorders(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserOrders(user);
	}

	@Override
	public String cancelOrder(Order order) {
		// TODO Auto-generated method stub
		return userDao.cancelOrder(order);
	}

	@Override
	public boolean registerNewUser(User regUser) {
		// TODO Auto-generated method stub
		return userDao.registerNewUser(regUser);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

}
