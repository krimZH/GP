package cn.krim.gp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.krim.gp.core.model.questions.PictureQuestion;

public interface PictureQuestionRepository extends JpaRepository<PictureQuestion, Long> {

}
