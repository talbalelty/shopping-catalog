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
	public Mono<ProductBoundary> store(ProductBoundary productBoundary) {
		return Mono.just(productBoundary)
				.flatMap(boundary -> {
					this.productDao.existsById(boundary.getId()).map(bool -> {
						if (bool)
							System.out.println("Product exists!");
						else
							System.out.println("Product not exists!");

						return bool;
					});
					if (boundary.getId() == null) {
						return Mono.error(
								() -> new ProductAlreadyExistsException("could not create product with id: " + boundary.getId()));
					} else {
						return Mono.just(boundary);
					}
				}) // Mono<ProductBoundary>
				.map(this::toEntity) // Mono<ProductEntity>
				.flatMap(entity -> this.productDao.save(entity)) // Mono<ProductEntity>
				.map(this::toBoundary) // Mono<ProductBoundary>
				.log(); // Mono<ProductBoundary>
	}

	@Override
	public Mono<ProductBoundary> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<ProductBoundary> findAll(String filterType, String filterValue, String sortBy, Boolean asc) {
		// TODO Auto-generated method stub
		return null;
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
