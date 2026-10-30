/**Filename: Produce.java - IMS
 * Author:   Kevin Viren 
 * Date:     4/26/2024
 */

public class Produce extends PerishableProduct {
	private String weight;
	private final int WEIGHT_LENGTH = 20;
	
	public Produce() {
		setDepartment("Prod");
		weight = "0000000000";
	}
	
	public Produce(int idNum, String name, int quantity, double price, String expDate, String weight) {
		super(idNum, name, quantity, price, expDate);
		setDepartment("Prod");
		setWeight(weight);
	}
	
	
	// GET/SET METHODS
	public String getWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		int weightLength = weight.length();
		
		if(weightLength < WEIGHT_LENGTH) {
			int diff = WEIGHT_LENGTH - weightLength; 
			for(int i=0; i < diff; ++i) {
				weight += " ";
			}
		}
		if(weightLength > WEIGHT_LENGTH) {
			weight = weight.substring(0, WEIGHT_LENGTH);
		}
		this.weight = weight;
	}
	
	// toString method used to build records
	public String toString() {
		String s = "ID#: "+getIdNumber()+"  ||  DEPARTMENT: "+getDepartment()+"  ||  DESCRIPTION: "+getName()+"  ||  QUANTITY: "+getQuantity()+"  ||  PRICE: $"+getPrice()+"  ||  Exp: "+getExpDate()+"  ||  Weight: "+getWeight();
		int recordLength = getRecordLength();
		
		int sLength = s.length();
		if(sLength < recordLength) {
			int diff = recordLength - sLength; 
			for(int i=0; i < diff; ++i) {
				s += " ";
			}
		}
		if(sLength > recordLength) {
			s = s.substring(0, recordLength);
		}
		return s;
	}
}