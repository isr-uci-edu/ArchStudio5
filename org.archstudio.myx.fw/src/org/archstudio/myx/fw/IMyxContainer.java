package org.archstudio.myx.fw;

import java.util.Collection;

public interface IMyxContainer extends IMyxBrick {

	public void addInternalBrick(IMyxBrick brick);

	public void removeInternalBrick(IMyxBrick brick);

	public Collection<? extends IMyxBrick> getInternalBricks();

	public IMyxBrick getInternalBrick(IMyxName brickName);
}
