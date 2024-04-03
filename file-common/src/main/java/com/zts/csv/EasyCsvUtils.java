package com.zts.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.zts.annotation.CsvIndex;

/**
 * @author zts
 * @date 2024/4/2 20:07
 * @Description
 */
public class EasyCsvUtils<T> {

	public List<T> read(String filePath, int headRow, Class<T> tClass) throws IOException, CsvException {
		CSVReader csvReader = new CSVReader(new FileReader(filePath));
		List<T> dataList = new ArrayList<>();
		List<String[]> rows = csvReader.readAll();
		int i = 0;
		// 获取到所有的字段，
		Field[] declaredFields = tClass.getDeclaredFields();
		Map<Integer, Field> filedMap = new HashMap<>();
		for(Field field : declaredFields) {
			CsvIndex annotation = field.getAnnotation(CsvIndex.class);
			if (annotation != null  && annotation.index() >= 0) {
				filedMap.put(annotation.index(), field);
			}
		}
		for (String[] row : rows) {
			if (i < headRow) {
				i++;
				continue;
			}
			T t = null;
			try {
				t = tClass.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			for (int j = 0; j < row.length; j++) {
				Field field = filedMap.get(j);
				if (field != null) {
					field.setAccessible(true);
					try {
						field.set(t, row[j]);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
			dataList.add(t);
		}
		return dataList;
	}

	/**
	 * 传入的headerMap一定要是有序的。
	 * @param tClass
	 * @param headerMap
	 */
	private Map<Integer, Field> getFieldMap(Class<T> tClass, Map<Integer, String> headerMap) {
		Field[] declaredFields = tClass.getDeclaredFields();
		Map<String, Field> fieldMap = Arrays.stream(declaredFields).collect(Collectors.toMap(Field::getName, field -> field));
		int i = 0;
		Map<Integer, Field> result = new HashMap<>();
		for (Map.Entry<Integer, String> integerStringEntry : headerMap.entrySet()) {
			if (fieldMap.containsKey(integerStringEntry.getValue())) {
				result.put(i, fieldMap.get(integerStringEntry.getValue()));
			}
			i++;
		}
		return result;
	}

}
