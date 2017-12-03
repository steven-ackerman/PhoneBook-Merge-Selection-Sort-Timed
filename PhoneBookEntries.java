
//Begin PhoneBookEntries Class.
public class PhoneBookEntries extends PhoneBook{
	//Class Variables
	private String lastName = "";
	private String firstName = "";
	private String phone = "";
	
	//Constructor
	PhoneBookEntries(String phone, String lastName, String firstName){
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		
	}
	
	//Getters
	
	public String getFirst() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	public String getPhone() {
		return this.phone;
	}
	
	@Override
	public String toString() {
		String entry = new String();
		entry = "Phone: "+this.getPhone()+" Last Name: "+this.getLastName()+" First Name: "+this.getFirst();
		return entry;
	}


}//End Class
