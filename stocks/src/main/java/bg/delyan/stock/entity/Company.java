package bg.delyan.stock.entity;

public class Company {
	
	private String name;

	public Company(String companyName) {
		this.name = companyName;
	}

	public String getName() {
		return name;
	}

	public int compareTo(Company company) {
		return company.getName().compareTo(name);
	}
}
