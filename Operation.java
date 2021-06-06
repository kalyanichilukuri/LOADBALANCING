package org.foo.app;

public class Operation {
	public String name;
	public int value;


	public Operation(String name, int value) throws OperationUnknownException {
		if (!name.equals("vi") && !name.equals("jio") && !name.equals("air"))
			throw new OperationUnknownException(name);
		this.name = name;
		this.value = value;
	}

	public int compute() throws OperationUnknownException {
		if (this.name.equals("vi"))
			return Operations.vi(this.value);
		else if (this.name.equals("jio"))
			return Operations.jio(this.value);
		else if (this.name.equals("air"))
			return Operations.air(this.value);
		else
			throw new OperationUnknownException(this.name);
	}
}