package com.bernardo.dbi.screen;

import com.bernardo.dbi.player.DBIPlayerData;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class CaracterScreen extends Screen {

    private static final Identifier MENU = new Identifier("dragonblockinfinity", "textures/gui/menu.png");
    private static final int TEX_W = 512;
    private static final int TEX_H = 512;
    private static final int IMG_W = 510;
    private static final int IMG_H = 318;

    // Cores para melhor visual
    private static final int COLOR_TITLE = 0xFFD700;        // Ouro
    private static final int COLOR_LABEL = 0xE8E8E8;        // Branco off
    private static final int COLOR_VALUE = 0xFFFF00;        // Amarelo brilhante

    private int guiLeft, guiTop, menuW, menuH;

    private static final String[] OPTIONS = {
        "race", "age", "hair", "hair_color", "eyes", "nose", "mouth"
    };

    private static final String[] OPTION_LABELS = {
        "Raça", "Idade", "Cabelo", "Cor do Cabelo", "Olhos", "Nariz", "Boca"
    };

    private final net.minecraft.client.gui.widget.ButtonWidget[] btnLeft  = new net.minecraft.client.gui.widget.ButtonWidget[OPTIONS.length];
    private final net.minecraft.client.gui.widget.ButtonWidget[] btnRight = new net.minecraft.client.gui.widget.ButtonWidget[OPTIONS.length];

    public CaracterScreen() {
        super(Text.literal("Criação de Personagem"));
    }

    @Override
    protected void init() {
        super.init();

        PlayerEntity player = this.client.player;
        if (player != null) {
            applyDefaultAppearance(player);
        }

        float ratio = Math.min(
            (this.width  * 0.75f) / IMG_W,
            (this.height * 0.75f) / IMG_H
        );
        menuW   = (int)(IMG_W * ratio);
        menuH   = (int)(IMG_H * ratio);
        guiLeft = (this.width  - menuW) / 2;
        guiTop  = (this.height - menuH) / 2;

        int panelX  = guiLeft + (int)(menuW * 0.55f);
        int panelW  = (int)(menuW * 0.42f);
        int startY  = guiTop  + (int)(menuH * 0.12f);
        int spacing = (int)((menuH * 0.75f) / OPTIONS.length);
        int btnW    = Math.max(12, (int)(menuH * 0.08f));
        int btnH    = Math.max(12, (int)(menuH * 0.08f));
        int lx      = panelX + 4;
        int rx      = panelX + panelW - btnW - 4;

        for (int i = 0; i < OPTIONS.length; i++) {
            final int idx = i;
            int rowY = startY + i * spacing + (spacing - btnH) / 2;

            btnLeft[i] = net.minecraft.client.gui.widget.ButtonWidget.builder(
                Text.literal("◀"), b -> cycleOption(OPTIONS[idx], -1)
            ).dimensions(lx, rowY, btnW, btnH).build();

            btnRight[i] = net.minecraft.client.gui.widget.ButtonWidget.builder(
                Text.literal("▶"), b -> cycleOption(OPTIONS[idx], +1)
            ).dimensions(rx, rowY, btnW, btnH).build();

            this.addDrawableChild(btnLeft[i]);
            this.addDrawableChild(btnRight[i]);
        }
    }

    private void cycleOption(String option, int dir) {
        PlayerEntity player = this.client.player;
        if (player == null) return;

        List<String> opts = AppearanceManager.getOptionsFor(option, DBIPlayerData.getRace(player).name());
        if (opts.isEmpty()) return;

        int current  = AppearanceManager.getCurrentValue(player, option);
        int newValue = (current + dir + opts.size()) % opts.size();
        AppearanceManager.updateAppearance(player, option, newValue);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        // Fundo menu.png escalado com ratio correto
        context.getMatrices().push();
        context.getMatrices().translate(guiLeft, guiTop, 0);
        context.getMatrices().scale((float) menuW / IMG_W, (float) menuH / IMG_H, 1f);
        context.drawTexture(MENU, 0, 0, 0, 0, IMG_W, IMG_H, TEX_W, TEX_H);
        context.getMatrices().pop();

        // Player model - renderização centralizada e proporcionada
        PlayerEntity player = this.client.player;
        if (player != null) {
            // Posição do modelo (centro-esquerda)
            int px    = guiLeft + (int)(menuW * 0.22f);
            int py    = guiTop  + (int)(menuH * 0.62f);
            
            // Escala adaptada à altura da tela
            int scale = (int)(Math.min(menuW, menuH) * 0.28f);
            
            InventoryScreen.drawEntity(
                context, px, py, scale,
                (float)(px - mouseX),
                (float)(py - 10 - mouseY),
                player
            );
        }

        // Título
        context.drawCenteredTextWithShadow(
            this.textRenderer,
            Text.literal("✦ Criação de Personagem ✦"),
            guiLeft + menuW / 2,
            guiTop + 8,
            COLOR_TITLE
        );

        // Painel de opções
        renderOptionsPanel(context, player);

        super.render(context, mouseX, mouseY, delta);
    }

    private void renderOptionsPanel(DrawContext context, PlayerEntity player) {
        if (player == null) return;

        // Sugestão de configurações automáticas (usadas por padrão)
        context.drawTextWithShadow(this.textRenderer, Text.literal("Recomendações: use os defaults e ajuste conforme quiser"), guiLeft + 10, guiTop + 30, COLOR_VALUE);

        int panelX  = guiLeft + (int)(menuW * 0.55f);
        int panelW  = (int)(menuW * 0.42f);
        int startY  = guiTop  + (int)(menuH * 0.12f);
        int spacing = (int)((menuH * 0.75f) / OPTIONS.length);

        float ratio = Math.min(
            (this.width  * 0.75f) / IMG_W,
            (this.height * 0.75f) / IMG_H
        );
        float labelScale = Math.max(0.7f, Math.min(0.85f, ratio * 0.9f));
        float valueScale = Math.max(0.75f, Math.min(1.0f, ratio * 1.15f));

        String race = DBIPlayerData.getRace(player).name();

        for (int i = 0; i < OPTIONS.length; i++) {
            int rowY = startY + i * spacing;
            int labelY = rowY + 4;
            int valueY = rowY + 18;

            // Rótulo da opção
            context.getMatrices().push();
            context.getMatrices().translate(panelX + 8, labelY, 0);
            context.getMatrices().scale(labelScale, labelScale, 1f);
            context.drawTextWithShadow(this.textRenderer, OPTION_LABELS[i], 0, 0, COLOR_LABEL);
            context.getMatrices().pop();

            // Valor atual
            List<String> opts = AppearanceManager.getDisplayOptionsFor(OPTIONS[i], race);
            int val = AppearanceManager.getCurrentValue(player, OPTIONS[i]);
            if (!opts.isEmpty()) val = (val % opts.size() + opts.size()) % opts.size();
            String display = opts.isEmpty() ? "—" : opts.get(val);

            int centerX = panelX + panelW / 2;
            context.getMatrices().push();
            context.getMatrices().translate(centerX, valueY, 0);
            context.getMatrices().scale(valueScale, valueScale, 1f);
            context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(display), 0, 0, COLOR_VALUE);
            context.getMatrices().pop();
        }
    }

    private void applyDefaultAppearance(PlayerEntity player) {
        // Race / Age padrão
        if (DBIPlayerData.getRace(player) == null) {
            DBIPlayerData.setRace(player, com.bernardo.dbi.player.Race.SAIYAN);
        }
        if (DBIPlayerData.getAge(player) == null) {
            DBIPlayerData.setAge(player, com.bernardo.dbi.player.AgeStage.ADULT);
        }

        // Texturas padrão (se ainda não configuradas)
        if (DBIPlayerData.getHairTexture(player) == null) {
            DBIPlayerData.setHairTexture(player, new Identifier("dragonblockinfinity", "textures/hairs/hair_goku_overlay.png"));
        }
        if (DBIPlayerData.getEyeTexture(player) == null) {
            DBIPlayerData.setEyeTexture(player, new Identifier("dragonblockinfinity", "textures/eyes/eye1.png"));
        }
        if (DBIPlayerData.getNoseTexture(player) == null) {
            DBIPlayerData.setNoseTexture(player, new Identifier("dragonblockinfinity", "textures/nose/nose1.png"));
        }
        if (DBIPlayerData.getMouthTexture(player) == null) {
            DBIPlayerData.setMouthTexture(player, new Identifier("dragonblockinfinity", "textures/mouth/mouth1.png"));
        }
    }

    @Override
    public boolean shouldPause() { return false; }
}
