package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerBuildType;

@Repository(value="designerBuildTypeMapper")
public interface DesignerBuildTypeMapper {
	List<DesignerBuildType> findDesignerBuildType();
}