package bg.delyan.stock.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import bg.delyan.stock.comparator.CompanyTimestampDataComparator;
import bg.delyan.stock.entity.Company;
import bg.delyan.stock.entity.CompanyTimestampData;

@Service
public class CompanyStockService {
	private TreeMap<CompanyTimestampData, Object> companiesStockValues = new TreeMap<>(new CompanyTimestampDataComparator());

	public void put(Company company, Object stockValue) {
		CompanyTimestampData o = new CompanyTimestampData(company, LocalDateTime.now());
		companiesStockValues.put(o, stockValue);
	}
	

	public Object get(String company, LocalDateTime time) throws NotFoundException {
		Object found = null;
		Set<CompanyTimestampData> keyes = companiesStockValues.keySet();
		for(CompanyTimestampData key : keyes) {
			if(key.getCompany().getName().equals(company) && key.getCreationDate().isBefore(time)) {
				found = companiesStockValues.get(key);
			}
		};
		
		if(found == null) {
			throw new NotFoundException();
		}
		return found;
	}
}
