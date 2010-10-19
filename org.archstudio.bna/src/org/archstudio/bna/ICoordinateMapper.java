package org.archstudio.bna;

public interface ICoordinateMapper extends java.io.Serializable {

	public static final ICoordinateMapper IDENTITY_COORDINATE_MAPPER = new ICoordinateMapper() {

		public int worldYtoLocalY(int worldY) {
			return worldY;
		}

		public int worldXtoLocalX(int worldX) {
			return worldX;
		}

		public int localYtoWorldY(int worldY) {
			return worldY;
		}

		public int localXtoWorldX(int worldX) {
			return worldX;
		}

		public int getWorldMinY() {
			return Integer.MIN_VALUE;
		}

		public int getWorldMinX() {
			return Integer.MIN_VALUE;
		}

		public int getWorldMaxY() {
			return Integer.MAX_VALUE;
		}

		public int getWorldMaxX() {
			return Integer.MAX_VALUE;
		}

		public int getWorldCenterY() {
			return 0;
		}

		public int getWorldCenterX() {
			return 0;
		}

		public double getScale() {
			return 1;
		}

		public void addCoordinateMapperListener(ICoordinateMapperListener l) {
			// do nothing
		}

		public void removeCoordinateMapperListener(ICoordinateMapperListener l) {
			// do nothing
		}
	};

	public int worldXtoLocalX(int worldX);

	public int worldYtoLocalY(int worldY);

	public int localXtoWorldX(int worldX);

	public int localYtoWorldY(int worldY);

	public double getScale();

	public int getWorldMinX();

	public int getWorldCenterX();

	public int getWorldMaxX();

	public int getWorldMinY();

	public int getWorldCenterY();

	public int getWorldMaxY();

	public void addCoordinateMapperListener(ICoordinateMapperListener l);

	public void removeCoordinateMapperListener(ICoordinateMapperListener l);
}
