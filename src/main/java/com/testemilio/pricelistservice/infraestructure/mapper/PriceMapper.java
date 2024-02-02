package com.testemilio.pricelistservice.infraestructure.mapper;

import com.testemilio.pricelistservice.domain.model.Price;
import com.testemilio.pricelistservice.infraestructure.dto.PriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "price.id.brandId", target = "brandId")
    @Mapping(source = "price.id.startDate", target = "startDate")
    @Mapping(source = "price.id.endDate", target = "endDate")
    @Mapping(source = "price.id.priceList", target = "priceList")
    @Mapping(source = "price.id.productId", target = "productId")
    @Mapping(source = "price.finalPrice", target = "price")
    PriceResponseDTO priceToPriceResponseDTO(Price price);

}
