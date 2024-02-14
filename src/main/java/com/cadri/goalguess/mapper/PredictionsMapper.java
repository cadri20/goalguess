package com.cadri.goalguess.mapper;

import com.cadri.goalguess.dto.PredictionDTO;
import com.cadri.goalguess.model.Prediction;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PredictionsMapper extends Converter<List<Prediction>, List<PredictionDTO>> {
    @Override
    List<PredictionDTO> convert(List<Prediction> source);
}
