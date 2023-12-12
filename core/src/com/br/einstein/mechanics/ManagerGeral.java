package com.br.einstein.mechanics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3;
import com.badlogic.gdx.graphics.Texture;
import org.lwjgl.system.CallbackI;

public class ManagerGeral {
    public AssetManager manager = new AssetManager();

    // Fundos
    private final String castelao = "assets/backgrounds/gameBackgrounds/castelao.png";
    private final String estacionamento = "assets/backgrounds/gameBackgrounds/estacionamento.png";
    private final String menuImage = "assets/backgrounds/mainMenuImage.png";
    private final String pause = "assets/backgrounds/pause.png";

    // Sons
    private final String fightMusic = "assets/musics/Fight_Music.mp3";
    private final String menuMusic = "assets/musics/menuMusic.mp3";
    private final String selectionMusic = "assets/musics/Selection_Screen.mp3";

    // barras de hp
    private final String dark = "assets/hpBar/darkbrowngradient.png";
    private final String light = "assets/hpBar/lightbrowngradient.png";
    private final String red = "assets/hpBar/redgradient.jpg";

    // Iracema
    private final String iChute = "assets/IracemaSprites/iracema_chutando.png";
    private final String iHead = "assets/IracemaSprites/Iracema_head.png";
    private final String iIdle = "assets/IracemaSprites/Iracema_parada_D.png";
    private final String iPulo = "assets/IracemaSprites/Iracema_pulo_D.png";
    private final String iSoco = "assets/IracemaSprites/Iracema_soco_animation_D.png";
    private final String iAndar = "assets/IracemaSprites/Iracema_walking_animation.png";

    // Loira
    private final String lChute = "assets/LoiraSprites/Loira_Chutando_D.png";
    private final String lHead = "assets/LoiraSprites/Loira_head.png";
    private final String lIdle = "assets/LoiraSprites/Loira_parada_D.png";
    private final String lPulo = "assets/LoiraSprites/Loira_pulo.png";
    private final String lSoco = "assets/LoiraSprites/Loira_Socando_D.png";
    private final String lAndar = "assets/LoiraSprites/Loira_Andando_D.png";

    // Sarto
    private final String sChute = "assets/SartoSprites/Sarto_Chutando.png";
    private final String sHead = "assets/SartoSprites/Sarto_head.png";
    private final String sIdle = "assets/SartoSprites/Sarto_Parado.png";
    private final String sPulo = "assets/SartoSprites/Sarto_Pulando_D.png";
    private final String sSoco = "assets/SartoSprites/Sarto_Socando.png";
    private final String sAndar = "assets/SartoSprites/Sarto_Andando.png";

    public void loadFundos() {
        manager.load(castelao, Texture.class);
        manager.load(estacionamento, Texture.class);
        manager.load(menuImage, Texture.class);
        manager.load(pause, Texture.class);
    }

    public void loadSons() {
        manager.load(fightMusic, Music.class);
        manager.load(menuMusic, Music.class);
        manager.load(selectionMusic, Music.class);
    }

    public void loadHp() {
        manager.load(dark, Texture.class);
        manager.load(light, Texture.class);
        manager.load(red, Texture.class);
    }

    public void loadIracema() {
        manager.load(iChute, Texture.class);
        manager.load(iHead, Texture.class);
        manager.load(iIdle, Texture.class);
        manager.load(iPulo, Texture.class);
        manager.load(iSoco, Texture.class);
        manager.load(iAndar, Texture.class);
    }

    public void loadLoira() {
        manager.load(lChute, Texture.class);
        manager.load(lHead, Texture.class);
        manager.load(lIdle, Texture.class);
        manager.load(lPulo, Texture.class);
        manager.load(lSoco, Texture.class);
        manager.load(lAndar, Texture.class);
    }

    public void loadSarto() {
        manager.load(sChute, Texture.class);
        manager.load(sHead, Texture.class);
        manager.load(sIdle, Texture.class);
        manager.load(sPulo, Texture.class);
        manager.load(sSoco, Texture.class);
        manager.load(sAndar, Texture.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
