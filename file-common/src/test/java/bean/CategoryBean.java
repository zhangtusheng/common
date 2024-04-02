package bean;

import com.zts.annotation.CsvIndex;
import lombok.Data;

/**
 * @author zts
 * @date 2024/4/2 21:46
 * @Description
 */
@Data
public class CategoryBean {

	@CsvIndex(index = 0)
	private String query;

	@CsvIndex(index = 2)
	private String virtualCategoryName;

	@CsvIndex(index = 1)
	private String backCategoryName;
}
