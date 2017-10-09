package model;

public class CustomerModel {
	private int customer_id;
	private String customer_name;
	private String customer_description;
	private boolean customer_isdeleted;
	
	public CustomerModel(int c_id, boolean isdeleted, String c_name, String c_description) {
		this.customer_id = c_id;
		this.customer_isdeleted = isdeleted;
		this.customer_name = c_name;
		this.customer_description = c_description;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_description() {
		return customer_description;
	}

	public void setCustomer_description(String customer_description) {
		this.customer_description = customer_description;
	}

	public boolean isCustomer_isdeleted() {
		return customer_isdeleted;
	}

	public void setCustomer_isdeleted(boolean customer_isdeleted) {
		this.customer_isdeleted = customer_isdeleted;
	}
	
}
