package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerLayoutType;

@Repository(value="designerLayoutTypeMapper")
public interface DesignerLayoutTypeMapper {
	List<DesignerLayoutType> findDesignerLayoutType();
}