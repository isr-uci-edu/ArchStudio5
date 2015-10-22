package org.archstudio.bna.ui;

import org.archstudio.bna.utils.BNAUtils2;

/**
 * Logics that implement this interface will receive all UI events regardless of what view they are in. The logic for
 * selecting which view is normally targeted by a UI action is described in
 * {@link BNAUtils2#getThingsAtLocation(org.archstudio.bna.IBNAView, org.archstudio.bna.ICoordinate)}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNAAllEventsListener2 {
}
