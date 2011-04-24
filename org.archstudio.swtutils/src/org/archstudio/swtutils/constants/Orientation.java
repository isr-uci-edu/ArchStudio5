package org.archstudio.swtutils.constants;

public enum Orientation{
	NONE,
	NORTHWEST,
	NORTH,
	NORTHEAST,
	EAST,
	SOUTHEAST,
	SOUTH,
	SOUTHWEST,
	WEST;
	
	public Orientation opposite(){
		switch(this){
			case NONE: return NONE;
			case NORTHWEST: return SOUTHEAST;
			case NORTH: return SOUTH;
			case NORTHEAST:return SOUTHWEST;
			case EAST: return WEST;
			case SOUTHEAST: return NORTHWEST;
			case SOUTH: return NORTH;
			case SOUTHWEST: return NORTHEAST;
			case WEST: return EAST;
			default:
				return NONE;
		}
	}
}
