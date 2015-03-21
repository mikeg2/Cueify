package com.cueify.cuelist.behavior;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.undo.Undoable;

public class AddListenerUndoable<T> implements Undoable {
	private boolean selfDestruct = false;
	private final ChangeListener<T> toRemove;
	private final ObservableValue<T> value;
	
	public AddListenerUndoable(ChangeListener<T> toRemove, ObservableValue<T> value) {
		this.toRemove = toRemove;
		this.value = value;
	}

	@Override
	public void undo() {
		if (selfDestruct)
			return;
		value.removeListener(toRemove);
		selfDestruct = true;
	}

}
