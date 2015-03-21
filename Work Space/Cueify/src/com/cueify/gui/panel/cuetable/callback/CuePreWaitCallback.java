package com.cueify.gui.panel.cuetable.callback;

import com.cueify.cuelist.CueListItem;
import com.cueify.timeline.ActiveTimeline;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CuePreWaitCallback implements Callback<TableColumn.CellDataFeatures<CueListItem,String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(final CellDataFeatures<CueListItem, String> param) {
		final ActiveTimeline preWait = param.getValue().getCue().getCueTimeline().getPreWait();
		return new StringBinding() {
			{
				super.bind(param.getValue().getCue().getCueTimeline().getPreWait().playhead());
				super.bind(param.getValue().getCue().getCueTimeline().getPreWait().waitTime());
			}

			@Override
			protected String computeValue() {
				try {
					return preWait.getAbsoluteDuration().subtract(preWait.getPlayhead()).toString();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
					return "";
				}

			}
		};
	}

}
