package shopnow.bean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import shopnow.domain.Order;
import shopnow.domain.OrderList;
import shopnow.domain.Product;
import shopnow.domain.User;
import shopnow.service.UserServiceLocal;

@ManagedBean
@SessionScoped
public class UserBean {
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	@EJB
	private UserServiceLocal userService;
	@ManagedProperty("#{productBean}")
	ProductBean productBean;
	private List<Product> productslist;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String checkuser;
	private String username;
	private String password;
	private User user = new User();
	private User registerUser = new User();
	private int items;
	private float payamount;
	private String disablevalue1 ;
	private String disablevalue2 ;
	private String disablevalue3 ;
	private String disablevalue4 ;
	private String currentpage;
	private List<Product> craftproductlist = new ArrayList<>();
	private List<OrderList> orderListlist = new ArrayList<>();
	private int maxcount;
	private List<Order> userorders = new ArrayList<>();
	private boolean setFieldOpen = true;
	private String orderContact;
	private String orderAddress;
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

	public boolean isSetFieldOpen() {
		return setFieldOpen;
	}

	public void setSetFieldOpen(boolean setFieldOpen) {
		this.setFieldOpen = setFieldOpen;
	}

	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Order> getUserorders() {
		User user = (User) session.getAttribute("user");
		if(user!=null && checkuser!=null && checkuser.equals("valid"))
		userorders = userService.getuserorders(user);
		return userorders;
	}



	public void setUserorders(List<Order> userorders) {
		this.userorders = userorders;
	}



	public String getCheckuser() {
		return checkuser;
	}

	public void setCheckuser(String checkuser) {
		this.checkuser = checkuser;
	}

	public String getDisablevalue2() {
		return disablevalue2;
	}

	public void setDisablevalue2(String disablevalue2) {
		this.disablevalue2 = disablevalue2;
	}

	public String getDisablevalue3() {
		return disablevalue3;
	}

	public void setDisablevalue3(String disablevalue3) {
		this.disablevalue3 = disablevalue3;
	}

	public String getDisablevalue4() {
		return disablevalue4;
	}

	public void setDisablevalue4(String disablevalue4) {
		this.disablevalue4 = disablevalue4;
	}
	

	public String getDisablevalue1() {
		if(orderListlist.size()<=0){
			value1="";
			disablevalue1="disable";
		}
		else disablevalue1="";
		return disablevalue1;
	}

	public void setDisablevalue1(String disablevalue1) {
		this.disablevalue1 = disablevalue1;
	}

