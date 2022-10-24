package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.ProductPutRequestDto;
import am.itspace.productcategoryservice.dto.ProductRequestDto;
import am.itspace.productcategoryservice.dto.ProductResponseDto;
import am.itspace.productcategoryservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category", source = "categoryRequestDto")
    Product map(ProductRequestDto productRequestDto);

    @Mapping(target = "category", source = "categoryPutRequestDto")
    Product map(ProductPutRequestDto productPutRequestDto);

    @Mapping(target = "categoryResponseDto", source = "category")
    ProductResponseDto map(Product product);

    List<ProductResponseDto> map(List<Product> productList);
}
