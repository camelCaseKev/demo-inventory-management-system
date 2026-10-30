/**Filename: Product.java - IMS (superclass of all product classes/categories)
 * Author:   Kevin Viren 
 * Date:     4/26/2024
 */

public class Product {
	private String idNum;
	private String dept;
	private String name;
	private String quantity;
	private String price;
	private final static int ID_NUM_LENGTH = 3;
	private final static int QUANTITY_LENGTH = 4;
	private final static int NAME_LENGTH = 40;
	private final static int DEPT_NAME_LENGTH = 4;
	private final static int RECORD_LENGTH = 200;
	
	public Product() {
		setIdNumber(000);
		setName("0000000000000000000000000000000000000000");
		setQuantity(0);
		setPrice(0.0);
		setDepartment("Misc");
	}
	
	public Product(int idNum, String name, int quantity, double price) {
		setIdNumber(idNum);
		setName(name);
		setQuantity(quantity);
		setPrice(price);
		setDepartment("Misc");
	}
	
	
	// GET METHODS
	public String getIdNumber() {
		return idNum;
	}
	
	public String getName() {
		return name;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getDepartment() {
		return dept;
	}
	
	public static int getIdNumberLength() {
		return ID_NUM_LENGTH;
	}
	
	public static int getDepartmentNameLength() {
		return DEPT_NAME_LENGTH;
	}
	
	public static int getNameLength() {
		return NAME_LENGTH;
	}
	
	public static int getQuantityLength() {
		return QUANTITY_LENGTH;
	}
	
	public static int getRecordLength() {
		return RECORD_LENGTH;
	}
	
	
	// SET METHODS
	public void setIdNumber(int num) { //Code below adds leading zeros to the idNum if the argument is less than ID_NUM_LENGTH (ensures consistency)
		String strNum = num+"";
		int numLength = strNum.length();
		int prefixNums; //Number of leading zeros attached to Id numbers when the argument is less than the ID_NUM_LENGTH
		String prefixZeros = "";
		
		if(numLength < ID_NUM_LENGTH) {
			prefixNums = ID_NUM_LENGTH - numLength;
			
			for(int i = 0; i < prefixNums; ++i) {
				prefixZeros += "0"; 
			}	
		}
		idNum = prefixZeros + num;
	}
	
	
	public void setName(String name) {
		int nameLength = name.length();
		
		
		if(nameLength < NAME_LENGTH) {
			int diff = NAME_LENGTH - nameLength; 
			for(int i=0; i < diff; ++i) {
				name += " ";
			}
		}
		if(nameLength > NAME_LENGTH) {
			name = name.substring(0, NAME_LENGTH);
		}
		this.name = name;
	}
	
	
	public void setQuantity(int num) { //Code below adds leading zeros to the quantity if the argument is less than QUANTITY_LENGTH (ensures consistency)
		String strNum = num+"";
		int numLength = strNum.length();
		int prefixNums; //Number of leading zeros attached to numbers when the argument is less than the QUANTITY_LENGTH
		String prefixZeros = "";
		
		if(numLength < QUANTITY_LENGTH) {
			prefixNums = QUANTITY_LENGTH - numLength;
			
			for(int i = 0; i < prefixNums; ++i) {
				prefixZeros += "0"; 
			}	
		}
		quantity = prefixZeros + num;
	}
	
	public void setPrice(double price) { // Ensures that prices are displayed with two digits for cents, not one
		String strPrice = price+"";
		String[] priceParts = strPrice.split("\\.");
		
		if(priceParts[1].length() < 2) {
			strPrice += "0";
		}
		this.price = strPrice;
	}
	
	public void setDepartment(String departmentName) {
		if(departmentName.length() > DEPT_NAME_LENGTH) {
			departmentName = departmentName.substring(0, DEPT_NAME_LENGTH);
		}
		if(departmentName.length() < DEPT_NAME_LENGTH) {
			departmentName = "Misc";
		}
		
		dept = departmentName;
	}
	
	
	// toString method used to build records
	public String toString() {
		String s = "ID#: "+getIdNumber()+"  ||  DEPARTMENT: "+getDepartment()+"  ||  DESCRIPTION: "+getName()+"  ||  QUANTITY: "+getQuantity()+"  ||  PRICE: $"+getPrice();
		
		int sLength = s.length();
		if(sLength < RECORD_LENGTH) {
			int diff = RECORD_LENGTH - sLength; 
			for(int i=0; i < diff; ++i) {
				s += " ";
			}
		}
		if(sLength > NAME_LENGTH) {
			s = s.substring(0, RECORD_LENGTH);
		}
		return s;
	}
}