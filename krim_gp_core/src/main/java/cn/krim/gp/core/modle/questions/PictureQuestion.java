package cn.krim.gp.core.modle.questions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 答案使用图片保存的
 * @author krim
 *
 */
@Data
@Entity
@Table(name="picture_question")
public class PictureQuestion {
	/**主键*/
	@Id @GeneratedValue
	protected Integer questionId;
	/**类型*/
	@Column
	private Integer type;
	/**题目内容*/
	@Column
	protected String questionContent;
	/**答案保存路径*/
	@Column
	protected String answer_address;
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
}
