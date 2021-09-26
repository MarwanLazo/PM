package com.example.demo.mapping;

import java.util.List;

import com.example.demo.entity.SuperEntity;
import com.example.demo.model.AbstractModel;

public interface SuperMappingService<EN extends SuperEntity, Model extends AbstractModel> {

	EN mapToEntity(Model model);

	Model mapToModel(EN en);

	List<Model> mapToModel(List<EN> entity);

	List<EN> mapToEntity(List<Model> model);

}
