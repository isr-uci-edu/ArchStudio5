package org.archstudio.archipelago2;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;

/**
 * Provides an initial label and image for a given Archipelago outline node.
 * <p/>
 * The Archipelago outline will iterate through all {@link IArchipelago2LabelProvider} instances and
 * use the first non-null value returned. Thus, implementers should be selective about which
 * elements return a value.
 * <p/>
 * All <code>element</code> parameters are lists, as described in
 * {@link IArchipelago2ContentProvider}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IArchipelago2LabelProvider extends IArchipelago2Provider, IStyledLabelProvider {
}
