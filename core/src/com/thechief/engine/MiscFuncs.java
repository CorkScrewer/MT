package com.thechief.engine;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class MiscFuncs {

	public static Vector3 clamp(Vector3 v, Vector3 min, Vector3 max) {
		v.x = MathUtils.clamp(v.x, min.x, max.x);
		v.y = MathUtils.clamp(v.y, min.y, max.y);
		return v;
	}

}
