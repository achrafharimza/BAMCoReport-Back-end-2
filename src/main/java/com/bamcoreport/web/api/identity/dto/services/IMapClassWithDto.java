package com.bamcoreport.web.api.identity.dto.services;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.entities.User;

import java.util.Collection;
import java.util.List;
import org.modelmapper.*;


public interface IMapClassWithDto<E,D> {

    D convertToDto(E entity, Class<D> dtoClass);

    E convertToEntity(D dto, Class<E> entityClass);

    List<D> convertListToListDto(Collection<E> entityList, Class<D> outCLass);

    List<E> convertListToListEntity(Collection<D> dtoList, Class<E> outCLass);
}
