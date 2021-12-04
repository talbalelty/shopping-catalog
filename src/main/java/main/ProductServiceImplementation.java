package main;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

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
		return Mono.just(productBoundary)
				.flatMap(boundary->{
					if(boundary.getId() == null || this.productDao.existsById(boundary.getId()).block()) {
						return Mono.error(() -> new ProductAlreadyExistsException("could not create product with id: "+ boundary.getId()));
					} else {						
						return Mono.just(boundary);
					}
				}) // Mono<ProductBoundary>
				.map(this::toEntity) // Mono<ProductEntity>
				.flatMap(entity->this.productDao
						.save(entity)) // Mono<ProductEntity>
				.map(this::toBoundary) // Mono<ProductBoundary>
				.log(); // Mono<ProductBoundary>
	}

	@Override
	public Flux<ProductBoundary> findAll(String filterType, String filterValue, String sortBy, Boolean asc) {
		Direction sortDirection = asc?Direction.ASC:Direction.DESC;
	
		switch (filterType) {
		case "byName":
			return this.productDao
					.findByFirstname(filterValue,Sort.by(sortDirection, sortBy))
					.map(this::toBoundary)
					.log();			
		case "byMinPrice":
			return this.productDao
					.findByPriceGreaterThan(Float.parseFloat(filterValue),Sort.by(sortDirection,sortBy))
					.map(this::toBoundary)
					.log();
		case "byMaxPrice":
			return this.productDao
					.findByPriceLessThan(Float.parseFloat(filterValue),Sort.by(sortDirection,sortBy))
					.map(this::toBoundary)
					.log();
		case "byCategoryName":
			return this.productDao
					.findByCategoryName(filterValue,Sort.by(sortDirection,sortBy))
					.map(this::toBoundary)
					.log();
		default:
			return this.productDao
					.findAll() 
					.map(this::toBoundary) 
					.log();
		}
		
	}
	
	@Override
	public Mono<Void> deleteAllProducts() {
		return this.productDao
				.deleteAll()
				.log();
	}
	
	private ProductEntity toEntity(ProductBoundary productBoundary) {
		ProductEntity rv = new ProductEntity();
		rv.setId(productBoundary.getId());
		rv.setName(productBoundary.getName());
		rv.setPrice(productBoundary.getPrice());
		rv.setImage(productBoundary.getImage());
		rv.setProductDetails(productBoundary.getProductDetails());
		rv.setCategory(productBoundary.getCategory());
		return rv;
	}
	
	private ProductBoundary toBoundary(ProductEntity productEntity) {
		ProductBoundary rv = new ProductBoundary();
		rv.setId(productEntity.getId());
		rv.setName(productEntity.getName());
		rv.setPrice(productEntity.getPrice());
		rv.setImage(productEntity.getImage());
		rv.setProductDetails(productEntity.getProductDetails());
		rv.setCategory(productEntity.getCategory());
		return rv;
	}



}
