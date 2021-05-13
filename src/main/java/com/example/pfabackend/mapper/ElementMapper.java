package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ElementRequest;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ElementMapper {
    @Mapping( target = "module",  source = "module")
    @Mapping( target = "id", source = "elementRequest.id")
    @Mapping( target = "nom" , source = "elementRequest.nom")
    Element toElement(ElementRequest elementRequest, Module module);
}
