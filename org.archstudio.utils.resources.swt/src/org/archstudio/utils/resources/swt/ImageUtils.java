package org.archstudio.utils.resources.swt;

import java.net.URL;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.Maps;

/**
 * General utilities for working with images.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class ImageUtils {
  /** Mapping from class to icon. */
  private static final Map<Class<?>, URL> typeToIcon16Url = Maps.newHashMap();
  static {
    // Read plug-in extensions and populate typeToIcon16Url.
    IExtensionRegistry reg = Platform.getExtensionRegistry();
    if (reg != null) {
      for (IConfigurationElement configurationElement : reg
          .getConfigurationElementsFor("org.archstudio.utils.resources")) {
        if ("Icon".equals(configurationElement.getName())) {
          String bundleName =
              configurationElement.getDeclaringExtension().getContributor().getName();
          String icon16Path = configurationElement.getAttribute("icon16");
          for (IConfigurationElement type : configurationElement.getChildren("Type")) {
            String className = type.getAttribute("class");
            try {
              Class<?> typeClass = Platform.getBundle(bundleName).loadClass(className);
              URL imageURL = Platform.getBundle(bundleName).getEntry(icon16Path);
              typeToIcon16Url.put(typeClass, imageURL);
            } catch (ClassNotFoundException cnfe) {
            }
          }
        }
      }
    }
  }

  // A dynamically created map from Display to URL to cached Image.
  private static final LoadingCache<Display, LoadingCache<URL, Image>> imageCache = CacheBuilder
      .newBuilder().removalListener(new RemovalListener<Display, LoadingCache<URL, Image>>() {
        @Override
        public void onRemoval(RemovalNotification<Display, LoadingCache<URL, Image>> notification) {
          notification.getValue().invalidateAll();
        }
      }).build(new CacheLoader<Display, LoadingCache<URL, Image>>() {
        @Override
        public LoadingCache<URL, Image> load(final Display display) throws Exception {
          display.disposeExec(new Runnable() {
            @Override
            public void run() {
              imageCache.invalidate(display);
            }
          });
          return CacheBuilder.newBuilder().removalListener(new RemovalListener<URL, Image>() {
            @Override
            public void onRemoval(RemovalNotification<URL, Image> notification) {
              notification.getValue().dispose();
            }
          }).build(new CacheLoader<URL, Image>() {
            @Override
            public Image load(URL key) throws Exception {
              try {
                if (key != null) {
                  return new Image(display, key.openStream());
                }
              } catch (Exception ignored) {
              }
              return null;
            }
          });
        }
      });

  /**
   * Returns a (cached) image loaded from the specified URL, or <code>null</code> if the URL does
   * not resolve to an image.
   *
   * @param display The Display to use to create the image.
   * @param url The url to the image file.
   * @return The Image for the given URL, or <code>null</code> if the Image cannot be created from
   *         the URL.
   */
  public static final Image getImage(Display display, URL url) {
    if (url != null) {
      return imageCache.getUnchecked(display).getUnchecked(url);
    }
    return null;
  }

  /**
   * Returns a 16x16 icon image for the specified type, or <code>null</code> if the type is not
   * registered. The registered types are read from the org.archstudio.utils.resources extension.
   *
   * @param display The Display to use to create the image.
   * @param forClass The class for which the icon is being requested.
   * @return The Image for the given class, or <code>null</code> if the Image cannot be created from
   *         the URL.
   */
  public static final Image getIcon16ForType(Display display, Class<?> forClass) {
    URL url = typeToIcon16Url.get(forClass);
    if (url != null) {
      return imageCache.getUnchecked(display).getUnchecked(url);
    }
    return null;
  }
}
