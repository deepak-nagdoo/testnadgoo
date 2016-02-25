package shopnow.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private int order_id;
	private String ordernumber;
	private float total;
	private User user = new User();
	private String remark;
	private String date;
	private OrderStatus orderStatus = new OrderStatus();
	private List<OrderList> orderlistlist = new ArrayList<>();
	private String orderContact;
	private String orderAddress;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<OrderList> getOrderlistlist() {
		return orderlistlist;
	}
	public void setOrderlistlist(List<OrderList> orderlistlist) {
		this.orderlistlist = orderlistlist;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderContact() {
		return orderContact;
	}
	public void setOrderContact(String orderContact) {
		this.orderContact = orderContact;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", ordernumber=" + ordernumber + ", total=" + total + ", user=" + user
				+ ", remark=" + remark + ", date=" + date + ", orderStatus=" + orderStatus + ", orderlistlist="
				+ orderlistlist + ", orderContact=" + orderContact + ", orderAddress=" + orderAddress + "]";
	}
 
}
