package net.scandroidz.widgets.flickstick;

import java.util.EventObject;

import net.scandroidz.enums.BattleEnums.MoveSelection;

//Generated when the Flick Stick is clicked
public class FlickClickEvent extends EventObject {

	public MoveSelection selection;
	
	public FlickClickEvent(MoveSelection selection) {
		super(selection);
		this.selection = selection;
	}

}
