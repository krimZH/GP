package cn.krim.gp.core.model.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.krim.gp.core.model.AbstratEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 答案使用字符串保存的
 * @author krim
 *
 */
@Data
@Entity
@Table(name="common_question")
@EqualsAndHashCode(callSuper=false)
public class CommonQuestion extends AbstratEntity {
	/**主键*/
	@Id @GeneratedValue
	protected Integer questionId;
	@Column
	/**类型*/
	private Integer type;
	/**题目内容*/
	@Column
	protected String questionContent;
	/**答案*/
	@Column
	protected String answer;
	/**答案解析*/
	@Column
	protected String analysis;
	/**是否为核心题目：0=不是，1=是*/
	@Column
	protected Integer core;
	/**题目被使用次数*/
	@Column
	protected Integer questedCount;
	/**正确率*/
	@Column
	protected Double correctRate;
	/**创建时间*/
	@Column
	protected Long createTime;
	/**修改时间*/
	@Column
	protected Long updateTime;
	@Override
	public Integer getEntityId() {
		return getQuestionId();
	}
}
