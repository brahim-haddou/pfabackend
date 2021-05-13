package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ClasseRequest;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    @Mapping( target = "element",  source = "element")
    @Mapping( target = "id", source = "classeRequest.id")
    @Mapping( target = "nom" , source = "classeRequest.nom")
    @Mapping( target = "type" , source = "classeRequest.type")
    @Mapping( target = "maxEtudiant" ,source = "classeRequest.maxEtudiant")
    Classe toClasse(ClasseRequest classeRequest, Element element);
}
