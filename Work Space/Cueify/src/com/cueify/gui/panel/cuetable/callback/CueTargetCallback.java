package com.cueify.gui.panel.cuetable.callback;

import java.net.URI;
import com.cueify.cue.MediaCue;
import com.cueify.cuelist.CueListItem;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CueTargetCallback implements Callback<TableColumn.CellDataFeatures<CueListItem,String>, ObservableValue<String>> {

	@Override
	public ObservableValue<String> call(CellDataFeatures<CueListItem, String> param) {
		ObservableValue<String> toReturn = null;
		if (param.getValue().getCue() instanceof MediaCue) {
			final MediaCue p = (MediaCue) param.getValue().getCue();
			toReturn = new StringBinding() {
				
				{
					super.bind(p.getOutput().source());
				}
				
				@Override
				protected String computeValue() {
					if(p.getOutput().getSource() == null) {
						return "No Target";
					}
					URI sourceURI =  p.getOutput().getSource().toURI();
					return getFileNameFromURI(sourceURI);
				}
			};
		} 
		return toReturn;
	}

	protected static String getFileNameFromURI(URI sourceURI) {
		String url = sourceURI.toString();
		return url.substring( url.lastIndexOf('/')+1, url.length());
	}

}
