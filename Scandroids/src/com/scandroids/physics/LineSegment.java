package com.scandroids.physics;

import com.badlogic.gdx.math.Vector2;

public class LineSegment {
	private Vector2 start = new Vector2();
	private Vector2 end = new Vector2();

	/**
	 * Construct a new LineSegment with the specified coordinates.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public LineSegment(float x1, float y1, float x2, float y2) {
		start = new Vector2(x1, y1);
		end = new Vector2(x2, y2);
	}

	/**
	 * The "start" of the line. Start and end are misnomers, this is just
	 * one end of the line.
	 * 
	 * @return Vector2
	 */
	public Vector2 start() {
		return start;
	}

	/**
	 * The "end" of the line. Start and end are misnomers, this is just one
	 * end of the line.
	 * 
	 * @return Vector2
	 */
	public Vector2 end() {
		return end;
	}

	/**
	 * Determine if the requested line could be tacked on to the end of this
	 * line with no kinks or gaps. If it can, the current LineSegment will
	 * be extended by the length of the passed LineSegment.
	 * 
	 * @param lineSegment
	 * @return boolean true if line was extended, false if not.
	 */
	public boolean extendIfPossible(LineSegment lineSegment) {
		/**
		 * First, let's see if the slopes of the two segments are the same.
		 */
		double slope1 = Math.atan2(end.y - start.y, end.x - start.x);
		double slope2 = Math.atan2(lineSegment.end.y - lineSegment.start.y,
				lineSegment.end.x - lineSegment.start.x);

		if (Math.abs(slope1 - slope2) > 1e-9) {
			return false;
		}

		/**
		 * Second, check if either end of this line segment is adjacent to
		 * the requested line segment. So, 1 pixel away up through sqrt(2)
		 * away.
		 * 
		 * Whichever two points are within the right range will be "merged"
		 * so that the two outer points will describe the line segment.
		 */
		if (start.dst(lineSegment.start) <= Math.sqrt(2) + 1e-9) {
			start.set(lineSegment.end);
			return true;
		} else if (end.dst(lineSegment.start) <= Math.sqrt(2) + 1e-9) {
			end.set(lineSegment.end);
			return true;
		} else if (end.dst(lineSegment.end) <= Math.sqrt(2) + 1e-9) {
			end.set(lineSegment.start);
			return true;
		} else if (start.dst(lineSegment.end) <= Math.sqrt(2) + 1e-9) {
			start.set(lineSegment.start);
			return true;
		}

		return false;
	}

	/**
	 * Returns a pretty description of the LineSegment.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "[" + start.x + "x" + start.y + "] -> [" + end.x + "x"
				+ end.y + "]";
	}
}
