package main;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<ProductBoundary> store(ProductBoundary productBoundary);

	Mono<ProductBoundary> findById(String id);

	Flux<ProductBoundary> findAll(String filterType, String filterValue, String sortBy, Boolean asc);

	Mono<Void> deleteAllPeople();

}
