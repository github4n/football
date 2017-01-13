package com.visolink.h5.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.DesignerStyleType;

@Repository(value="designerStyleTypeMapper")
public interface DesignerStyleTypeMapper {
    /**
     * 设计师风格查询
     * @return
     */
	List<DesignerStyleType> findDesignerStyleType();
}