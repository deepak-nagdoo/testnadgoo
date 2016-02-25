package shopnow.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderList implements Comparable{
	private int orderList_id;
	private User user = new User();
	private Product product = new Product();
	private String orderNumber;
	private String date ;
	private OrderStatus orderStatus = new OrderStatus();
	private int quantity;
	private float sum;
	public int getOrderList_id() {
		return orderList_id;
	}
	public void setOrderList_id(int orderList_id) {
		this.orderList_id = orderList_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "OrderList [orderList_id=" + orderList_id + ", user=" + user + ", product=" + product + ", orderNumber="
				+ orderNumber + ", date=" + date + ", orderStatus=" + orderStatus + ", quantity=" + quantity + ", sum="
				+ sum + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + orderList_id;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderList other = (OrderList) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderList_id != other.orderList_id)
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (orderStatus == null) {
			if (other.orderStatus != null)
				return false;
		} else if (!orderStatus.equals(other.orderStatus))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public int compareTo(Object o) {
		OrderList ord = (OrderList)o;
		// TODO Auto-generated method stub
		return this.product.getProductname().compareTo(ord.product.getProductname());
	}
	
	
}
