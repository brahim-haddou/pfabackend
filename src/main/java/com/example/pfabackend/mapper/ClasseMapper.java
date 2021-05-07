package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ClasseRequest;
import com.example.pfabackend.model.Classe;
import com.example.pfabackend.model.Element;
import com.example.pfabackend.model.EmploiDuTemps;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

//    @Mapping( target = "emploiDuTemps", source = "emploiDuTemps")
    @Mapping( target = "element",  source = "element")
    @Mapping( target = "id", source = "classeRequest.id")
    @Mapping( target = "nom" , source = "classeRequest.nom")
    @Mapping( target = "type" , source = "classeRequest.type")
    @Mapping( target = "maxEtudiant" ,source = "classeRequest.maxEtudiant")
    Classe toclasse(ClasseRequest classeRequest, Element element);
}
