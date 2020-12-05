package bg.delyan.stock.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bg.delyan.stock.entity.Company;
import bg.delyan.stock.service.CompanyStockService;

@RestController
@RequestMapping("/stocks")
public class StocksController {

	@Autowired
	private CompanyStockService companyStockService;

	@PutMapping("/{companyName}/value/{stockValue}")
	@ResponseStatus(HttpStatus.CREATED)
	public void put(@PathVariable(name  = "companyName", required = true ) String companyName, 
					@PathVariable(name  ="stockValue", required = true ) Object stockValue) {
		Company company = new Company(companyName);
		companyStockService.put(company, stockValue);
	}

	@GetMapping("/{companyName}/at-time/{atTime}")
	@ResponseStatus(HttpStatus.OK)
	public Object get(@PathVariable(name = "companyName", required = true) String companyName,
			@PathVariable(name = "atTime", required = true) String stringAtTime) throws NotFoundException {
		LocalDateTime atTime = LocalDateTime.parse(stringAtTime);
		Object object = companyStockService.get(companyName, atTime);
		return object;
	}

}
