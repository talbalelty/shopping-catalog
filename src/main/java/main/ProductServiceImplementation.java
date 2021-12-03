package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImplementation implements ProductService {
	private ProductDao productDao;
	
	@Autowired
	public ProductServiceImplementation(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public Mono<ProductBoundary> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ProductBoundary> store(ProductBoundary productBoundary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<ProductBoundary> findAll(String filterType, String filterValue, String sortBy, Boolean asc) {
		// TODO Auto-generated method stub
		return null;
	}



}
