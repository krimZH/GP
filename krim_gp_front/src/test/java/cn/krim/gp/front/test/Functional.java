package cn.krim.gp.front.test;

@FunctionalInterface
public interface Functional {
	void method();
	default String defaultMethod(){
		return "default method";
	}
}
