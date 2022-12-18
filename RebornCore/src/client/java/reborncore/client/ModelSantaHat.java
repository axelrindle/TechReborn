/*
 * This file is part of RebornCore, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2021 TeamReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package reborncore.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Arrays;
import java.util.Collections;

public class ModelSantaHat extends EntityModel<AbstractClientPlayerEntity> {
	private final ModelPart hatBand1;
	private final ModelPart hatBand2;
	private final ModelPart hatBand3;
	private final ModelPart hatBand4;
	private final ModelPart hatBase1;
	private final ModelPart hatBand5;
	private final ModelPart hatBand6;
	private final ModelPart hatBase2;
	private final ModelPart hatExtension1;
	private final ModelPart hatExtension2;
	private final ModelPart hatExtension3;
	private final ModelPart hatExtension4;
	private final ModelPart hatBall1;
	private final ModelPart hatBall2;
	private final ModelPart hatBall3;
	private final ModelPart hatBall4;
	private final ModelPart hatBall5;
	private final ModelPart hatBall6;

	public ModelSantaHat() {
		ModelPart.Cuboid[] hatBand1Cuboids = {
			new ModelPart.Cuboid(0, 32, -4F, -8F, -5F, 8F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand1 = new ModelPart(Arrays.asList(hatBand1Cuboids), Collections.emptyMap());
		hatBand1.setPivot(0F, 0F, 0F);
		setRotation(hatBand1, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBand2Cuboids = {
			new ModelPart.Cuboid(0, 32, -4F, -8F, 4F, 8F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand2 = new ModelPart(Arrays.asList(hatBand2Cuboids), Collections.emptyMap());
		hatBand2.setPivot(0F, 0F, 0F);
		setRotation(hatBand2, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBand3Cuboids = {
			new ModelPart.Cuboid(0, 34, -5F, -8F, -4F, 1F, 1F, 8F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand3 = new ModelPart(Arrays.asList(hatBand3Cuboids), Collections.emptyMap());
		hatBand3.setPivot(0F, 0F, 0F);
		setRotation(hatBand3, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBand4Cuboids = {
			new ModelPart.Cuboid(0, 34, 4F, -8F, -4F, 1F, 1F, 8F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand4 = new ModelPart(Arrays.asList(hatBand4Cuboids), Collections.emptyMap());
		hatBand4.setPivot(0F, 0F, 0F);
		setRotation(hatBand4, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBase1Cuboids = {
			new ModelPart.Cuboid(0, 43, -4F, -9F, -4F, 8F, 1F, 8F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBase1 = new ModelPart(Arrays.asList(hatBase1Cuboids), Collections.emptyMap());
		hatBase1.setPivot(0F, 0F, 0F);
		setRotation(hatBase1, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBand5Cuboids = {
			new ModelPart.Cuboid(18, 41, 0F, -7F, -5F, 4F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand5 = new ModelPart(Arrays.asList(hatBand5Cuboids), Collections.emptyMap());
		hatBand5.setPivot(0F, 0F, 0F);
		setRotation(hatBand5, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBand6Cuboids = {
			new ModelPart.Cuboid(18, 41, -4F, -7F, 0F, 4F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBand6 = new ModelPart(Arrays.asList(hatBand6Cuboids), Collections.emptyMap());
		hatBand6.setPivot(0F, 0F, 4F);
		setRotation(hatBand6, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBase2Cuboids = {
			new ModelPart.Cuboid(18, 34, -3F, -10F, -3F, 6F, 1F, 6F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBase2 = new ModelPart(Arrays.asList(hatBase2Cuboids), Collections.emptyMap());
		hatBase2.setPivot(0F, 0F, 0F);
		setRotation(hatBase2, 0F, 0.1115358F, 0F);

		ModelPart.Cuboid[] hatExtension1Cuboids = {
			new ModelPart.Cuboid(0, 52, -3F, -11F, -2F, 4F, 2F, 4F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatExtension1 = new ModelPart(Arrays.asList(hatExtension1Cuboids), Collections.emptyMap());
		hatExtension1.setPivot(0F, 0F, 0F);
		setRotation(hatExtension1, 0F, -0.0371786F, 0.0743572F);

		ModelPart.Cuboid[] hatExtension2Cuboids = {
			new ModelPart.Cuboid(16, 52, -2.4F, -12F, -1.5F, 3F, 2F, 3F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatExtension2 = new ModelPart(Arrays.asList(hatExtension2Cuboids), Collections.emptyMap());
		hatExtension2.setPivot(0F, 0F, 0F);
		setRotation(hatExtension2, 0F, 0.0743572F, 0.0743572F);

		ModelPart.Cuboid[] hatExtension3Cuboids = {
			new ModelPart.Cuboid(28, 52, -3.5F, -13F, -1F, 2F, 2F, 2F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatExtension3 = new ModelPart(Arrays.asList(hatExtension3Cuboids), Collections.emptyMap());
		hatExtension3.setPivot(0F, 0F, 0F);
		setRotation(hatExtension3, 0F, 0F, 0.2230717F);

		ModelPart.Cuboid[] hatExtension4Cuboids = {
			new ModelPart.Cuboid(0, 58, -13F, -6.6F, -1F, 2F, 3F, 2F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatExtension4 = new ModelPart(Arrays.asList(hatExtension4Cuboids), Collections.emptyMap());
		hatExtension4.setPivot(0F, 0F, 0F);
		setRotation(hatExtension4, 0F, 0F, 1.264073F);

		ModelPart.Cuboid[] hatBall1Cuboids = {
			new ModelPart.Cuboid(8, 58, 2F, -14.4F, -1.001F, 2F, 2F, 2F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall1 = new ModelPart(Arrays.asList(hatBall1Cuboids), Collections.emptyMap());
		hatBall1.setPivot(0F, 0F, 0F);
		setRotation(hatBall1, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBall2Cuboids = {
			new ModelPart.Cuboid(16, 57, 2.5F, -14.8F, -0.5F, 1F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall2 = new ModelPart(Arrays.asList(hatBall2Cuboids), Collections.emptyMap());
		hatBall2.setPivot(0F, 0F, 0F);
		setRotation(hatBall2, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBall3Cuboids = {
			new ModelPart.Cuboid(16, 57, 2.5F, -13F, -0.5F, 1F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall3 = new ModelPart(Arrays.asList(hatBall3Cuboids), Collections.emptyMap());
		hatBall3.setPivot(0F, 0F, 0F);
		setRotation(hatBall3, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBall4Cuboids = {
			new ModelPart.Cuboid(16, 57, 3.4F, -14F, -0.5F, 1F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall4 = new ModelPart(Arrays.asList(hatBall4Cuboids), Collections.emptyMap());
		hatBall4.setPivot(0F, 0F, 0F);
		setRotation(hatBall4, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBall5Cuboids = {
			new ModelPart.Cuboid(16, 57, 2.5F, -14F, 0.4F, 1F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall5 = new ModelPart(Arrays.asList(hatBall5Cuboids), Collections.emptyMap());
		hatBall5.setPivot(0F, 0F, 0F);
		setRotation(hatBall5, 0F, 0F, 0F);

		ModelPart.Cuboid[] hatBall6Cuboids = {
			new ModelPart.Cuboid(16, 57, 2.5F, -14F, -1.4F, 1F, 1F, 1F, 0F, 0F, 0F, true, 64F, 64F)
		};
		hatBall6 = new ModelPart(Arrays.asList(hatBall6Cuboids), Collections.emptyMap());
		hatBall6.setPivot(0F, 0F, 0F);
		setRotation(hatBall6, 0F, 0F, 0F);
	}

	@Override
	public void setAngles(AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float age, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, float r, float g, float b, float f) {
		hatBand1.render(matrixStack, vertexConsumer, light, overlay);
		hatBand2.render(matrixStack, vertexConsumer, light, overlay);
		hatBand3.render(matrixStack, vertexConsumer, light, overlay);
		hatBand4.render(matrixStack, vertexConsumer, light, overlay);
		hatBase1.render(matrixStack, vertexConsumer, light, overlay);
		hatBand5.render(matrixStack, vertexConsumer, light, overlay);
		hatBand6.render(matrixStack, vertexConsumer, light, overlay);
		hatBase2.render(matrixStack, vertexConsumer, light, overlay);
		hatExtension1.render(matrixStack, vertexConsumer, light, overlay);
		hatExtension2.render(matrixStack, vertexConsumer, light, overlay);
		hatExtension3.render(matrixStack, vertexConsumer, light, overlay);
		hatExtension4.render(matrixStack, vertexConsumer, light, overlay);
		hatBall1.render(matrixStack, vertexConsumer, light, overlay);
		hatBall2.render(matrixStack, vertexConsumer, light, overlay);
		hatBall3.render(matrixStack, vertexConsumer, light, overlay);
		hatBall4.render(matrixStack, vertexConsumer, light, overlay);
		hatBall5.render(matrixStack, vertexConsumer, light, overlay);
		hatBall6.render(matrixStack, vertexConsumer, light, overlay);
	}

	private void setRotation(ModelPart model, float x, float y, float z) {
		model.pitch = x;
		model.yaw = y;
		model.roll = z;
	}

}
