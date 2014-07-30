package org.archstudio.resources;

import java.net.URL;

import org.archstudio.utils.metadata.IconUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class ResourceCache {

	private static final LoadingCache<URL, Image> imageCache = CacheBuilder.newBuilder()
			.removalListener(new RemovalListener<URL, Image>() {
				@Override
				public void onRemoval(RemovalNotification<URL, Image> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<URL, Image>() {
				@Override
				public Image load(URL key) throws Exception {
					return new Image(Display.getDefault(), key.openStream());
				}
			});

	public static final Image getImage(URL url) {
		return imageCache.getUnchecked(url);
	}

	public static final Image getIcon(IXArchADT xarch, ObjRef objRef) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
		String nsURI = typeMetadata.getNsURI();
		EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
		if (ePackage != null) {
			String typeName = typeMetadata.getTypeName();
			EClassifier eClassifier = ePackage.getEClassifier(typeName);
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				Class<?> instanceClass = eClass.getInstanceClass();
				return getIcon(instanceClass);
			}
		}
		return null;
	}

	public static final Image getIcon(Class<?> forClass) {
		URL imageURL = IconUtils.getIconForType(forClass);
		if (imageURL != null) {
			return ResourceCache.getImage(imageURL);
		}
		return null;
	}
}