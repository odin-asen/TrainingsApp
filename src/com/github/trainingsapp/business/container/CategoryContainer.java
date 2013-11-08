package com.github.trainingsapp.business.container;

import android.support.v4.util.ArrayMap;

import java.util.*;

/**
 * Die Klasse repraesentiert ein nach Kategorien geordnetes Array.
 * Die Typspezifizierungen geben die Kategorie an und die Elemente der Kategorie,
 * wobei C die Klasse der Kategorie ist und E das Element. Die Klasse ist unveraenderlich (immutable).
 * <p/>
 * Die Klasse ist nicht für grosse Datenmengen geeignet. Ob etwas zu gross ist, haengt davon ab, ob die
 * Performanz nicht ausreichend ist.
 * <p/>
 * Author: Timm Herrmann<br/>
 * Date: 08.11.13
 */
public abstract class CategoryContainer<C,E> {
  protected Map<C,List<E>> mAllElements;
  protected List<C> mCategories;

  /**
   * Das uergebene Array wird nicht veraendert. Es wird in ein anderes Array kopiert und
   * dieses sortiert.
   * @param elements Darf nicht leer oder null sein.
   * @param sortingCondition Darf nicht null sein.
   */
  public CategoryContainer(List<E> elements, Comparator<E> sortingCondition) {
    assert (elements == null) || elements.isEmpty() || (sortingCondition == null);

    final List<E> sortedElements = new ArrayList<E>(elements.size());
    addListElements(sortedElements, elements);

    Collections.sort(sortedElements, sortingCondition);
    setCategories(sortedElements);
    setMapElements(sortedElements);
  }

  /**
   * Gibt eine Kopie der Kategorieliste zurueck.
   */
  public List<C> getCategories() {
    final List<C> categories = new ArrayList<C>(mCategories.size());
    addListElements(categories, mCategories);
    return categories;
  }

  /**
   * Gibt eine Kopie der Elementenliste einer Kategorie zurueck.
   */
  public List<E> getElements(C category) {
    final List<E> originalList = mAllElements.get(category);
    final List<E> elements = new ArrayList<E>(originalList.size());
    addListElements(elements, originalList);
    return elements;
  }

  /**
   * Gibt eine Kopie der Map aller Elemente zurueck.
   */
  public Map<C,List<E>> getAllElements() {
    final Set<C> keySet = mAllElements.keySet();
    final Map<C,List<E>> map = new ArrayMap<C, List<E>>(keySet.size());

    copyAllElementsToMap(keySet, map);

    return map;
  }

  /**
   * Erstellt aus den Elementen eine Kategorieliste.
   */
  abstract protected void setCategories(List<E> elements);

  /**
   * Setzt die Elemente in eine nach Kategorie eingeteilte Map.
   */
  abstract protected void setMapElements(List<E> elements);

  /*******************/
  /* Private Methods */

  private void copyAllElementsToMap(Set<C> keySet, Map<C, List<E>> map) {
    for (C category : keySet) {
      final List<E> originalList = mAllElements.get(category);
      final List<E> elements = new ArrayList<E>(originalList.size());
      addListElements(elements, originalList);
      map.put(category, elements);
    }
  }

  private <T> void addListElements(List<T> destination, List<T> source) {
    for (T element : source) {
      destination.add(element);
    }
  }

  /*       End       */
  /*******************/
}
