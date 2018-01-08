package cn.krim.gp.entities.scores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * 分数
 * @author krim
 *
 */
@Entity
@Table(name="score")
@Data
public class Score {
	@Id @GeneratedValue
	private Integer scoreId;
	@Column
	private String userId;
	@Column
	private String homeworkScore;
	@Column
	private String testScore;
	@Column
	private Double midScore;
	@Column
	private Double finalScore;
	@Column
	private Long createTime;
	@Column
	private Long updateTime;
}
