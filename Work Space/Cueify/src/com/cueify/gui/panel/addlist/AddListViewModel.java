package com.cueify.gui.panel.addlist;

import com.cueify.gui.panel.*;

public class AddListViewModel extends BasicGUIViewModel<AddListModel, AddList>{
	
	@Override
	public void linkControllerToView(AddList panel) {
		panel.listView.setItems(AddListModel.cueGUIs);
	}

}
