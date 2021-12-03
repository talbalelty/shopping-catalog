package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class ShoppingController {

	private ProductService productService;

	@Autowired	
	public ShoppingController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@RequestMapping(path="/shopping/products",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductBoundary> store(@RequestBody ProductBoundary productBoundary){
		return this.productService.store(productBoundary);
	}
	
	@RequestMapping(path="/shopping/products/{productId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ProductBoundary> findById(@PathVariable("productId") String productId){
		return this.productService.findById(productId);
	}
	
	@RequestMapping(path="/shopping/products",
			method = RequestMethod.GET,
			produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ProductBoundary> findAll(
			// all filters
			@RequestParam(name = "filterType", required = false, defaultValue = "") String filterType,
			@RequestParam(name = "filterValue", required = false, defaultValue = "") String filterValue,
			// default
			@RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(name = "sortOrder", required = false, defaultValue = "true") Boolean asc){
		return this.productService.findAll(filterType, filterValue, sortBy, asc);
	}	

}

