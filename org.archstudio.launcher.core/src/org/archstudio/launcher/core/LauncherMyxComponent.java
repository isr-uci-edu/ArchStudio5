package org.archstudio.launcher.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.archstudio.launcher.common.ILaunchData;
import org.archstudio.launcher.common.ILaunchable;
import org.archstudio.myx.conn.IMultiwayResults;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.resources.common.IResources;

public class LauncherMyxComponent extends AbstractMyxSimpleBrick {

	public static final IMyxName INTERFACE_NAME_OUT_RESOURCES = MyxUtils.createName("resources");
	public static final IMyxName INTERFACE_NAME_OUT_LAUNCHER = MyxUtils.createName("launcher");
	public static final IMyxName INTERFACE_NAME_OUT_LAUNCHER_RESULTS = MyxUtils.createName("results");

	protected IMultiwayResults launchable_results = null;
	protected ILaunchable launchable = null;
	protected IResources resources = null;

	private MyxRegistry er = MyxRegistry.getSharedInstance();

	public LauncherMyxComponent() {
	}

	public void begin() {
		resources = (IResources) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_RESOURCES);
		launchable = (ILaunchable) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_LAUNCHER);
		launchable_results = (IMultiwayResults) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_LAUNCHER_RESULTS);
		System.err.println("Registering Launcher");
		er.register(this);
	}

	public void end() {
		er.unregister(this);
	}

	public Object getServiceObject(IMyxName interfaceName) {
		return null;
	}

	public IResources getResources() {
		return resources;
	}

	public List<ILaunchData> getLaunchData() {
		launchable.getLaunchData();
		Object[] res = launchable_results.getReturnValues();
		List<ILaunchData> l = new ArrayList<ILaunchData>();
		for (int i = 0; i < res.length; i++) {
			if (res[i] != null) {
				l.add((ILaunchData)res[i]);
			}
		}
		Collections.sort(l, new Comparator<ILaunchData>() {
			public int compare(ILaunchData o1, ILaunchData o2) {
				String n1 = o1.getName();
				String n2 = o2.getName();
				return n1.compareTo(n2);
			}
		});
		return Collections.unmodifiableList(l);
	}

}
