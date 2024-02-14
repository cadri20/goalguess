package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.PredictionDTO;
import com.cadri.goalguess.model.Prediction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper(componentModel = "spring")
public interface PredictionMapper extends Converter<Prediction, PredictionDTO> {
    @Override
    PredictionDTO convert(Prediction prediction);

}
