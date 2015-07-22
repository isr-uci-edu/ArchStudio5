package org.archstudio.utils.resources.swt;

import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class FontUtils {
  // A dynamically created map from Display to FontData to cached Image.
  private static final LoadingCache<Display, LoadingCache<FontData, Font>> fontCache = CacheBuilder
      .newBuilder().removalListener(new RemovalListener<Display, LoadingCache<FontData, Font>>() {
        @Override
        public void onRemoval(
            RemovalNotification<Display, LoadingCache<FontData, Font>> notification) {
          notification.getValue().invalidateAll();
        }
      }).build(new CacheLoader<Display, LoadingCache<FontData, Font>>() {
        @Override
        public LoadingCache<FontData, Font> load(final Display display) throws Exception {
          display.disposeExec(new Runnable() {
            @Override
            public void run() {
              fontCache.invalidate(display);
            }
          });
          return CacheBuilder.newBuilder().removalListener(new RemovalListener<FontData, Font>() {
            @Override
            public void onRemoval(RemovalNotification<FontData, Font> notification) {
              notification.getValue().dispose();
            }
          }).build(new CacheLoader<FontData, Font>() {
            @Override
            public Font load(FontData key) throws Exception {
              return new Font(display, key);
            }
          });
        }
      });

  /**
   * Returns the font described by the parameters.
   *
   * @param display The display with which to create the font.
   * @param name The font name.
   * @param height The font height (i.e., size).
   * @param style The font style (e.g., {@link SWT#ITALIC}).
   * @return The specified font.
   */
  public static final Font getFont(Display display, String name, int height, int style) {
    return fontCache.getUnchecked(display).getUnchecked(new FontData(name, height, style));
  }

  /**
   * Returns the font described by the parameters.
   *
   * @param display The display with which to create the font.
   * @param name The font name.
   * @param height The font height (i.e., size).
   * @param style The font style (e.g., {@link SWT#ITALIC}).
   * @return The specified font.
   */
  public static final Font getFont(Display display, String name, float height, int style) {
    return getFont(display, name, SystemUtils.floor(height), style);
  }
}
