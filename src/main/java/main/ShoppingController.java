package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@RequestMapping(path="/shopping/products/{productId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Product> findById(@RequestParam String id){
		return this.productService.findById(id);
	}
	
//	@RequestMapping(path="/people",
//		method = RequestMethod.POST,
//		consumes = MediaType.APPLICATION_JSON_VALUE,
//		produces = MediaType.APPLICATION_JSON_VALUE)
//	public Mono<Person> store (@RequestBody Person newPerson) {
//		return this.people
//			.store(newPerson);
//	}
//	
//	@RequestMapping(path="/people",
//			method = RequestMethod.GET,
//			produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Flux<Person> getAllPeople (){
//		return this.people
//			.getAll();
//	}
//
//	@RequestMapping(path="/people",
//			method = RequestMethod.DELETE)
//	public Mono<Void> deleteAllPeople() {
//		return this.people
//			.deleteAllPeople();
//	}
}

