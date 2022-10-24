package am.itspace.productcategoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private int id;
    private String title;
    private int count;
    private double price;
    @JsonProperty(namespace = "categoryResponseDto")
    private CategoryResponseDto categoryResponseDto;
}
