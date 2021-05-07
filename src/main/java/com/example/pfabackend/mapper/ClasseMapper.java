package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ClasseRequest;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    @Mapping( target = "element",  source = "element")
    @Mapping( target = "id", source = "request.id")
    @Mapping( target = "nom" , source = "request.nom")
    @Mapping( target = "type" , source = "request.type")
    @Mapping( target = "maxEtudiant" ,source = "request.maxEtudiant")
    Classe toclasse(ClasseRequest request, Element element);
}
