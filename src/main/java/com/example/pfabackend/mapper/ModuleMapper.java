package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ModuleRequest;
import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    @Mapping(target = "filiere", source = "filiere")
    @Mapping(target = "id", source = "moduleRequest.id")
    @Mapping(target = "nom", source = "moduleRequest.nom")
    Module toModule(ModuleRequest moduleRequest, Filiere filiere);
}
