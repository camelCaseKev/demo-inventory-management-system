 /**Filename: MainWindow.java - IMS
 *  Author:   Kevin Viren 
 *  Date:     4/25/2024
 *  
 *  Resources: I watched a 15-part YouTube tutorial to familiarize myself with Swing.
 *  		   The playlist is listed here: https://www.youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.nio.channels.FileChannel;
import javax.swing.*;


public class MainWindow {
	private Path file = Paths.get("src\\records.txt"); // Relative path works on both my systems. If your working directory is different, you may need to change the path   =/
	
	private JFrame frame;
	private JPanel northPanel, westPanel, southPanel, southPanel1, southPanel2, southPanel3, southPanel4;
	private JPanel southPanel5, southPanel6, southPanel7, southPanel8;
	private static JTextArea textArea;
	private ScrollPane scrollPane;
	private JLabel categoryLabel, searchLabel, addLabel, deptLabel1, descLabel1, priceLabel1;
	private JLabel qtyLabel1, expLabel1, weightLabel1, animalSrcLabel1, deleteLabel, idLabel1;
	private JLabel editLabel, deptLabel2, idLabel2, descLabel2, priceLabel2, qtyLabel2, expLabel2, weightLabel2, animalSrcLabel2;
	private JButton produceBtn, meatBtn, miscBtn, allBtn, searchBtn, addBtn, clearBtn1, deleteBtn, clearBtn2, editBtn, clearBtn3;
	private JTextField searchField, deptField1, descField1, priceField1;
	private JTextField qtyField1, expField1, weightField1, animalSrcField1, idField1;
	private JTextField deptField2, idField2, descField2, priceField2, qtyField2, expField2, weightField2, animalSrcField2;
	
	
	public MainWindow() {
		
		
		// ===========================|| WINDOW SETUP ||=============================================
		
		
		frame = new JFrame();
		frame.setTitle("INVENTORY MANAGEMENT SYSTEM");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1250, 700);
		frame.setLocationRelativeTo(null);
		
		
		// ========= CENTER REGION =================
		
		// Read the records and send them to the textArea
		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setText(readRecords(file));
		textArea.setDisabledTextColor(Color.BLACK);
		
		scrollPane = new ScrollPane();
		scrollPane.add(textArea);
		
		
		
		// ========= NORTH REGION ==================
		
		// Create panel and components in NORTH region
		searchLabel = new JLabel("Search by Description");
		searchLabel.setFont(new Font("Arial", Font.BOLD, 18));
		searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		searchField = new JTextField();
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!searchField.getText().equals("")) {
					textArea.setText(searchRecords(file, searchField.getText()));
					searchField.setText("");
				}
			}
		});
		
		northPanel = new JPanel(new GridLayout(0, 4, 10, 0));
		northPanel.setBackground(Color.GRAY);
		
		northPanel.add(searchLabel);
		northPanel.add(searchField);
		northPanel.add(searchBtn);
		
		
		
		// ========= WEST REGION ==================
		
		// Create panel and components in WEST region
		categoryLabel = new JLabel("Filters");
		categoryLabel.setForeground(Color.WHITE);
		categoryLabel.setFont(new Font("Arial", Font.BOLD, 22));
		
		produceBtn = new JButton("Produce");
		produceBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(readRecords(file, "Prod"));
			}
		});
		
		meatBtn = new JButton("Meat");
		meatBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(readRecords(file, "Meat"));
			}
		});
		
		miscBtn = new JButton("Misc");
		miscBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(readRecords(file, "Misc"));
			}
		});
		
		allBtn = new JButton("ALL");
		allBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.setText(readRecords(file));
			}
		});
		
		westPanel = new JPanel(new GridLayout(6, 1, 0, 15));
		westPanel.setBackground(Color.DARK_GRAY);
		
		westPanel.add(categoryLabel);
		westPanel.add(produceBtn);
		westPanel.add(meatBtn);
		westPanel.add(miscBtn);
		westPanel.add(allBtn);
		
		
		
		// ========= SOUTH REGION ==================
		
		// Create panel in SOUTH region and components in nested panels
		addLabel = new JLabel("Add Product");
		addLabel.setFont(new Font("Arial", Font.BOLD, 20));
		southPanel1 = new JPanel();
		southPanel1.setBackground(Color.GRAY);
		southPanel1.add(addLabel);
		deptLabel1 = new JLabel("Department");
		deptLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		deptField1 = new JTextField(4);
		descLabel1 = new JLabel("Description");
		descLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		descField1 = new JTextField(20);
		priceLabel1 = new JLabel("Price $");
		priceLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		priceField1 = new JTextField(6);
		qtyLabel1 = new JLabel("Quantity ");
		qtyLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		qtyField1 = new JTextField(4);
		expLabel1 = new JLabel("Exp Date");
		expLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		expField1 = new JTextField(10);
		weightLabel1 = new JLabel("Weight (for produce and meat - optional)");
		weightLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		weightField1 = new JTextField(10);
		animalSrcLabel1 = new JLabel("Animal Source (for meat - optional)");
		animalSrcLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		animalSrcField1 = new JTextField(10);
		addBtn = new JButton("ADD");
		clearBtn1 = new JButton("clear");
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String desc = descField1.getText();
				String qty = qtyField1.getText();
				String price = priceField1.getText();
				String expDate = expField1.getText();
				String weight = weightField1.getText();
				String animalSrc = animalSrcField1.getText();
				
				if(deptField1.getText().equalsIgnoreCase("misc")) {
					if(!(desc.equals("") || qty.equals("") || price.equals(""))) {
						addProductRecord(file, desc, qty, price);
					} else {
						textArea.setText("ERROR: Miscellaneous products require a description, quantity, and price. Make sure to supply all these.");
					}
					
				} else if(deptField1.getText().equalsIgnoreCase("prod")) {
					if(!(desc.equals("") || qty.equals("") || price.equals(""))) {
						addProduceRecord(file, desc, qty, price, expDate, weight);
					} else {
						textArea.setText("ERROR: Produce products require a description, quantity, price, and expiration date. Make sure to supply all these.");
					}
					
				} else if(deptField1.getText().equalsIgnoreCase("meat")) {
					if(!(desc.equals("") || qty.equals("") || price.equals(""))) {
						addMeatRecord(file, desc, qty, price, expDate, animalSrc);
					} else {
						textArea.setText("ERROR: Meat products require a description, quantity, price, and expiration date. Make sure to supply all these.");
					}
					
				} else {
					textArea.setText("ERROR: Invalid department. Please choose between \"Prod\" for produce, \"Meat\" for meat, and \"Misc\" for miscellaneous.");
				}
			}
		});
		
		clearBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deptField1.setText("");
				descField1.setText("");
				priceField1.setText("");
				qtyField1.setText("");
				expField1.setText("");
				weightField1.setText("");
				animalSrcField1.setText("");
			}
		});
		
		southPanel2 = new JPanel();
		southPanel2.setBackground(Color.GRAY);
		southPanel2.add(deptLabel1);
		southPanel2.add(deptField1);
		southPanel2.add(descLabel1);
		southPanel2.add(descField1);
		southPanel2.add(priceLabel1);
		southPanel2.add(priceField1);
		southPanel3 = new JPanel();
		southPanel3.setBackground(Color.GRAY);
		southPanel3.add(qtyLabel1);
		southPanel3.add(qtyField1);
		southPanel3.add(expLabel1);
		southPanel3.add(expField1);
		southPanel3.add(weightLabel1);
		southPanel3.add(weightField1);
		southPanel3.add(animalSrcLabel1);
		southPanel3.add(animalSrcField1);
		southPanel3.add(addBtn);
		southPanel3.add(clearBtn1);
		
		
		deleteLabel = new JLabel("Delete Product");
		deleteLabel.setFont(new Font("Arial", Font.BOLD, 20));
		deleteLabel.setForeground(Color.LIGHT_GRAY);
		southPanel4 = new JPanel();
		southPanel4.setBackground(Color.DARK_GRAY);
		southPanel4.add(deleteLabel);
		idLabel1 = new JLabel("ID#");
		idLabel1.setFont(new Font("Arial", Font.PLAIN, 15));
		idLabel1.setForeground(Color.WHITE);
		idField1 = new JTextField(3);
		
		deleteBtn = new JButton("DELETE");
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteRecord(file, idField1.getText());
				idField1.setText("");
			}
		});
		
		clearBtn2 = new JButton("clear");
		clearBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idField1.setText("");
			}
		});
		
		
		southPanel5 = new JPanel();
		southPanel5.setBackground(Color.DARK_GRAY);
		southPanel5.add(idLabel1);
		southPanel5.add(idField1);
		southPanel5.add(deleteBtn);
		southPanel5.add(clearBtn2);
		
		
		editLabel = new JLabel("Edit Product");
		editLabel.setFont(new Font("Arial", Font.BOLD, 20));
		southPanel6 = new JPanel();
		southPanel6.setBackground(Color.LIGHT_GRAY);
		southPanel6.add(editLabel);
		idLabel2 = new JLabel("ID# of product to be edited");
		idLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		idField2 = new JTextField(3);
		deptLabel2 = new JLabel("Department");
		deptLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		deptField2 = new JTextField(4);
		descLabel2 = new JLabel("Description");
		descLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		descField2 = new JTextField(20);
		priceLabel2 = new JLabel("Price $");
		priceLabel2.setFont(new Font("Arial", Font.PLAIN, 15));;
		priceField2 = new JTextField(6);
		southPanel7 = new JPanel();
		southPanel7.setBackground(Color.LIGHT_GRAY);
		
		southPanel7.add(idLabel2);
		southPanel7.add(idField2);
		southPanel7.add(deptLabel2);
		southPanel7.add(deptField2);
		southPanel7.add(descLabel2);
		southPanel7.add(descField2);
		southPanel7.add(priceLabel2);
		southPanel7.add(priceField2);
		
		
		qtyLabel2 = new JLabel("Quantity ");
		qtyLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		qtyField2 = new JTextField(4);
		expLabel2 = new JLabel("Exp Date");
		expLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		expField2 = new JTextField(10);
		weightLabel2 = new JLabel("Weight (for produce and meat - optional)");
		weightLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		weightField2 = new JTextField(10);
		animalSrcLabel2 = new JLabel("Animal Source (for meat - optional)");
		animalSrcLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
		animalSrcField2 = new JTextField(10);
		
		editBtn = new JButton("EDIT");
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editRecord(file, idField2.getText(), deptField2.getText(), descField2.getText(), qtyField2.getText(), priceField2.getText(), expField2.getText(), weightField2.getText(), animalSrcField2.getText());
			}
		});
		
		clearBtn3 = new JButton("clear");
		clearBtn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idField2.setText("");
				deptField2.setText("");
				descField2.setText("");
				priceField2.setText("");
				qtyField2.setText("");
				expField2.setText("");
				weightField2.setText("");
				animalSrcField2.setText("");
			}
		});
		
		southPanel8 = new JPanel();
		southPanel8.setBackground(Color.LIGHT_GRAY);
		southPanel8.add(qtyLabel2);
		southPanel8.add(qtyField2);
		southPanel8.add(expLabel2);
		southPanel8.add(expField2);
		southPanel8.add(weightLabel2);
		southPanel8.add(weightField2);
		southPanel8.add(animalSrcLabel2);
		southPanel8.add(animalSrcField2);
		southPanel8.add(editBtn);
		southPanel8.add(clearBtn3);
		
		
		southPanel = new JPanel(new GridLayout(8, 0, 15, 0));
		southPanel.setBackground(Color.GRAY);
		southPanel.add(southPanel1);
		southPanel.add(southPanel2);
		southPanel.add(southPanel3);
		southPanel.add(southPanel4);
		southPanel.add(southPanel5);
		southPanel.add(southPanel6);
		southPanel.add(southPanel7);
		southPanel.add(southPanel8);
		
		
		// ========= ADD REGIONAL CONTAINERS TO THE FRAME ==================
		frame.add(northPanel, BorderLayout.NORTH);
		frame.add(westPanel, BorderLayout.WEST);
		frame.add(southPanel, BorderLayout.SOUTH);
		frame.add(scrollPane);
	}
	
	
	
	
	// ===========================|| METHODS ||=============================================
	
	
	// Makes the frame visible
	public void show() {
		frame.setVisible(true);
	}
	
	
	// Reads all records and sends them to the text area
	public static String readRecords(Path file) { 
		String records = "";
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			line = reader.readLine();
			while(line != null) {
				if(!(line.startsWith("ID#: 000"))) { // Ensure # of 0s matches ID_NUM_LENGTH from Product.java)
					records += line+"\n\n";
				}
				line = reader.readLine();
			}
			reader.close();
			return records;
		}
		catch(IOException e) {
			return "ERROR READING RECORDS: " + e;
		}
		catch(Exception e) {
			return "ERROR READING RECORDS: " + e;
		}
	}
	
	
	// Reads all records and returns only the ones of type department
	public String readRecords(Path file, String department) { 
		String records = "";
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			int endIndex = 26 + Product.getDepartmentNameLength();
			line = reader.readLine();
			while(line != null) {
				if(!line.startsWith("ID#: 000") && line.substring(26, endIndex).equals(department)) { // Return only records with a dept field matching the entered department and a non-empty Id
					records += line+"\n\n";
				}
				line = reader.readLine();
			}
			reader.close();
			return records;
		}
		catch(IOException e) {
			return "ERROR READING RECORDS: " + e;
		}
		catch(Exception e) {
			return "ERROR READING RECORDS: " + e;
		}
	}
	
	
	// Searches all records for those with searchQuery in the description, then returns those
	public static String searchRecords(Path file, String searchQuery) { 
		String records = "";
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			int endIndex = 49 + Product.getNameLength();
			line = reader.readLine();
			while(line != null) {
				if(line.substring(49, endIndex).toLowerCase().contains(searchQuery.toLowerCase())) {
					records += line+"\n\n";
				}
				line = reader.readLine();
			}
			reader.close();
			return records;
		}
		catch(IOException e) {
			return "ERROR READING RECORDS: " + e;
		}
		catch(Exception e) {
			return "ERROR READING RECORDS: " + e;
		}
	}
	
	
	// Adds a Product record to the first "empty" line in records.txt
	public static void addProductRecord(Path file, String desc, String quantity, String price) {
		int lineNum = 0;
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			final int RECSIZE = Product.getRecordLength() + System.getProperty("line.separator").length();
			line = reader.readLine();
			++lineNum;
			while(line != null) { // Ensure # of 0s matches ID_NUM_LENGTH from Product.java)
				if(line.startsWith("ID#: 000")) {
					Product record = new Product(lineNum, desc, Integer.parseInt(quantity), Double.parseDouble(price));
					
					byte[] data = (record.toString()+System.getProperty("line.separator")).getBytes();
					ByteBuffer buffer = ByteBuffer.wrap(data);
					FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
					fc.position((lineNum-1)*RECSIZE);
					fc.write(buffer);
					fc.close();
					textArea.setText(searchRecords(file, desc)); // DISPLAY THE NEW PRODUCT
					break;
				}
				line = reader.readLine();
				++lineNum;
			}
			reader.close();
		}
		catch(IOException e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
		catch(Exception e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
	}
	
	
	// Adds a Produce record to the first "empty" line in records.txt
	public static void addProduceRecord(Path file, String desc, String quantity, String price, String expDate, String weight) {
		int lineNum = 0;
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			final int RECSIZE = Product.getRecordLength() + System.getProperty("line.separator").length();
			line = reader.readLine();
			++lineNum;
			while(line != null) { 
				if(line.startsWith("ID#: 000")) { // Ensure # of 0s matches ID_NUM_LENGTH from Product.java)
					Produce record = new Produce(lineNum, desc, Integer.parseInt(quantity), Double.parseDouble(price), expDate, weight);
					
					byte[] data = (record.toString()+System.getProperty("line.separator")).getBytes();
					ByteBuffer buffer = ByteBuffer.wrap(data);
					FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
					fc.position((lineNum-1)*RECSIZE);
					fc.write(buffer);
					fc.close();
					textArea.setText(searchRecords(file, desc)); // DISPLAY THE NEW PRODUCT
					break;
				}
				line = reader.readLine();
				++lineNum;
			}
			reader.close();
		}
		catch(IOException e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
		catch(Exception e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
	}
	
	
	// Adds a Meat record to the first "empty" line in records.txt
	public static void addMeatRecord(Path file, String desc, String quantity, String price, String expDate, String animalSrc) {
		int lineNum = 0;
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			final int RECSIZE = Product.getRecordLength() + System.getProperty("line.separator").length();
			line = reader.readLine();
			++lineNum;
			while(line != null) { 
				if(line.startsWith("ID#: 000")) { // Ensure # of 0s matches ID_NUM_LENGTH from Product.java
					Meat record = new Meat(lineNum, desc, Integer.parseInt(quantity), Double.parseDouble(price), expDate, animalSrc);
					
					byte[] data = (record.toString()+System.getProperty("line.separator")).getBytes();
					ByteBuffer buffer = ByteBuffer.wrap(data);
					FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
					fc.position((lineNum-1)*RECSIZE);
					fc.write(buffer);
					fc.close();
					textArea.setText(searchRecords(file, desc)); // DISPLAY THE NEW PRODUCT
					break;
				}
				line = reader.readLine();
				++lineNum;
			}
			reader.close();
		}
		catch(IOException e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
		catch(Exception e) {
			textArea.setText("ERROR ADDING RECORD: " + e);
		}
	}
	
	
	// Deletes a record by creating a blank record in its stead
	public static void deleteRecord(Path file, String idNum) {
		
		try {
			final int RECSIZE = Product.getRecordLength() + System.getProperty("line.separator").length();
			Product record = new Product();
			
			byte[] data = (record.toString()+System.getProperty("line.separator")).getBytes();
			ByteBuffer buffer = ByteBuffer.wrap(data);
			FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
			fc.position((Integer.parseInt(idNum)-1)*RECSIZE);
			fc.write(buffer);
			fc.close();
			textArea.setText(readRecords(file));
		}
		catch(IOException e) {
			textArea.setText("ERROR DELETE RECORD: " + e);
		}
		catch(Exception e) {
			textArea.setText("ERROR DELETE RECORD: " + e);
		}
	}
	
	
	// Replaces a record of a specified Id number with a new product created with user arguments. This results in the illusion of an edited record. 
	public static void editRecord(Path file, String idNum, String dept, String desc, String quantity, String price, String expDate, String weight, String animalSrc) {
		
		int lineNum = 0;
		String line;
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			final int RECSIZE = Product.getRecordLength() + System.getProperty("line.separator").length();
			line = reader.readLine();
			++lineNum;
			while(line != null) {
				if(line.startsWith("ID#: "+formatIdNumber(Integer.parseInt(idNum)))) {// Ensure # of 0s matches ID_NUM_LENGTH from Product.java
					
					Product newProduct = new Product(Integer.parseInt(idNum), desc, Integer.parseInt(quantity), Double.parseDouble(price));
					
					if(dept.equalsIgnoreCase("misc")) {
						if(!(desc.equals("") || quantity.equals("") || price.equals(""))) {
							newProduct = new Product(Integer.parseInt(idNum), desc, Integer.parseInt(quantity), Double.parseDouble(price));
							textArea.setText(searchRecords(file, desc));
						} else {
							textArea.setText("ERROR: Miscellaneous products require a description, price, and quantity. Make sure to supply all these.");
						}
						
					} else if(dept.equalsIgnoreCase("prod")) {
						if(!(desc.equals("") || quantity.equals("") || price.equals(""))) {
							newProduct = new Produce(Integer.parseInt(idNum), desc, Integer.parseInt(quantity), Double.parseDouble(price), expDate, weight);
							textArea.setText(searchRecords(file, desc));
						} else {
							textArea.setText("ERROR: Produce products require a description, price, and quantity. Make sure to supply all these.");
						}
						
					} else if(dept.equalsIgnoreCase("meat")) {
						if(!(desc.equals("") || quantity.equals("") || price.equals(""))) {
							newProduct = new Meat(Integer.parseInt(idNum), desc, Integer.parseInt(quantity), Double.parseDouble(price), expDate, animalSrc);
							textArea.setText(searchRecords(file, desc));
						} else {
							textArea.setText("ERROR: Meat products require a description, price, and quantity. Make sure to supply all these.");
						}
						
					} else {
						textArea.setText("ERROR: Invalid department. Please choose between \"Prod\" for produce, \"Meat\" for meat, and \"Misc\" for miscellaneous.");
						break;
					}
					
					byte[] data = (newProduct.toString()+System.getProperty("line.separator")).getBytes();
					ByteBuffer buffer = ByteBuffer.wrap(data);
					FileChannel fc = (FileChannel)Files.newByteChannel(file, READ, WRITE);
					fc.position((lineNum-1)*RECSIZE);
					fc.write(buffer);
					fc.close();
					textArea.setText(searchRecords(file, desc)); // Displays the new product in place of the old one as if it has been modified
					break;
				}
				line = reader.readLine();
				++lineNum;
			}
			reader.close();
		}
		catch(NumberFormatException e) {
			textArea.setText("ERROR: Make sure to provide an ID#, department, description, price, and quantity.");
		}
		catch(IOException e) {
			textArea.setText("ERROR EDITING RECORD: " + e);
			
		}
		catch(Exception e) {
			textArea.setText("ERROR EDITING RECORD: " + e);
		}
	}
	
	
	// Alters a number to an Id number format and returns it as a string (used in the editRecord method)
	public static String formatIdNumber(int num) {
		String strNum = num+"";
		int numLength = strNum.length();
		int prefixNums; //Number of leading zeros attached to Id numbers when the argument is less than the ID_NUM_LENGTH
		String prefixZeros = "";
		
		if(numLength < Product.getIdNumberLength()) {
			prefixNums = Product.getIdNumberLength() - numLength;
			
			for(int i = 0; i < prefixNums; ++i) {
				prefixZeros += "0"; 
			}	
		}
		return prefixZeros + num;
	}
}