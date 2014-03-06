package org.archstudio.launcher.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archstudio.launcher.ILaunchData;

/**
 * Myx brick: "Launcher Impl"
 * 
 * @see org.archstudio.launcher.core.LauncherMyxComponentStub
 * @generated
 */
public class LauncherMyxComponent extends org.archstudio.launcher.core.LauncherMyxComponentStub {

	public List<ILaunchData> getLaunchData() {
		launcher.getLaunchData();
		Object[] res = results.getReturnValues();
		List<ILaunchData> l = new ArrayList<ILaunchData>();
		for (Object re : res) {
			if (re != null) {
				l.add((ILaunchData) re);
			}
		}
		Collections.sort(l, new Comparator<ILaunchData>() {

			@Override
			public int compare(ILaunchData o1, ILaunchData o2) {
				String n1 = o1.getName();
				String n2 = o2.getName();
				return n1.compareTo(n2);
			}
		});
		return Collections.unmodifiableList(l);
	}
}