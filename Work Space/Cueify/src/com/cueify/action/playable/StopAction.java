package com.cueify.action.playable;

public class StopAction extends PlayableAction {

	@Override
	public void doAction() {
		this.getTarget().stop();
	}

}