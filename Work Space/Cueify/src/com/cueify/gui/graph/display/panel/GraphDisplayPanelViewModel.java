package com.cueify.gui.graph.display.panel;

import com.cueify.gui.graph.display.AnyGraphDisplay;
import com.cueify.gui.panel.BasicGUIViewModel;

public class GraphDisplayPanelViewModel extends BasicGUIViewModel<GraphDisplayPanelModel, GraphDisplayPanel> {
	private GraphDisplayPanelModel model;

	@Override
	public void linkControllerToView(GraphDisplayPanel panel) {
		panel.graphDisplay = new AnyGraphDisplay(model.graph);
		panel.setCenter(panel.graphDisplay);
		
	}

}
