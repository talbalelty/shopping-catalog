package main;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;

import reactor.core.publisher.Flux;

public interface ProductDao extends ReactiveMongoRepository<ProductEntity, String>{

	public Flux<ProductEntity> findByName(@Param("name") String name, Sort sort);

	public Flux<ProductEntity> findByPriceGreaterThan(@Param("price") float filterValue, Sort by);
	
	public Flux<ProductEntity> findByPriceLessThan(@Param("price") float filterValue, Sort by);
	
	public Flux<ProductEntity> findByCategory(@Param("category") String category, Sort sort);


}
