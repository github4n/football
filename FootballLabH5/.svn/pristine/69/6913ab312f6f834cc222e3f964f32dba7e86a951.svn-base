package com.visolink.h5.service.homedecoration;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.visolink.h5.entity.Calculator;
import com.visolink.h5.mapper.CalculatorMapper;
@Service("calculatorService")
public class CalculatorService {
	
	@Resource(name="calculatorMapper")
	private CalculatorMapper calculatorMapper;
	
	/**
	 * 插入计算器
	 * @param calculator
	 * @return
	 * @throws Exception
	 */
	public void insertCal(Calculator calculator) throws Exception{
		calculatorMapper.insertCal(calculator);
	}

}
