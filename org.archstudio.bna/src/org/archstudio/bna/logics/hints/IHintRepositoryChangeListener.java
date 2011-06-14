package org.archstudio.bna.logics.hints;

public interface IHintRepositoryChangeListener {

	/**
	 * @param repository
	 * @param context
	 * @param hintName
	 *            The name of the hint that was changed, or <code>null</code> to indicate that all hints should be
	 *            restored for the given context.
	 */
	public void hintRepositoryChanged(IHintRepository repository, Object context, String hintName);
}
