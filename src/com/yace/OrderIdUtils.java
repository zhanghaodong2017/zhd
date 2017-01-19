package com.yace;

import java.util.Random;

public class OrderIdUtils
{
	private static Random	ran	= new Random();

	public static String getOrderId(int length)
	{
		String redix = "";
		if (length >= 8)
		{
			return Get8OrderId() + shortUuid(length - 8);
		}
		else if (length >= 6)
		{
			return Get6OrderId()  + shortUuid(length - 6);
		}
		else if (length >= 4)
		{
			int l = length - 4;
			for (int i = 0; i < l; i++)
			{
				redix = redix +shortUuid(length - 4);
			}
			return Get4OrderId() + redix;
		}
		else
		{
			int i = 36;
			if (length == 3)
			{
				i = 36 * 36 * 36;
			}
			else if (length == 2)
			{
				i = 36 * 36;
			}
			return Long.toString(ran.nextInt(i), 36);
		}

	}

	private static String Get6OrderId()
	{

		Long t = Long.valueOf((System.currentTimeMillis() + "").substring(3));

		if (t > 1679615)
		{
			t = Long.valueOf(t.toString().substring(1));
		}

		return Long.toString(t, 36);

	}

	private static String Get8OrderId()
	{
		return Long.toString(System.currentTimeMillis(), 36);
	}

	private static String Get4OrderId()
	{

		Integer t = Integer.valueOf((System.currentTimeMillis() + "")
				.substring(6));

		if (t > 1679615)
		{
			t = Integer.valueOf(t.toString().substring(1));
		}

		return Long.toString(t, 36);
	}

	public static String shortUuid(int index) {
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" };
		StringBuilder shortBuilder = new StringBuilder();

		for (int i = 0; i < index; i++) {
			int ranInt = ran.nextInt(chars.length);
			String str = chars[ranInt];
			shortBuilder.append(str);
		}
		return shortBuilder.toString();

	}
}
