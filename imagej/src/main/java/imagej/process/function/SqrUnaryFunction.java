package imagej.process.function;

import imagej.process.TypeManager;
import mpicbg.imglib.type.numeric.RealType;
import mpicbg.imglib.type.numeric.integer.UnsignedShortType;

public class SqrUnaryFunction implements UnaryFunction
{
	private RealType<?> targetType;
	private boolean isUnsignedShort;
	
	public SqrUnaryFunction(RealType<?> targetType)
	{
		this.targetType = targetType;
		this.isUnsignedShort = targetType instanceof UnsignedShortType;
	}
	
	public double compute(double input)
	{
		double value = input * input;
		
		// this needed for compatibility with old SHortProcessor
		if (this.isUnsignedShort)
			if (value > Integer.MAX_VALUE)
				value = 0;
		
		value = TypeManager.boundValueToType(this.targetType, value);
		
		return value;
	}
}

