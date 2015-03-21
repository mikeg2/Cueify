package com.cueify.gui.panel.cuetable.callback;

import com.cueify.cuelist.CueListItem;
import com.cueify.timeline.ActiveTimeline;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CueContentCallback implements Callback<TableColumn.CellDataFeatures<CueListItem,String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(final CellDataFeatures<CueListItem, String> param) {
		final ActiveTimeline contentPoint = param.getValue().getCue().getCueTimeline().getContent();
		return new StringBinding() {
			{
				super.bind(param.getValue().getCue().getCueTimeline().getContent().playhead());
				super.bind(param.getValue().getCue().getCueTimeline().getContent().absoluteDuration());
			}
			@Override
			protected String computeValue() {
				return contentPoint.getAbsoluteDuration().subtract(contentPoint.getPlayhead()).toString();
			}
		};
	}

}
