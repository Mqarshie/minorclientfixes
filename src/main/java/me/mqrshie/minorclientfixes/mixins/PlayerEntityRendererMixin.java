package me.mqrshie.minorclientfixes.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntityRenderer.class, priority = 1100)
public class PlayerEntityRendererMixin {

    @Inject(method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;CROSSBOW_HOLD:Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;"), cancellable = true)
    private static void setArmPoseIfOffHand(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (hand == Hand.MAIN_HAND || !player.isMainPlayer() || !MinecraftClient.getInstance().options.getPerspective().isFirstPerson()) {
            return;
        }

        cir.setReturnValue(BipedEntityModel.ArmPose.ITEM);
    }

}