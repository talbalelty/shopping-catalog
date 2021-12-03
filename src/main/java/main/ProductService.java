package main;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<ProductBoundary> findById(String id);

	Mono<ProductBoundary> store(ProductBoundary productBoundary);

	Flux<ProductBoundary> findAll(String filterType, String filterValue, String sortBy, Boolean asc);

}
