package org.foo.app;

public class Operations {

	public static int vi(int x) {
		if (x == 0)
			return 0;
		if (x == 1)
			return 1;
		return vi(x - 1) + vi(x - 2);
	}

	public static int jio(int x) {
		int high = 0;

		for (int i = 1; i <= x; ++i)
		{
				high = i;
		}

		return high;
	}

	public static int air(int x) {
		if (x <= 1)
			return 1;

		for (int i = 2; i < x; ++i)
		{
			if (x % i == 0)
				return x;
		}

		return 0;		
	}

}