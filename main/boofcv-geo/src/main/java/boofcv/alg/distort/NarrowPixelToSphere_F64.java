/*
 * Copyright (c) 2011-2019, Peter Abeles. All Rights Reserved.
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

package boofcv.alg.distort;

import boofcv.struct.distort.Point2Transform2_F64;
import boofcv.struct.distort.Point2Transform3_F64;
import georegression.struct.point.Point2D_F64;
import georegression.struct.point.Point3D_F64;

/**
 * Given a transform from pixels to normalized image coordinate it will output unit sphere coordinates.
 *
 * @author Peter Abeles
 */
public class NarrowPixelToSphere_F64 implements Point2Transform3_F64 {

	Point2Transform2_F64 pixelToNorm;
	Point2D_F64 projected = new Point2D_F64();

	public NarrowPixelToSphere_F64(Point2Transform2_F64 pixelToNorm) {
		this.pixelToNorm = pixelToNorm;
	}

	@Override
	public void compute(double x, double y, Point3D_F64 out) {
		pixelToNorm.compute(x,y,projected);

		out.set(projected.x,projected.y,1);
		out.scale( 1.0/out.norm() );
	}

	@Override
	public Point2Transform3_F64 copyConcurrent() {
		return new NarrowPixelToSphere_F64(this.pixelToNorm.copyConcurrent());
	}
}
