package com.cueify.action.playable;


public class PlayAction extends PlayableAction {

	@Override
	public void doAction() {
		this.getTarget().play();
	}

}
