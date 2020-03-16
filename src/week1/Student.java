package week1;

public class Student {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int noOfCredits;
	private double mean;
	
	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String emailAddress, int noOfCredits, double mean) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.noOfCredits = noOfCredits;
		this.mean = mean;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getNoOfCredits() {
		return noOfCredits;
	}

	public void setNoOfCredits(int noOfCredits) {
		this.noOfCredits = noOfCredits;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.emailAddress 
									+ "," + this.getNoOfCredits() + "," + this.getMean() + "\n";
	}
	
	
	

}
