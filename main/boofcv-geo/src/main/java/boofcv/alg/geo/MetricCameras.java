/*
 * Copyright (c) 2011-2020, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.geo;

import boofcv.struct.calib.CameraPinhole;
import georegression.struct.se.Se3_F64;
import org.ddogleg.struct.FastQueue;

/**
 * Describes the camera pose and intrinsic parameters for a set of cameras.
 *
 * @author Peter Abeles
 */
public class MetricCameras {
	/**
	 * Motion relative to first camera.
	 * First camera is implicit, not included in this list, and assumed to be the origin
	 */
	public final FastQueue<Se3_F64> motion_1_to_k = new FastQueue<>(Se3_F64::new);
	/** Intrinsic parameters for all cameras */
	public final FastQueue<CameraPinhole> intrinsics = new FastQueue<>(CameraPinhole::new);

	public void reset() {
		motion_1_to_k.reset();
		intrinsics.reset();
	}
}