/**Filename: Meat.java - IMS
 * Author:   Kevin Viren 
 * Date:     4/26/2024
 */

public class Meat extends PerishableProduct {
	private String animalSrc; //Could be chicken or beef, etc.
	private final int ANIMAL_SRC_LENGTH = 30;
	
	public Meat() {
		setDepartment("Meat");
		setAnimalSrc("00000000000000000000");
	}
	
	public Meat(int idNum, String name, int quantity, double price, String expDate, String animal) {
		super(idNum, name, quantity, price, expDate);
		setDepartment("Meat");
		setAnimalSrc(animal);
	}
	
	
	// GET/SET METHODS
	public String getAnimalSrc() {
		return animalSrc;
	}
	
	public void setAnimalSrc(String animal) {
		int animalLength = animal.length();
		
		if(animalLength < ANIMAL_SRC_LENGTH) {
			int diff = ANIMAL_SRC_LENGTH - animalLength; 
			for(int i=0; i < diff; ++i) {
				animal += " ";
			}
		}
		if(animalLength > ANIMAL_SRC_LENGTH) {
			animal = animal.substring(0, ANIMAL_SRC_LENGTH);
		}
		animalSrc = animal;
	}
	
	// toString method used to build records
	public String toString() {
		String s = "ID#: "+getIdNumber()+"  ||  DEPARTMENT: "+getDepartment()+"  ||  DESCRIPTION: "+getName()+"  ||  QUANTITY: "+getQuantity()+"  ||  PRICE: $"+getPrice()+"  ||  Exp: "+getExpDate()+"  ||  Animal Source: "+getAnimalSrc();
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