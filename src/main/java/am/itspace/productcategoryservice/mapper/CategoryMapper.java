package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.CategoryPutRequestDto;
import am.itspace.productcategoryservice.dto.CategoryRequestDto;
import am.itspace.productcategoryservice.dto.CategoryResponseDto;
import am.itspace.productcategoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category map(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto map(Category category);

    Category map(CategoryPutRequestDto categoryPutRequestDto);

    List<CategoryResponseDto> map(List<Category> categoryList);

    CategoryRequestDto map(Optional<Category> category);

    Category map(Integer value);
}
