package com.bernardo.dbi.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import com.bernardo.dbi.screen.widget.BtnArrowRightSmall;
import com.bernardo.dbi.screen.widget.BtnArrowLeftSmall;
import com.bernardo.dbi.screen.widget.IconButton;
import com.bernardo.dbi.screen.widget.BtnCloseXLarge;

public class CaracterScreen extends Screen {

    private static final Identifier MENU = new Identifier("dragonblockinfinity", "textures/gui/menu.png");
    private static final int TEX_W = 512;
    private static final int TEX_H = 512;
    private static final int IMG_W = 510;
    private static final int IMG_H = 318;

    private int btnX, btnY;

    private int guiLeft, guiTop, menuW, menuH;

    public CaracterScreen() {
        super(Text.literal("DragonBlockInfinity Menu"));
    }

    @Override
    protected void init() {
        super.init();

        float ratio = Math.min((this.width * 0.75f) / IMG_W, (this.height * 0.75f) / IMG_H);
        menuW = (int) (IMG_W * ratio);
        menuH = (int) (IMG_H * ratio);
        guiLeft = (this.width - menuW) / 2;
        guiTop = (this.height - menuH) / 2;

        // Adicionar botão com textura
        BtnCloseXLarge btnCloseXLarge = new BtnCloseXLarge();
        int btnX = guiLeft + (int)(menuW * 0.8f); // Posição relativa ao menu
        int btnY = guiTop + (int)(menuH * 0.9f);
        
        btnCloseXLarge.place(btnX, btnY, ratio);
        btnCloseXLarge.setOnPress(() -> {
            // Ação do botão, por exemplo, fechar a tela
            this.close();
        });
        this.addDrawableChild(btnCloseXLarge);
       

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);

        context.getMatrices().push();
        context.getMatrices().translate(guiLeft, guiTop, 0);
        context.getMatrices().scale((float) menuW / IMG_W, (float) menuH / IMG_H, 1f);
        context.drawTexture(MENU, 0, 0, 0, 0, IMG_W, IMG_H, TEX_W, TEX_H);
        context.getMatrices().pop();

        PlayerEntity player = this.client.player;
        if (player != null) {
            int px = guiLeft + menuW / 4;
            int py = guiTop + menuH * 4 / 7;
            int scale = (int) (Math.min(menuW, menuH) * 0.25f);
            InventoryScreen.drawEntity(context, px, py, scale, (float) (px - mouseX), (float) (py - 10 - mouseY), player);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    

    
}
