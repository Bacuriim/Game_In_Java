package com.br.einstein.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class FontParameters {

    private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("MadHomie-K7RPA.ttf"));
    private BitmapFont font50;
    private BitmapFont font100;
    private BitmapFont fontP;
    private Label.LabelStyle font;
    private Label.LabelStyle labelStyle100;
    private Label.LabelStyle labelStyle50;
    private Label.LabelStyle labelStyle50R;
    private Label.LabelStyle labelStyle50B;
    private TextButton.TextButtonStyle buttonStyle;
    private AssetManager manager;

    public FontParameters() {
        // Setting loader, AssetManager and parameters
        manager = new AssetManager();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter parms50 = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        parms50.fontFileName = "MadHomie-K7RPA.ttf";

        parms50.fontParameters.size = 50;
        parms50.fontParameters.borderWidth = 2;
        parms50.fontParameters.borderColor = Color.BLACK;


        manager.load("MadHomie-K7RPA.ttf", BitmapFont.class, parms50);
        manager.finishLoading();

        font50 = manager.get("MadHomie-K7RPA.ttf", BitmapFont.class);

        font100 = manager.get("MadHomie-K7RPA.ttf", BitmapFont.class);


        fontP = new BitmapFont();

        //Setting styles
        labelStyle100 = new Label.LabelStyle();
        labelStyle100.font = font100;
        labelStyle100.fontColor = Color.YELLOW;

        labelStyle50 = new Label.LabelStyle();
        labelStyle50.font = font50;
        labelStyle50.fontColor = Color.YELLOW;

        labelStyle50R = new Label.LabelStyle();
        labelStyle50R.font = font50;
        labelStyle50R.fontColor = Color.RED;

        labelStyle50B = new Label.LabelStyle();
        labelStyle50B.font = font50;
        labelStyle50B.fontColor = Color.BLUE;

        font = new Label.LabelStyle();
        font.font = fontP;
        font.fontColor = Color.WHITE;

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
    public Label.LabelStyle getLabelStyle50R() {
        return this.labelStyle50R;
    }
    public Label.LabelStyle getLabelStyle50B() {
        return this.labelStyle50B;
    }
    public Label.LabelStyle getFont() {
        return this.font;
    }

    public TextButton.TextButtonStyle getButtonStyle() {
        return this.buttonStyle;
    }

    public void dispose() {
        generator.dispose();
        manager.dispose();
    }
}
