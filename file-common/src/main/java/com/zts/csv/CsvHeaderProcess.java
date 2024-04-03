package com.zts.csv;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

/**
 * @author zts
 * @date 2024/4/3 11:46
 * @Description
 */
public class CsvHeaderProcess {

	private static final String Header_prefix = "Column";

	public CsvHeader getCsvHeader(CSVReader csvReader, int headRow) throws CsvValidationException, IOException {
		CsvHeader csvHeader = new CsvHeader();
		if (headRow > 0) {
			csvHeader = getCsvHeaderByFileHeader(csvReader, headRow);
		} else {
			csvHeader = getDefaultCsvHeader(csvReader);
		}
		return csvHeader;
	}


	/**
	 * 获取默认的文件头解析器。
	 * @param csvReader
	 * @return
	 * @throws CsvValidationException
	 * @throws IOException
	 */
	private CsvHeader getDefaultCsvHeader(CSVReader csvReader) throws CsvValidationException, IOException {
		CsvHeader csvHeader = new CsvHeader();
		String[] strings = csvReader.readNext();
		if (strings == null) {
			return csvHeader;
		}
		AtomicReference<Integer> index = new AtomicReference<>(0);
		Map<Integer, String> headerMap = Arrays.stream(strings).collect(Collectors.toMap(s -> index.getAndSet(index.get() + 1), s -> Header_prefix + index.get()));
		csvHeader.setHeaderMap(headerMap);
		return csvHeader;
	}

	private CsvHeader getCsvHeaderByFileHeader(CSVReader csvReader, int headRow) throws CsvValidationException, IOException {
		CsvHeader csvHeader = new CsvHeader();
		String[] strings = csvReader.readNext();
		if (strings == null) {
			return csvHeader;
		}
		AtomicReference<Integer> index = new AtomicReference<>(0);
		Map<Integer, String> headerMap = Arrays.stream(strings).collect(Collectors.toMap(s -> index.getAndSet(index.get() + 1), s -> s));
		csvHeader.setHeaderMap(headerMap);
		return csvHeader;
	}
}
