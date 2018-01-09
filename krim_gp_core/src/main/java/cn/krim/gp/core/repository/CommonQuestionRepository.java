package cn.krim.gp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.krim.gp.core.modle.questions.CommonQuestion;

public interface CommonQuestionRepository extends JpaRepository<CommonQuestion, Long> {

}
