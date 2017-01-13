package com.visolink.h5.mapper;

import org.springframework.stereotype.Repository;

import com.visolink.h5.entity.Calculator;
@Repository(value = "calculatorMapper")  
public interface CalculatorMapper {
	 /**
     * 插入
     */
    void insertCal(Calculator calculator);
}
