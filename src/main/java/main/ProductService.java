package main;

import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<ProductBoundary> findById(String id);

}
