package cn.krim.gp.core.modle.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 作业，试卷
 * @author krim
 *
 */
@Entity
@Table(name="test_paper")
@Data
public class TestPaper {
	@Id @GeneratedValue
	private  int testId;
	/**选择题*/
	@Column
	private String multipleChoices;
	@Column
	private String drawings;
	/**填空题*/
	@Column
	private String fillInBlanks;
	/**简答题*/
	@Column
	private String shortAnswers;
	/**使用次数*/
	@Column
	private Integer testCount;
	/**平均分数*/
	@Column
	private Double argScore;
	/**创建时间*/
	@Column
	private Long creatTime;
	/**更新时间*/
	@Column
	private Long updateTime;
}
