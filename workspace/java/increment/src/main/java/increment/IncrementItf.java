package increment;

import integratedtoolkit.types.annotations.Constraints;
import integratedtoolkit.types.annotations.Method;
import integratedtoolkit.types.annotations.Parameter;
import integratedtoolkit.types.annotations.Parameter.Direction;
import integratedtoolkit.types.annotations.Parameter.Type;


public interface IncrementItf {
	
	@Constraints(processorCoreCount = 1)
	@Method(declaringClass = "increment.IncrementImpl")
	void increment(
		@Parameter(type = Type.FILE, direction = Direction.INOUT) String file
	);
}
