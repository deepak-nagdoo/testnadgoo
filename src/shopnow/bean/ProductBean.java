package shopnow.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import shopnow.domain.Category;
import shopnow.domain.ClientFile;
import shopnow.domain.OrderList;
import shopnow.domain.Product;
import shopnow.domain.SubCategory;
import shopnow.service.CategoryServiceLocal;

@ManagedBean
@SessionScoped
public class ProductBean {

	private List<Category> categorylist = new ArrayList<>();
	private List<Product> productlist = new ArrayList<>();
	private List<Product> productlistbycategory = new ArrayList<>();
	private List<SubCategory> subcategorylist = new ArrayList<>();
	private ClientFile clientFileObj = new ClientFile();
	private String clientName;
	private String clientAddress;
	private String clientContact;
	private Part clientFile;
	

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientContact() {
		return clientContact;
	}

	public void setClientContact(String clientContact) {
		this.clientContact = clientContact;
	}

	public Part getClientFile() {
		return clientFile;
	}

	public void setClientFile(Part clientFile) {
		this.clientFile = clientFile;
	}

	@EJB
	private CategoryServiceLocal categoryService;

	public List<SubCategory> getSubcategorylist() {
		return subcategorylist;
	}

	public void setSubcategorylist(List<SubCategory> subcategorylist) {
		this.subcategorylist = subcategorylist;
	}

	public List<Category> getCategorylist() {
		categorylist = categoryService.getAllCategory();
		return categorylist;
	}

	public void setCategorylist(List<Category> categorylist) {
		this.categorylist = categorylist;
	}

	public List<Product> getProductlist() {
		productlist = categoryService.getAllProduct();
		return productlist;
	}

	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}

	public List<Product> getProductlistbycategory() {
		return productlistbycategory;
	}

	public void setProductlistbycategory(List<Product> productlistbycategory) {
		this.productlistbycategory = productlistbycategory;
	}

	public String getProductbyid() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		List<OrderList> list = null;
		if (session != null) {
			list = (ArrayList<OrderList>) session.getAttribute("orderListlist");
		}
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		Category category ;
		SubCategory subcategory ;
		if(requestmap.get("subcategory")==null){
		category = (Category) requestmap.get("category");
		subcategorylist = categoryService.getSubCategory(category.getCategory_id());
		if(subcategorylist!=null)
		if(subcategorylist.size()>0)
		productlistbycategory = null; else productlistbycategory = categoryService.getProductByCategoryId(category.getCategory_id());
		}
		else{
		subcategory = (SubCategory) requestmap.get("subcategory");
		subcategorylist = null;
		productlistbycategory = categoryService.getProductByCategoryId(subcategory);
		}
		if (list != null)
			for (OrderList orderList : list) {
				if(productlistbycategory!=null)
				for (Product product : productlistbycategory) {
					if (orderList.getProduct().getProduct_id() == product.getProduct_id()) {
						product.setQuantity(orderList.getProduct().getQuantity());
						}
				}
			}
		return "index.xhtml?faces-redirect=true";
	}
	
	public String getProductbysubid() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		List<OrderList> list = null;
		if (session != null) {
			list = (ArrayList<OrderList>) session.getAttribute("orderListlist");
		}
		Map<String, Object> requestmap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
		SubCategory subcategory = (SubCategory) requestmap.get("subcategory");
		if(subcategory!=null){
		subcategorylist = null;
		productlistbycategory = categoryService.getProductByCategoryId(subcategory);
		}
		if (list != null )
			for (OrderList orderList : list) {
				for (Product product : productlistbycategory) {
					if (orderList.getProduct().getProduct_id() == product.getProduct_id()) {
						product.setQuantity(orderList.getProduct().getQuantity());
						}
				}
			}
		return "index.xhtml?faces-redirect=true";
	}
	
	public String uploadOrderFile(){
/*
		System.out.println("file content type : "+clientFile.getContentType()); //application/octet-stream
		System.out.println("file header with string disposition: "+clientFile.getHeader("content-disposition")); // form-data; name="file"; filename="slf4j-api-1.5.8.jar" example 
		System.out.println("file header with string type: "+clientFile.getHeader("content-type"));// application/octet-stream
		System.out.println("file name : "+clientFile.getName()); // file
		System.out.println("file size : "+clientFile.getSize()); // 23445 
		System.out.println("file submitted name : "+clientFile.getSubmittedFileName()); // slf4j-api-1.5.8.jar
		System.out.println("file header names : "+clientFile.getHeaderNames());// [Content-Disposition, Content-Type] 
		System.out.println("file headers disposition: "+clientFile.getHeaders("content-disposition"));// [form-data; name="file"; filename="slf4j-api-1.5.8.jar"]
		System.out.println("file headers type: "+clientFile.getHeaders("content-type")); //[application/octet-stream]
		
*/	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String dateName = dateFormat.format(date);
		//System.out.println("sample date format : "+dateFormat.format(date));
		if(null!=clientFile){
			String fileSubmittedName	= clientFile.getSubmittedFileName();
			String filePath = "B:/finalproject/sampleUpload/"+clientName+"_"+clientContact+"_"+dateName+clientFile.getSubmittedFileName().substring(fileSubmittedName.lastIndexOf("."),fileSubmittedName.length());
			System.out.println("final path to upload : "+filePath);
			try {
				Path destination = Paths.get(filePath);
				InputStream bytes = null;
				if(null!=clientFile){
					bytes = clientFile.getInputStream();
					if(!destination.toFile().exists()){
						Files.copy(bytes, destination);
					}
				}
				if(destination.toFile().exists()){
					clientFileObj.setClientName(clientName);
					clientFileObj.setClientContact(clientContact);
					clientFileObj.setClientAddress(clientAddress);
					clientFileObj.setClientFilePath(filePath);
					String check = categoryService.setUploadOrderDetail(clientFileObj);
					if(null!=check){
						return "index.xhtml?uploadMessage=Order Received ! Will Contact You Soon & faces-redirect=true";
					}
					else{
						return "index.xhtml?uploadMessage=Error Occured Please Try Again Later & faces-redirect=true";
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		return "index.xhtml?uploadMessage=Select File to upload&faces-redirect=true";
		
		return "index.xhtml?uploadMessage=Try again after some time&faces-redirect=true";
	}
}