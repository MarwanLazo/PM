package com.example.demo.mapping;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.demo.entity.SuperEntity;
import com.example.demo.model.AbstractModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SuperMappingServiceImpl<EN extends SuperEntity, Model extends AbstractModel>
		implements SuperMappingService<EN, Model> {

	private final ModelMapper mapper;
	private final Class<EN> enClazz;
	private final Class<Model> modelClazz;

	@Override
	public EN mapToEntity(Model model) {
		return mapper.map(model, enClazz);
	}

	@Override
	public Model mapToModel(EN en) {
		return mapper.map(en, modelClazz);
	}

	@Override
	public List<Model> mapToModel(List<EN> entity) {
		return entity.stream().map(this::mapToModel).collect(Collectors.toList());
	}

	@Override
	public List<EN> mapToEntity(List<Model> model) {
		return model.stream().map(this::mapToEntity).collect(Collectors.toList());
	}

}
