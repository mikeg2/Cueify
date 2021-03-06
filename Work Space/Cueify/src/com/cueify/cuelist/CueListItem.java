package com.cueify.cuelist;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.cue.Cue;
import com.cueify.cuelist.behavior.CueItemBehaviorBinder;
import com.cueify.cuelist.behavior.ListItemCallNextBehavior;
import com.cueify.undo.Undoable;

public class CueListItem {
	private Cue cue;
	
	@XmlTransient
	private SimpleObjectProperty<ListItemCallNextBehavior> callNextBehavior = new SimpleObjectProperty<>();
	
	@XmlTransient
	private CueList list;
	
	@XmlTransient
	private Undoable addedBehavior = null;

	public CueListItem(Cue c, CueList list) {
		this.cue = c;
		this.list = list;
		linkBehaviorBinding();
		setCallNextBehavior(ListItemCallNextBehavior.CALL_NEVER);
	}
	
	public CueListItem(Cue c) {
		this(c, null);
	}
	
	public CueListItem() {
		
	}

	private void linkBehaviorBinding() {
		InvalidationListener invalidList = new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				System.out.println("UPDATE B");
				updateBehavior();
			}
		};
		callNextBehavior.addListener(invalidList);
	}

	private void updateBehavior() {
		if(addedBehavior != null)
			addedBehavior.undo();
		addedBehavior = CueItemBehaviorBinder.addBehaviorToCueForList(callNextBehavior.get(), cue, list);
	}

	@XmlElement(name = "cue")
	public Cue getCue() {
		return cue;
	}
	
	public void setCue(Cue c) {
		cue = c;
	}
	
	@XmlTransient
	public CueList getList() {
		return list;
	}
	
	public void setList(CueList list) {
		this.list = list;
		updateBehavior();
	}

	@XmlElement(name = "callNextBahavior")
	public ListItemCallNextBehavior getCallNextBehavior() {
		return callNextBehavior.getValue();
	}

	public void setCallNextBehavior(ListItemCallNextBehavior value) {
		callNextBehavior.setValue(value);
	}

	public SimpleObjectProperty<ListItemCallNextBehavior> callNextBehavior() {
		return callNextBehavior;
	}
	
	
}
