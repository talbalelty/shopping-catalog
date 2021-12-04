package main;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import reactor.core.publisher.Flux;

public interface ProductDao extends ReactiveMongoRepository<ProductEntity, String>{

	public Flux<ProductEntity> findByFirstname(@Param("name") String name,Sort sort);

	public Flux<ProductEntity> findByPriceGreaterThan(@Param("price") float filterValue, Sort by);
	
}
