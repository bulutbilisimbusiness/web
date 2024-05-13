package com.bbb.shoppingapp.product.repository.es;

import com.bbb.shoppingapp.product.domain.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ProductEsRepository extends ReactiveElasticsearchRepository<ProductEs,String> {
}
