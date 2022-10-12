package nz.ac.vuw.ecs.swen225.gp22.domain;

import gameImages.Img;

import java.awt.*;

/**
 * The Info tile displays text when Chip is standing on it.
 */
public class Info extends FreeTile {
	public String infoText;
	public Info(String infoText) {
		this.infoText = infoText;
	}
	public Info() {
		this.infoText = "";
	}

	public String toString() {
		return "I";
	}

	public Image getImage() {
		return Img.Infotile.image;
	}
}
