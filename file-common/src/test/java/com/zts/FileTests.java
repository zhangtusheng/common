package com.zts;

import java.io.IOException;
import java.util.List;

import bean.CategoryBean;
import com.alibaba.fastjson.JSON;
import com.opencsv.exceptions.CsvException;
import com.zts.csv.EasyCsvUtils;
import org.junit.jupiter.api.Test;


public class FileTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCSV() throws IOException, CsvException {
		EasyCsvUtils<CategoryBean> categoryBeanEasyCsvUtils = new EasyCsvUtils<>();
		List<CategoryBean> read = categoryBeanEasyCsvUtils.read("/Users/zts/IdeaProjects/common/file-common/src/main/resources/category.csv", 0, CategoryBean.class);
		System.out.println(JSON.toJSONString(read));
	}

}