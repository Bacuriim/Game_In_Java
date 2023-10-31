package com.br.einstein.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.br.einstein.screen.ScreenManager;

public class Hud {
    public Stage stage;
    public Viewport viewport;

    Label play;
    Label quit;
    Label logo;

    public Hud(SpriteBatch sb) {
        viewport = new FitViewport(ScreenManager.V_WIDTH, ScreenManager.V_HEIGTH, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        logo = new Label("Street Fortal", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(logo).expandX().padTop(10);

        stage.addActor(table);
    }
}
