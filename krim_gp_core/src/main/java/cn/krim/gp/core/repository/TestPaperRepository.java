package cn.krim.gp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.krim.gp.core.model.questions.TestPaper;

public interface TestPaperRepository extends JpaRepository<TestPaper, Long> {

}
