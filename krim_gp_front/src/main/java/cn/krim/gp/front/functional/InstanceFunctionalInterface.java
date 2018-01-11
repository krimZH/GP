package cn.krim.gp.front.functional;

@FunctionalInterface
public interface  InstanceFunctionalInterface<T> {
		String getIdentify();
		
		default Integer getHashCode(T t){
			return t.hashCode();
		}
}
