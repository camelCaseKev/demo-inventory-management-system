/**Filename: PerishableProduct.java
 * Author:   Kevin Viren 
 * Date:     4/26/2024
 */

public class PerishableProduct extends Product {
	private String expDate;
	private int EXP_DATE_LENGTH = 10;
	
	public PerishableProduct() {
		expDate = "0000000000";
	}
	
	public PerishableProduct(int idNum, String name, int quantity, double price, String expDate) {
		super(idNum, name, quantity, price);
		setExpDate(expDate);
	}
	
	
	// GET/SET METHODS
	public String getExpDate() {
		return expDate;
	}
	
	public void setExpDate(String expirationDate) {
		int expDateLength = expirationDate.length();
		
		if(expDateLength < EXP_DATE_LENGTH) {
			int diff = EXP_DATE_LENGTH - expDateLength; 
			for(int i=0; i < diff; ++i) {
				expirationDate += " ";
			}
		}
		if(expDateLength > EXP_DATE_LENGTH) {
			expirationDate = expirationDate.substring(0, EXP_DATE_LENGTH);
		}
		expDate = expirationDate;
	}
}