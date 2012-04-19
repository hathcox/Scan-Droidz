package net.scandroidz.widgets.flickstick;

import java.util.EventObject;

import net.scandroidz.enums.BattleEnums.MoveSelection;

//Generated when the Flick Stick is released in one of its four quadrants
public class FlickReleaseEvent extends EventObject{

	public MoveSelection selection;
	
	public FlickReleaseEvent(MoveSelection selection) {
		super(selection);
		this.selection = selection;
	}

}
