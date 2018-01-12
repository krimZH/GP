package cn.krim.gp.front.model.questions;

import lombok.Data;

/**
 * 作业，试卷
 * @author krim
 *
 */
@Data
public class TestPaper {

	private  Integer testId;
	/**选择题*/
	
	private String multipleChoices;
	
	private String drawings;
	/**填空题*/
	
	private String fillInBlanks;
	/**简答题*/
	
	private String shortAnswers;
	/**使用次数*/
	
	private Integer testCount;
	/**平均分数*/
	
	private Double argScore;
	/**创建时间*/
	
	private Long creatTime;
	/**更新时间*/
	
	private Long updateTime;
}
