package design.aeonic.logicnetworks.api.client.screen.input.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import design.aeonic.logicnetworks.api.client.screen.input.AbstractInputWidget;
import design.aeonic.logicnetworks.api.client.screen.input.WidgetScreen;
import design.aeonic.logicnetworks.api.util.Texture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class ButtonInputWidget extends AbstractInputWidget {
    public static final Texture HIGHLIGHT = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 12, 14, 0, 24);

    public static final Texture BOX_LEFT = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 1, 12, 57, 0);
    public static final Texture BOX_FILL = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 64, 12, 0, 12);
    public static final Texture BOX_LAST = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 1, 12, 59, 0);

    public static final Texture CLICKED_LEFT = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 1, 12, 60, 0);
    public static final Texture CLICKED_FILL = new Texture("logicnetworks:textures/gui/input_widgets_extended.png", 64, 64, 64, 12, 0, 0);
    public static final Texture CLICKED_LAST = new Texture("logicnetworks:textures/gui/input_widgets.png", 64, 64, 1, 12, 62, 0);

    protected final Component label;
    protected final Component shiftLabel;
    protected final Runnable action;
    protected final Runnable shiftAction;

    protected boolean clicked = false;

    public ButtonInputWidget(int x, int y, Component label, Runnable action) {
        this(x, y, label, label, action, action);
    }

    public ButtonInputWidget(int x, int y, Component label, Component shiftLabel, Runnable action, Runnable shiftAction) {
        super(x, y);

        this.label = label;
        this.shiftLabel = shiftLabel;
        this.action = action;
        this.shiftAction = shiftAction;
    }

    @Override
    public void onClose(WidgetScreen screen) {
        clicked = false;
    }

    @Override
    public boolean mouseDown(WidgetScreen screen, int mouseX, int mouseY, int button) {
        if (button == 0) {
            clicked = true;
            if (Screen.hasShiftDown()) {
                shiftAction.run();
            } else {
                action.run();
            }
            playClickSound();
            return true;
        }
        return super.mouseDown(screen, mouseX, mouseY, button);
    }

    @Override
    public boolean mouseUp(WidgetScreen screen, int mouseX, int mouseY, int button) {
        if (button == 0 && clicked) {
            clicked = false;
            return true;
        }
        return super.mouseUp(screen, mouseX, mouseY, button);
    }

    @Override
    public boolean keyDown(WidgetScreen screen, int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_SPACE) {
            if (Screen.hasShiftDown()) {
                shiftAction.run();
            } else {
                action.run();
            }
            playClickSound();
            return true;
        }
        return super.keyDown(screen, keyCode, scanCode, modifiers);
    }

    @Override
    public void draw(PoseStack stack, WidgetScreen screen, int mouseX, int mouseY, float partialTicks) {
        if (isEnabled() && (isWithinBounds(mouseX, mouseY) || screen.getFocusedWidget() == this)) {
            HIGHLIGHT.draw(stack, getX() - 1, getY() - 1, 0, getWidth() + 2, getHeight() + 2, 1, 1, 1, 1, false);
        }

        float[] rgba = isEnabled() ? new float[]{1, 1, 1, 1} : new float[]{1f, 1f, 1f, .65f};
        BOX_LEFT.draw(stack, getX(), getY(), 0, 1, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);
        BOX_FILL.draw(stack, getX() + 1, getY(), 0, getWidth() - 2, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);
        BOX_LAST.draw(stack, getX() + getWidth() - 1, getY(), 0, 1, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);

        if (clicked) {
            CLICKED_LEFT.draw(stack, getX(), getY(), 0, 1, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);
            CLICKED_FILL.draw(stack, getX() + 1, getY(), 0, getWidth() - 2, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);
            CLICKED_LAST.draw(stack, getX() + getWidth() - 1, getY(), 0, 1, getHeight(), rgba[0], rgba[1], rgba[2], rgba[3], false);
        }

        stack.pushPose();
        stack.translate(0, 0, 400);
        Minecraft.getInstance().font.draw(stack, Screen.hasShiftDown() ? shiftLabel : label, getX() + 3, getY() + 2, 0xFFFFFF);
        stack.popPose();
    }

    @Override
    public int getWidth() {
        return 6 + Minecraft.getInstance().font.width((Screen.hasShiftDown() ? shiftLabel : label));
    }

    @Override
    public int getHeight() {
        return 12;
    }
}
