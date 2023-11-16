package com.br.einstein.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FontParameters {

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("MadHomie-K7RPA.ttf"));
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter50 = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter100 = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private BitmapFont font50;
    private BitmapFont font100;
    private Label.LabelStyle labelStyle100;
    private Label.LabelStyle labelStyle50;
    private TextButton.TextButtonStyle buttonStyle;

    public FontParameters() {
        //Setting parameters
        parameter50.size = 50;
        parameter50.borderWidth = 2;
        parameter50.borderColor = Color.BLACK;
        font50 = generator.generateFont(parameter50);

        parameter100.size = 100;
        parameter100.borderWidth = 5;
        parameter100.borderColor = Color.BLACK;
        font100 = generator.generateFont(parameter100);

        //Setting styles
        labelStyle100 = new Label.LabelStyle();
        labelStyle100.font = font100;
        labelStyle100.fontColor = Color.YELLOW;

        labelStyle50 = new Label.LabelStyle();
        labelStyle50.font = font50;
        labelStyle50.fontColor = Color.YELLOW;

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font50;
        buttonStyle.fontColor = Color.YELLOW;
        buttonStyle.overFontColor = Color.ORANGE;
        buttonStyle.downFontColor = Color.RED;
    }

    public Label.LabelStyle getLabelStyle100() {
        return this.labelStyle100;
    }

    public Label.LabelStyle getLabelStyle50() {
        return this.labelStyle50;
    }

    public TextButton.TextButtonStyle getButtonStyle() {
        return this.buttonStyle;
    }
}
