package bg.delyan.stock.comparator;

import java.util.Comparator;

import bg.delyan.stock.entity.CompanyTimestampData;

public class CompanyTimestampDataComparator implements Comparator<CompanyTimestampData> {

	@Override
	public int compare(CompanyTimestampData arg0, CompanyTimestampData arg1) {
		if(arg0.getCompany().getName().equals(arg1.getCompany().getName())
		   && arg0.getCreationDate().equals(arg1.getCreationDate()) ) {
			return 0;
		} 
		if(arg0.getCompany().getName().equals(arg1.getCompany().getName())) {
			return arg0.getCreationDate().compareTo(arg1.getCreationDate());
		} else {
			return arg0.getCompany().compareTo(arg1.getCompany());
		}
	}

}
