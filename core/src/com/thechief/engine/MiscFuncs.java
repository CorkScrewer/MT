package com.thechief.engine;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MiscFuncs {

	public static Vector2 clamp(Vector2 v, Vector2 min, Vector2 max) {
		Vector2 r = v;
		r.x = MathUtils.clamp(r.x, min.x, max.x);
		r.y = MathUtils.clamp(r.y, min.y, max.y);
		return r;
	}

}
