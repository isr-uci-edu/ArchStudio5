package org.archstudio.bna.ui;

import org.archstudio.bna.utils.BNAUtils2;

/**
 * An interface to indicate that all UI events should be sent to this logic, rather than only events targeted to the
 * logic's view. Mouse events are targeted to a particular view according to the rules described in
 * {@link BNAUtils2#getThingsAtLocation(org.archstudio.bna.IBNAView, org.archstudio.bna.ICoordinate)}.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNAAllEventsListener2 {
}
