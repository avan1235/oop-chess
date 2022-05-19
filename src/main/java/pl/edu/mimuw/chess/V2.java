package pl.edu.mimuw.chess;

import java.util.Objects;

public final class V2 implements Comparable <V2>
{

	public static final V2 N = v(1, 0);
	public static final V2 S = v(-1, 0);
	public static final V2 E = v(0, 1);
	public static final V2 W = v(0, -1);

	public static final V2 NE = N.plus(E);
	public static final V2 NW = N.plus(W);
	public static final V2 SE = S.plus(E);
	public static final V2 SW = S.plus(W);

	public static final V2 NNE = N.plus(N).plus(E);
	public static final V2 NEE = N.plus(E).plus(E);
	public static final V2 SSE = S.plus(S).plus(E);
	public static final V2 SEE = S.plus(E).plus(E);
	public static final V2 NNW = N.plus(N).plus(W);
	public static final V2 NWW = N.plus(W).plus(W);
	public static final V2 SSW = S.plus(S).plus(W);
	public static final V2 SWW = S.plus(W).plus(W);


	public final int x;
	public final int y;

	private V2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public V2 plus(V2 v)
	{
		return v(this.x + v.x, this.y + v.y);
	}

	public V2 times(int scalar)
	{
		return v(this.x * scalar, this.y * scalar);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		V2 v2 = (V2) o;
		return x == v2.x && y == v2.y;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(x, y);
	}

	public static V2 v(int x, int y)
	{
		return new V2(x, y);
	}

	@Override
	public int compareTo(V2 o)
	{
		if (this.x == o.x)
			return Integer.compare(this.y, o.y);
		else return Integer.compare(this.x, o.x);
	}
}