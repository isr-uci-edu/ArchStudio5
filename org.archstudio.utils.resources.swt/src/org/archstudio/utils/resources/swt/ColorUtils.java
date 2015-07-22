package org.archstudio.utils.resources.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class ColorUtils {
  // A dynamically created map from Display to RGB to cached Color.
  private static final LoadingCache<Display, LoadingCache<RGB, Color>> colorCache = CacheBuilder
      .newBuilder().removalListener(new RemovalListener<Display, LoadingCache<RGB, Color>>() {
        @Override
        public void onRemoval(RemovalNotification<Display, LoadingCache<RGB, Color>> notification) {
          notification.getValue().invalidateAll();
        }
      }).build(new CacheLoader<Display, LoadingCache<RGB, Color>>() {
        @Override
        public LoadingCache<RGB, Color> load(final Display display) throws Exception {
          display.disposeExec(new Runnable() {
            @Override
            public void run() {
              colorCache.invalidate(display);
            }
          });
          return CacheBuilder.newBuilder().removalListener(new RemovalListener<RGB, Color>() {
            @Override
            public void onRemoval(RemovalNotification<RGB, Color> notification) {
              notification.getValue().dispose();
            }
          }).build(new CacheLoader<RGB, Color>() {
            @Override
            public Color load(RGB key) throws Exception {
              return new Color(display, key);
            }
          });
        }
      });

  /**
   * Returns a color instance for the given display of the given color.
   *
   * @param display The display with which to create the color.
   * @param rgb The RGB value for the color.
   * @return A color instance with the specified RGB.
   */
  public static final Color getColor(Display display, RGB rgb) {
    return colorCache.getUnchecked(display).getUnchecked(rgb);
  }

  /**
   * Returns the given system color.
   *
   * @param display The display with which to fetch the color.
   * @param swtId The SWT color constant (e.g., {@link SWT#COLOR_BLACK}).
   * @return A color instance for the specified SWT color onstant.
   */
  public static final Color getSystemColor(Display display, int swtId) {
    return display.getSystemColor(swtId);
  }
}
