package com.visolink.h5.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource  extends AbstractRoutingDataSource{
	public static final String DATA_SOURCE_REMOTE = "remoteDataSource";
	public static final String DATA_SOURCE_LOCAL = "localDataSource";
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }

	public static String getDatasourcekey() {
		return dataSourceKey.get();
	}
	
	public static void clearDatasourcekey() {  
		dataSourceKey.remove();  
    }


	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}

}
