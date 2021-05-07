package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ModuleRequest;
import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleMapper {


    @Mapping(target = "elements", source = "")
    @Mapping(target = "filiere", source = "filiere")
    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "nom", source = "request.nom")
    Module tomodule(ModuleRequest request, Filiere filiere);
}
