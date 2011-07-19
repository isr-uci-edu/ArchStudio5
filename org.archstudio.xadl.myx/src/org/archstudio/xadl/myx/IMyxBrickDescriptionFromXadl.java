package org.archstudio.xadl.myx;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public interface IMyxBrickDescriptionFromXadl {

	public IMyxBrickDescription parse(IXArchADT xarch, ObjRef brickRef) throws MyxUnsupportedBrickDescriptionException;

}
