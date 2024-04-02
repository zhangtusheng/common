package com.zts.csv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

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
		for (String[] row : rows) {
			if (i < headRow) {
				i++;
				continue;
			}

		}
	}

}
