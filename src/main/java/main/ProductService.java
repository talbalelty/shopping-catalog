package main;

import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Product> findById(String id);

}
