package shopnow.domain;

import java.util.HashMap;
import java.util.Map;

import shopnow.dao.UserDaoImpl;
import shopnow.service.UserService;

public class Role {
	private String role;
	private int role_id;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "Role [role=" + role + ", role_id=" + role_id + "]";
	}
	
 
}
