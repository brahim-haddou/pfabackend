package com.example.pfabackend.mapper;

import com.example.pfabackend.dto.ClasseRequest;
import com.example.pfabackend.dto.CreneauRequest;
import com.example.pfabackend.dto.ModuleRequest;
import com.example.pfabackend.model.Creneau;
import com.example.pfabackend.model.Filiere;
import com.example.pfabackend.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreneauMapper {
    @Mapping(target = "filiere", source = "filiere")
    @Mapping(target = "jour", source = "creneauRequest.jour")
    @Mapping(target = "debut", source = "creneauRequest.debut")
    @Mapping(target = "fin", source = "creneauRequest.fin")
    Creneau toCreneau(CreneauRequest creneauRequest, Filiere filiere);
}
