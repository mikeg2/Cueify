package com.cueify.cuelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.ListBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import com.cueify.cue.Cue;
import com.cueify.cuelist.behavior.ListItemCallNextBehavior;
import com.cueify.ticker.Tickable;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="cueList")
public class CueList implements ObservableList<CueListItem> {
	public static final ListItemCallNextBehavior defaultItemBehavior = ListItemCallNextBehavior.CALL_NEVER;

	private  ObservableList<CueListItem> cueListItems = FXCollections.<CueListItem>synchronizedObservableList(FXCollections.<CueListItem>observableArrayList());
	
	@XmlTransient
	private  ObservableList<Cue> cueList = FXCollections.<Cue>synchronizedObservableList(new ListBinding<Cue>() {
		
		{
			bind(cueListItems);
		}
		@Override
		protected ObservableList<Cue> computeValue() {
			System.out.println(Arrays.toString(cueListItems.toArray()));
			ArrayList<Cue> values = new ArrayList<>();
			synchronized(cueListItems) {
				for (CueListItem item : cueListItems) {
					values.add(item.getCue());
				}
			}
			return FXCollections.observableArrayList(values);
		}
	});
	
	public void add(Cue cue) {
		CueListItem item = createCueListItem(cue);
		cueListItems.add(item);
	}
	
	public void add(int index, Cue cue) {
		CueListItem item = createCueListItem(cue);
		cueListItems.add(index, item);
	}
	
	private CueListItem createCueListItem(Cue cue) {
		CueListItem item = new CueListItem(cue, this);
		prepareForList(item);
		return item;
	}

	private double getNextCueNumber() {
		if (cueList.size() != 0) {
			double number = cueList.get(0).getCueNumber() + 1;
			for (Cue cue : cueList) {
				number = cue.getCueNumber() >= number ? cue.getCueNumber()+1 : number;
			}
			return number;
		} else {
			return 1;
		}
	}

	public void remove(Cue cue) {
		for (CueListItem item : cueListItems) {
			if (item.getCue() == cue) {
				cueListItems.remove(item);
				break;
			}
		};
	}
	
	public int indexOf(Cue cue) {
		int i;
		for (i = 0; i < cueListItems.size(); i++) {
			if (cueListItems.get(i).getCue() == cue){
				break;
			}
		}
		return i;
	}
	
	public ObservableList<Cue> getCueList() {
		return cueList;
	}

	public int numberOfCues() {
		return cueListItems.size();
	}

	public void callNextForCue(Cue cue) {
		System.out.println("CALL NEXT---------------->");
		int nextCue = indexOf(cue) + 1;
		if (size() == nextCue) {
			return;
		}
		get(nextCue).getCue().getCueTimeline().play();
	}

	// Called on CueListItems to "indoctrinate" them into the list
	public void prepareForList(CueListItem...element) {
		double number = getNextCueNumber();
		for (CueListItem cueListItem : element) {
			if (cueListItem.getCue().getCueNumber().equals(Double.NaN)) {
				cueListItem.getCue().setCueNumber(number);
				number++;
			}
			cueListItem.setList(this);
		}
	}

	@Override
	public int size() {
		return cueListItems.size();
	}

	@Override
	public boolean isEmpty() {
		return cueListItems.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return cueListItems.contains(o);
	}

	@Override
	public Object[] toArray() {
		return cueListItems.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return cueListItems.toArray(a);
	}

	@Override
	public boolean add(CueListItem e) {
		prepareForList(e);
		return cueListItems.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return cueListItems.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return cueListItems.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends CueListItem> c) {
		prepareForList((CueListItem[]) c.toArray());
		return cueListItems.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends CueListItem> c) {
		prepareForList((CueListItem[]) c.toArray());
		return cueListItems.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return cueListItems.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return cueListItems.retainAll(c);
	}

	@Override
	public void clear() {
		cueListItems.clear();
	}

	@Override
	public CueListItem get(int index) {
		return cueListItems.get(index);
	}

	@Override
	public CueListItem set(int index, CueListItem element) {
		prepareForList(element);
		return cueListItems.set(index, element);
	}

	@Override
	public void add(int index, CueListItem element) {
		prepareForList(element);
		cueListItems.add(index, element);
	}

	@Override
	public CueListItem remove(int index) {
		return cueListItems.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return cueListItems.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return cueListItems.lastIndexOf(o);
	}

	@Override
	public ListIterator<CueListItem> listIterator() {
		return cueListItems.listIterator();
	}

	@Override
	public ListIterator<CueListItem> listIterator(int index) {
		return cueListItems.listIterator(index);
	}

	@Override
	public List<CueListItem> subList(int fromIndex, int toIndex) {
		return cueListItems.subList(fromIndex, toIndex);
	}

	@Override
	public void addListener(InvalidationListener listener) {
		cueListItems.addListener(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		cueListItems.removeListener(listener);
	}

	@Override
	public boolean addAll(CueListItem... elements) {
		prepareForList(elements);
		return cueListItems.addAll(elements);
	}

	@Override
	public void addListener(ListChangeListener<? super CueListItem> listener) {
		cueListItems.addListener(listener);
	}

	@Override
	public void remove(int from, int to) {
		cueListItems.remove(from, to);
	}

	@Override
	public boolean removeAll(CueListItem... elements) {
		return cueListItems.removeAll(elements);
	}

	@Override
	public void removeListener(ListChangeListener<? super CueListItem> listener) {
		cueListItems.removeListener(listener);
	}

	@Override
	public boolean retainAll(CueListItem... elements) {
		return cueListItems.retainAll(elements);
	}

	@Override
	public boolean setAll(CueListItem... elements) {
		return cueListItems.setAll(elements);
	}

	@Override
	public boolean setAll(Collection<? extends CueListItem> col) {
		return cueListItems.setAll(col);
	}

	@Override
	public Iterator<CueListItem> iterator() {
		return cueListItems.iterator();
	}
}
