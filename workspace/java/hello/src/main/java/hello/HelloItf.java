package hello;

import integratedtoolkit.types.annotations.Constraints;
import integratedtoolkit.types.annotations.Method;


public interface HelloItf {
	
	@Constraints(processorCoreCount = 1)
	@Method(declaringClass = "hello.HelloImpl")
	void sayHello(
	);
}
