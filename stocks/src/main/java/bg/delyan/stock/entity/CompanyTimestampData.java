package bg.delyan.stock.entity;

import java.time.LocalDateTime;

public class CompanyTimestampData {
	
	private Company company;
	private LocalDateTime creationDate;

	public CompanyTimestampData(Company company, LocalDateTime creationDate) {
		this.company = company;
		this.creationDate = creationDate;
	}

	public Company getCompany() {
		return company;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
