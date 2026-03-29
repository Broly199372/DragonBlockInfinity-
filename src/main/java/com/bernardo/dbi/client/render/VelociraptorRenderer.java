package com.bernardo.dbi.client.render;

import com.bernardo.dbi.entity.VelociraptorEntity;
import com.bernardo.dbi.client.model.VelociraptorModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class VelociraptorRenderer extends MobEntityRenderer<VelociraptorEntity, VelociraptorModel<VelociraptorEntity>> {

    private static final Identifier TEXTURE = new Identifier("dragonblockinfinity", "textures/npc/dinosauro1.png");

    public VelociraptorRenderer(EntityRendererFactory.Context context) {
        super(context, new VelociraptorModel<>(context.getPart(VelociraptorModel.LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(VelociraptorEntity entity) {
        return TEXTURE;
    }
}