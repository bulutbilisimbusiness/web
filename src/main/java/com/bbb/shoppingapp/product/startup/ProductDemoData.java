package com.bbb.shoppingapp.product.startup;

import com.bbb.shoppingapp.filestore.service.FileStoreService;
import com.bbb.shoppingapp.product.domain.MoneyTypes;
import com.bbb.shoppingapp.product.model.category.CategoryResponse;
import com.bbb.shoppingapp.product.model.category.CategorySaveRequest;
import com.bbb.shoppingapp.product.model.product.ProductResponse;
import com.bbb.shoppingapp.product.model.product.ProductSaveRequest;
import com.bbb.shoppingapp.product.repository.es.ProductEsRepository;
import com.bbb.shoppingapp.product.service.ProductService;
import com.bbb.shoppingapp.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDemoData {
    private final ProductService productService;
    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;
    private final FileStoreService fileStoreService;

    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {

        Long countOfData = productService.count().block();
        assert countOfData != null;
        if (countOfData.equals(0L)) {

            productEsRepository.deleteAll().block();

            categoryService.save(CategorySaveRequest.builder().name("Elektronik").build());
            CategoryResponse phone = categoryService.save(CategorySaveRequest.builder().name("Cep Telefonu").build());


            IntStream.range(0, 20).forEach(item -> {
                HashMap<MoneyTypes, BigDecimal> price = new HashMap<>() {{
                    put(MoneyTypes.USD, BigDecimal.valueOf((item + 1) * 5));
                    put(MoneyTypes.EUR, BigDecimal.valueOf((item + 1) * 4));
                }};

                String imgUuid = UUID.randomUUID().toString();

                byte[] file = null;
                try {
                    file = Files.readAllBytes(ResourceUtils.getFile("classpath:product-images/phone.jpeg").toPath());
                } catch (IOException e) {
                    log.error("File read error : ", e);
                }
                fileStoreService.saveImage(imgUuid, new ByteArrayInputStream(file));


                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(randomUUID().toString())
                                .id(randomUUID().toString())
                                .description("Product Description " + item)
                                .price(price)
                                .categoryId(phone.getId())
                                .name("Product Name " + item)
                                .features("<li>Black Color</li> <li>Aluminum Case</li> <li>2 Years Warranty</li> <li>5 Inch (35x55mm)</li>")
                                .images(List.of(imgUuid))
                                .build());


            });
        }
    }
}