package com.bernardo.dbi.client.render;

import com.bernardo.dbi.entity.VelociraptorEntity;
import com.bernardo.dbi.client.model.Dinossauro1Model;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class VelociraptorRenderer extends MobEntityRenderer<VelociraptorEntity, Dinossauro1Model<VelociraptorEntity>> {

    private static final Identifier TEXTURE = new Identifier("dragonblockinfinity", "textures/npc/velociraptor.png");

    public VelociraptorRenderer(EntityRendererFactory.Context context) {
        super(context, new Dinossauro1Model<>(context.getPart(Dinossauro1Model.LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(VelociraptorEntity entity) {
        return TEXTURE;
    }
}