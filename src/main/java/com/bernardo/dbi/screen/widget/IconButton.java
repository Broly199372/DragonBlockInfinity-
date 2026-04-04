package com.bernardo.dbi.screen.widget;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class IconButton extends ClickableWidget {

    private static final Identifier ICONS = new Identifier("dragonblockinfinity", "textures/gui/icons_btn.png");
    private static final int SHEET_SIZE = 256;

    protected final int wNormal, hNormal;
    protected final int uNormal, vNormal;
    protected final int wPressed, hPressed;
    protected final int uPressed, vPressed;

    private float scale = 1.0f;
    private boolean pressed = false;
    private boolean locked = false;
    private Runnable onPress = null;

    public IconButton(
        int wNormal, int hNormal,
        int uNormal, int vNormal,
        int wPressed, int hPressed,
        int uPressed, int vPressed
    ) {
        super(0, 0, wNormal, hNormal, Text.empty());
        this.wNormal  = wNormal;
        this.hNormal  = hNormal;
        this.uNormal  = uNormal;
        this.vNormal  = vNormal;
        this.wPressed = wPressed;
        this.hPressed = hPressed;
        this.uPressed = uPressed;
        this.vPressed = vPressed;
    }

    public void place(int x, int y, float scale) {
        this.scale  = scale;
        this.setX(x);
        this.setY(y);
        this.width  = Math.max(1, Math.round(wNormal * scale));
        this.height = Math.max(1, Math.round(hNormal * scale));
    }

    public void setLocked(boolean locked) { this.locked = locked; }
    public boolean isLocked() { return locked; }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int u      = pressed ? uPressed : uNormal;
        int v      = pressed ? vPressed : vNormal;
        int srcW   = pressed ? wPressed : wNormal;
        int srcH   = pressed ? hPressed : hNormal;
        int drawW  = Math.max(1, Math.round(srcW * scale));
        int drawH  = Math.max(1, Math.round(srcH * scale));

        // Usa a assinatura completa: x, y, u, v, width, height, regionW, regionH, textureW, textureH
        context.drawTexture(ICONS, this.getX(), this.getY(), drawW, drawH, u, v, srcW, srcH, SHEET_SIZE, SHEET_SIZE);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (locked) return false;
        if (this.isHovered()) pressed = true;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (locked) return false;
        pressed = false;
        if (this.isHovered() && onPress != null) {
            onPress.run();
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public void setOnPress(Runnable onPress) { this.onPress = onPress; }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {}
}
