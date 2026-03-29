package com.bernardo.dbi.client.render;

import com.bernardo.dbi.entity.Dinossauro1Entity;
import com.bernardo.dbi.client.model.Dinossauro1Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class Dinossauro1Renderer extends MobEntityRenderer<Dinossauro1Entity, Dinossauro1Model<Dinossauro1Entity>> {

    private static final Identifier TEXTURE = new Identifier("dragonblockinfinity", "textures/npc/dinosauro1.png");

    public Dinossauro1Renderer(EntityRendererFactory.Context context) {
        super(context, new Dinossauro1Model<>(context.getPart(Dinossauro1Model.LAYER)), 0.6f);
    }

    @Override
    public Identifier getTexture(Dinossauro1Entity entity) {
        return TEXTURE;
    }
}