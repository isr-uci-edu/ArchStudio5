package org.archstudio.archipelago2;

public interface IArchipelago2Outline {
  /**
   * Causes the outline to select a given element if it exists.
   *
   * @see IArchipelago2ContentProvider
   * @param element The element to select.
   */
  public void selectElement(Object element);

  /**
   * Causes the outline to edit an element if it exists and can be edited.
   *
   * @param element The element to edit.
   * @param column The column to edit.
   */
  public void editElement(Object element, int column);
}
