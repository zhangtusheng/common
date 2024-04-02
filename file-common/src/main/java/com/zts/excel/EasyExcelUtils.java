package com.zts.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;


/**
 * @author zts
 * @date 2024/4/2 17:27
 * @Description
 */
public class EasyExcelUtils<T> {

	/**
	 * 写文件。
	 * @param list
	 * @param fileName
	 * @param clazz
	 * @param sheetName
	 */
	public void writeData(List<T> list, String fileName, Class<T> clazz, String sheetName) {
		EasyExcel.write(fileName, clazz).sheet(sheetName).doWrite(list);
	}

	/**
	 * 读文件。
	 * @param fileName
	 * @param clazz
	 * @param headRow
	 * @param callback
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<T> readData(String fileName, Class<T> clazz, int headRow, Consumer<List<T>> callback) throws FileNotFoundException {
		File file = new File(fileName);

		FileInputStream fileInputStream = new FileInputStream(file);

		// 使用线程安全的列表来存储数据
		List<T> dataList = new CopyOnWriteArrayList<>();

		AnalysisEventListener<T> analysisEventListener = new AnalysisEventListener<T>() {

			@Override
			public void invoke(T data, AnalysisContext context) {
				dataList.add(data);
			}


			@Override
			public void doAfterAllAnalysed(AnalysisContext context) {
				callback.accept(dataList);
			}

		};

		// 读取 Excel 文件
		EasyExcel.read(fileInputStream)
			.head(clazz)
			.registerReadListener(analysisEventListener)
			.headRowNumber(headRow)
			.sheet()
			.doRead();

		return dataList;
	}


}
