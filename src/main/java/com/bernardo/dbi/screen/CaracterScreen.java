package com.bernardo.dbi.screen;

import com.bernardo.dbi.network.DBINetwork;
import com.bernardo.dbi.network.PacketIds;
import com.bernardo.dbi.player.DBIPlayerData;
import com.bernardo.dbi.screen.widget.BtnArrowLeftSmall;
import com.bernardo.dbi.screen.widget.BtnArrowRightSmall;
import com.bernardo.dbi.screen.widget.BtnCloseXLarge;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
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

    private static final List<String> HAIR_OPTIONS = List.of(
        "",
        "textures/hairs/hair_vegeta.png",
        "textures/hairs/hair_goku.png",
        "textures/hairs/hair_trunks.png"
    );

    private static final List<String> HAIR_LABELS = List.of(
        "Nenhum",
        "hair2",
        "hair1",
        "hair3"
    );

    private int guiLeft, guiTop, menuW, menuH;
    private float ratio;
    private int hairIndex = 0;

    public CaracterScreen() {
        super(Text.literal("DragonBlockInfinity Menu"));
    }

    @Override
    protected void init() {
        super.init();

        ratio = Math.min((this.width * 0.75f) / IMG_W, (this.height * 0.75f) / IMG_H);
        menuW = (int) (IMG_W * ratio);
        menuH = (int) (IMG_H * ratio);
        guiLeft = (this.width - menuW) / 2;
        guiTop  = (this.height - menuH) / 2;

        PlayerEntity player = this.client.player;
        if (player != null) {
            Identifier current = DBIPlayerData.getHairTexture(player);
            if (current == null) {
                hairIndex = 0;
            } else {
                String path = current.getPath();
                for (int i = 1; i < HAIR_OPTIONS.size(); i++) {
                    if (HAIR_OPTIONS.get(i).equals(path)) {
                        hairIndex = i;
                        break;
                    }
                }
            }
        }

        int rowY   = guiTop  + (int)(menuH * 0.20f);
        int labelX = guiLeft + (int)(menuW * 0.58f);
        int arrowGap = (int)(20 * ratio);

        BtnArrowLeftSmall  btnLeft  = new BtnArrowLeftSmall();
        BtnArrowRightSmall btnRight = new BtnArrowRightSmall();

        btnLeft.place(labelX - arrowGap - (int)(15 * ratio), rowY, ratio);
        btnLeft.setOnPress(() -> {
            hairIndex = (hairIndex - 1 + HAIR_OPTIONS.size()) % HAIR_OPTIONS.size();
            applyHair(player);
        });

        btnRight.place(labelX + (int)(50 * ratio), rowY, ratio);
        btnRight.setOnPress(() -> {
            hairIndex = (hairIndex + 1) % HAIR_OPTIONS.size();
            applyHair(player);
        });

        this.addDrawableChild(btnLeft);
        this.addDrawableChild(btnRight);

        BtnCloseXLarge btnClose = new BtnCloseXLarge();
        btnClose.place(guiLeft + (int)(menuW * 0.88f), guiTop + (int)(menuH * 0.88f), ratio);
        btnClose.setOnPress(this::close);
        this.addDrawableChild(btnClose);
    }

    private void applyHair(PlayerEntity player) {
        String path = HAIR_OPTIONS.get(hairIndex);
        if (player != null) {
            if (path.isEmpty()) {
                DBIPlayerData.setHairTexture(player, null);
            } else {
                DBIPlayerData.setHairTexture(player, new Identifier("dragonblockinfinity", path));
            }
        }
        ClientPlayNetworking.send(PacketIds.SET_HAIR, DBINetwork.buildSetHairPacket(path));
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
            int px    = guiLeft + menuW / 4;
            int py    = guiTop  + menuH * 4 / 7;
            int scale = (int)(Math.min(menuW, menuH) * 0.25f);
            InventoryScreen.drawEntity(context, px, py, scale,
                (float)(px - mouseX), (float)(py - 10 - mouseY), player);
        }

        int labelX = guiLeft + (int)(menuW * 0.58f);
        int rowY   = guiTop  + (int)(menuH * 0.20f);
        context.drawText(this.textRenderer,
            Text.literal("Cabelo: " + HAIR_LABELS.get(hairIndex)),
            labelX, rowY + 3, 0xFFFFFF, true);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