	public String getValue1() {
		if (checkuser != null) {
			if (checkuser.equals("valid")) {
				user = (User) session.getAttribute("user");
				if (user != null) {
					value1="Bucket";
					disablevalue1="";
					value2 = "Welcome :" + user.getFirstname();
					disablevalue2="disable";
					value3 = "";
					disablevalue3="disable";
					value4 = "Logout";
					disablevalue4="";
					maxcount = userService.getOrderCount() + 1;
				}
			}
			if (!checkuser.equals("valid")) {
				value1="Bucket";
				disablevalue1="";
				value2 = "Login";
				disablevalue2="";
				value3 = "Register";
				disablevalue3="";
				value4 = "";
				disablevalue4="disable";
				maxcount = userService.getOrderCount() + 1;
			}
		} else {
			value1="Bucket";
			disablevalue1="";
			value2 = "Login";
			disablevalue2="";
			value3 = "Register";
			disablevalue3="";
			value4 = "";
			disablevalue4="disable";
			maxcount = userService.getOrderCount() + 1;
		}

		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	
	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Product> getCraftproductlist() {
		return craftproductlist;
	}

	public void setCraftproductlist(List<Product> craftproductlist) {
		this.craftproductlist = craftproductlist;
	}

	public float getPayamount() {
		return payamount;
	}

	public void setPayamount(float payamount) {
		this.payamount = payamount;
	}

	public List<OrderList> getOrderListlist() {
		if (session != null) {
			List<OrderList> list = (ArrayList<OrderList>) session.getAttribute("orderListlist");
			if (list != null)
				orderListlist = list;
		}
		return orderListlist;
	}

	public void setOrderListlist(List<OrderList> orderListlist) {
		this.orderListlist = orderListlist;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	
	public String changeFieldOpen(){
		setFieldOpen = false;
		return null;
	}

	public String addtocart() {
		items = 0;
		payamount = 0;
		currentpage="index";
		productslist = productBean.getProductlistbycategory();
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		Product product = ((Product) requestmap.get("product"));
		OrderList orderList = new OrderList();
		String orderNumber = getDate() + maxcount;
		orderList.setOrderNumber(orderNumber);
		// orderList.setUser(user);
		for (Product product2 : productslist) {
			if (product.getProduct_id() == product2.getProduct_id()) {
				product2.setQuantity(product2.getQuantity()+1);
				System.out.println(product2);
				orderList.setQuantity(product2.getQuantity());
			}
		}
		List<OrderList> removeItem = new ArrayList<>();
		for (OrderList orderList1 : orderListlist) {
			if ((orderList1.getProduct().getPrice()*orderList1.getProduct().getQuantity())!=orderList1.getSum() || orderList1.getProduct().getProduct_id()==product.getProduct_id()) {
				removeItem.add(orderList1);
			}
		}
		orderListlist.removeAll(removeItem);
		productBean.setProductlistbycategory(productslist);
		orderList.setProduct(product);
		orderList.getOrderStatus().setOrderStatus_id(1);
		orderList.setDate(getDate());
		// orderList.setQuantity(quantity);
		Collections.sort(orderListlist);
		orderList.setSum(orderList.getProduct().getQuantity() * orderList.getProduct().getPrice());
		if(orderList.getProduct().getQuantity()>=1)
		orderListlist.add(orderList);
		// items= orderListlist.size();
		for (OrderList orderList2 : orderListlist) {
			items = orderList2.getQuantity() + items;
			payamount = orderList2.getSum() + payamount;
		}
		System.out.println(orderListlist);
		session.setAttribute("orderListlist", orderListlist);
		session.setAttribute("item", items);
		return "index.xhtml?cartmessage=Valid Add &faces-redirect=true";
	}

	public String removeFromCart() {
		items=0;
		payamount=0;
		currentpage="index";
		productslist = productBean.getProductlistbycategory();
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		Product product = ((Product) requestmap.get("product"));
		List<OrderList> removeitem = new ArrayList<>();
		if (product.getQuantity()<=0) {
			return "index.xhtml?cartmessage=Valid Add &faces-redirect=true";
		}
		for (Product product2 : productslist) {
			if (product.getProduct_id() == product2.getProduct_id()) {
				product2.setQuantity(product2.getQuantity() - 1);
			}
		}
		productBean.setProductlistbycategory(productslist);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getProduct_id()==product.getProduct_id() ){
				if(orderList.getProduct().getQuantity()<=0)
					removeitem.add(orderList);
				else{
				//orderList.getProduct().setQuantity(orderList.getProduct().getQuantity()-1);
				orderList.setQuantity(orderList.getQuantity()-1);
				orderList.setSum(orderList.getProduct().getPrice()*orderList.getProduct().getQuantity());
				}
			}
		}
		for (OrderList orderList1 : orderListlist) {
			if ((orderList1.getProduct().getPrice()*orderList1.getProduct().getQuantity())!=orderList1.getSum() || orderList1.getProduct().getProduct_id()==product.getProduct_id()) {
				removeitem.add(orderList1);
			}
		}
		orderListlist.removeAll(removeitem);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getQuantity()>0){	
			items = orderList.getProduct().getQuantity() + items;
			payamount = orderList.getSum() + payamount;
			}else
				orderList.setSum(0);
		}
		// orderList.setUser(user);
		// orderList.setQuantity(quantity);
		Collections.sort(orderListlist);
		System.out.println(orderListlist);
		session.setAttribute("orderListlist", orderListlist);
		session.setAttribute("item", items);
		return "index.xhtml?cartmessage=Valid Add &faces-redirect=true";
	}

	public String addtoorderlist() {
		items = 0;
		payamount = 0;
		currentpage="Bucket";
		productslist = productBean.getProductlistbycategory();
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		OrderList orderList3  = ((OrderList) requestmap.get("orderlist"));
		orderList3.setUser((User) session.getAttribute("user"));
		System.out.println("----orderlist object request:"+orderList3);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getProduct_id()==orderList3.getProduct().getProduct_id()){
				orderList.getProduct().setQuantity(orderList.getProduct().getQuantity()+1);
				orderList.setQuantity(orderList.getProduct().getQuantity());
				if(orderList3.getUser()!=null)
				orderList.setUser(orderList3.getUser());
				orderList.setSum(orderList.getProduct().getQuantity()*orderList.getProduct().getPrice());
			}
			items = orderList.getQuantity() + items;
			payamount = orderList.getSum() + payamount;
		}
		session.setAttribute("orderListlist", orderListlist);
		session.setAttribute("item", items);
		return "Bucket.xhtml?&faces-redirect=true";
	}

	public String removefromorderlist() {
		items = 0;
		payamount = 0;
		currentpage="Bucket";
		productslist = productBean.getProductlistbycategory();
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		OrderList orderList3  = ((OrderList) requestmap.get("orderlist"));
		List<OrderList> removeitem = new ArrayList<>();
		if(orderList3.getProduct().getQuantity()<=0){
			for (OrderList orderList : orderListlist) {
				if(orderList.getProduct().getProduct_id()==orderList3.getProduct().getProduct_id()){
					orderList.getProduct().setQuantity(orderList.getProduct().getQuantity()-1);
					orderList.setQuantity(orderList.getProduct().getQuantity());
					orderList.setSum(orderList.getProduct().getQuantity()*orderList.getProduct().getPrice());
				}
				if (orderList.getProduct().getQuantity()<=0) {
					removeitem.add(orderList);
				}
			}
			orderListlist.removeAll(removeitem);
			return "Bucket.xhtml?&faces-redirect=true";
		}
		System.out.println("----orderlist remove:"+orderList3);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getProduct_id()==orderList3.getProduct().getProduct_id()){
				orderList.getProduct().setQuantity(orderList.getProduct().getQuantity()-1);
				orderList.setQuantity(orderList.getProduct().getQuantity());
				orderList.setSum(orderList.getProduct().getQuantity()*orderList.getProduct().getPrice());
			}
			if (orderList.getProduct().getQuantity()<=0) {
				removeitem.add(orderList);
			}
		}
		for (OrderList orderList1 : orderListlist) {
			if ((orderList1.getProduct().getPrice()*orderList1.getProduct().getQuantity())!=orderList1.getSum() && orderList1.getProduct().getProduct_id()==orderList3.getProduct().getProduct_id()) {
				removeitem.add(orderList1);
			}
		}
		orderListlist.removeAll(removeitem);
		for (OrderList orderList : orderListlist) {
			items = orderList.getQuantity() + items;
			payamount = orderList.getSum() + payamount;
		}
		session.setAttribute("orderListlist", orderListlist);
		session.setAttribute("item", items);
		if(orderListlist.size()<=0){
			return "index.xhtml?faces-redircet=true";
		}
		return "Bucket.xhtml?&faces-redirect=true";
	}

	public String validateUser() {
		if (username == null || password == null)
			return "index.xhtml?usermessage=Please enter fields&faces-redirect=true";
		else {
			user = userService.validateUser(username, password);
			System.out.println("order to be placed"+ orderListlist);
			if (user != null) {
				session.setAttribute("user", user);
				productBean.setClientName(user.getFirstname());
				checkuser = "valid";
				if (currentpage!=null)
				if (currentpage.equals("index")) {
					return "index.xhtml?usermessage=Valid user&faces-redirect=true";
				}else return "Bucket.xhtml?usermessage=Valid user&faces-redirect=true";
				return "index.xhtml?usermessage=Valid user&faces-redirect=true";
			} else {
				checkuser = null;
				return "Login.xhtml?usermessage=Invalid Username &faces-redirect=true";
			}
		}
	}

	public String placeOrder(){
		currentpage="Bucket";
		if(checkuser==null )
			return "Login.xhtml?ordermessage=Please login to place order&faces-redirect=true";
		if(orderListlist.size()<=0)
			return "index.xhtml?ordermessage=Please select item to place order&faces-redirect=true";
		if(checkuser.equals("valid")){
			User user = (User)session.getAttribute("user");
			for (OrderList orderList : orderListlist) {
				orderList.setUser(user);
			}
			//String message = userService.placeOrderlist(orderListlist);
			Order order = new Order();
			order.setDate(getDate());
			order.setTotal(payamount);
			order.setUser(user);
			order.setOrdernumber(orderListlist.get(0).getOrderNumber());
			if(null!=orderContact && !("").equalsIgnoreCase(orderContact)){
				order.setOrderContact(orderContact);
			}else order.setOrderContact(user.getMobile());
			if(null!=orderAddress && !("").equalsIgnoreCase(orderAddress)){
				order.setOrderAddress(orderAddress);
			}else order.setOrderAddress(user.getAddress().getAddress());
			String orderlistmessage = userService.placeOrderlist(orderListlist);
			String ordermessage = userService.placeOrder(order);
			System.out.println("----place orderlist-->"+orderListlist.get(0).getDate());
			System.out.println("order----final"+order.getDate());
			productslist = productBean.getProductlistbycategory();
			for (OrderList orderList : orderListlist) {
				for (Product product : productslist) {
					if(orderList.getProduct().getProduct_id()==product.getProduct_id())
						product.setQuantity(0);
				}
			}
			productBean.setProductlistbycategory(productslist);
			orderListlist.clear();
			items=0;
			payamount=0;
			if(orderlistmessage.equals("success") && ordermessage.equals("success"))
				return "Bucket.xhtml?message="+orderlistmessage+" and "+ordermessage+"&faces-redircet=true";
			else
			return "index.xhtml?message="+orderlistmessage+" and "+ordermessage+"&faces-redircet=true";
		}else 
			return "Bucket.xhtml?ordermessage=Please login with valid Username and Password&faces-redirect=true";
	}
	
	public String removeItem(){
		items=0;
		payamount=0;
		currentpage="index";
		productslist = productBean.getProductlistbycategory();
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		 OrderList orderListobj = ((OrderList) requestmap.get("orderlist"));
		List<OrderList> removeitem = new ArrayList<>();
		for (Product product2 : productslist) {
			if (orderListobj.getProduct().getProduct_id() == product2.getProduct_id()) {
				product2.setQuantity(0);
			}
		}
		productBean.setProductlistbycategory(productslist);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getProduct_id()==orderListobj.getProduct().getProduct_id() ){
					removeitem.add(orderList);
				}
			}
		for (OrderList orderList1 : orderListlist) {
			if ((orderList1.getProduct().getPrice()*orderList1.getProduct().getQuantity())!=orderList1.getSum() && orderList1.getProduct().getProduct_id()==orderListobj.getProduct().getProduct_id()) {
				removeitem.add(orderList1);
			}
		}
		orderListlist.removeAll(removeitem);
		for (OrderList orderList : orderListlist) {
			if(orderList.getProduct().getQuantity()>0){	
			items = orderList.getProduct().getQuantity() + items;
			payamount = orderList.getSum() + payamount;
			}else
				orderList.setSum(0);
		}
		// orderList.setUser(user);
		// orderList.setQuantity(quantity);
		Collections.sort(orderListlist);
		System.out.println(orderListlist);
		session.setAttribute("orderListlist", orderListlist);
		session.setAttribute("item", items);
		if(orderListlist.size()<=0){
			return "index.xhtml?faces-redircet=true";
		}
		return "Bucket.xhtml?&faces-redirect=true";
	}
	
	public String getDate() {
		Date currentdate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(currentdate);
	}

	public String logout() {
		session.invalidate();
		return "index.xhtml?faces-redirect=true";
	}
	
	public String cancelOrder(){
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		 Order order = ((Order) requestmap.get("order"));
		 System.out.println("cancel order--->"+order);
		 String message = userService.cancelOrder(order);
		return "ViewOrders.xhtml?faces-redirect=true";
	}

	public User getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(User registerUser) {
		this.registerUser = registerUser;
	}

	public String registerNewUser(){
		registerUser.setUsername(registerUser.getEmail());
		boolean flag = userService.registerNewUser(registerUser);
			System.out.println(registerUser+"************flag***********"+flag) ;
		return null;
	}
	
	public String updateUser(){
		System.out.println("*************new User Value********"+user);
		boolean flag = userService.updateUser(user);
		setFieldOpen = true;
		return null;
	}
	
	public void checkUserLogin(){
		if(null!=checkuser){
			
		} else
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml?uploadMessage=Please Login First To Upload& faces-redirect=true");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}