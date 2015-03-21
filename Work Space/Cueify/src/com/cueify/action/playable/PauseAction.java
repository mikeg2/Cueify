package com.cueify.action.playable;

public class PauseAction extends PlayableAction {

	@Override
	public void doAction() {
		this.getTarget().pause();
	}

}
