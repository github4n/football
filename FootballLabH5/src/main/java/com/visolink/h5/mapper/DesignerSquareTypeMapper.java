package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerSquareType;

@Repository(value="designerSquareTypeMapper")
public interface DesignerSquareTypeMapper {
	List<DesignerSquareType> findDesignerSquareType();
}