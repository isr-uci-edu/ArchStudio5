package org.archstudio.swtutils;

public interface IFinder<C> {
	public IFindResult[] find(C context, String search);

	public void selected(IFindResult selectedResult);
}
