package net.PeytonPlayz585.Optifine;

import com.google.common.collect.Lists;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiMessage extends GuiScreen {
    private GuiScreen parentScreen;
    private String messageLine1;
    private String messageLine2;
    @SuppressWarnings("rawtypes")
	private final List listLines2 = Lists.newArrayList();
    protected String confirmButtonText;
    private int ticksUntilEnable;

    public GuiMessage(GuiScreen p_i33_1_, String p_i33_2_, String p_i33_3_) {
        this.parentScreen = p_i33_1_;
        this.messageLine1 = p_i33_2_;
        this.messageLine2 = p_i33_3_;
        this.confirmButtonText = I18n.format("gui.done", new Object[0]);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @SuppressWarnings("unchecked")
	public void initGui() {
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 74, this.height / 6 + 96, this.confirmButtonText));
        this.listLines2.clear();
        this.listLines2.addAll(this.fontRendererObj.listFormattedStringToWidth(this.messageLine2, this.width - 50));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) {
    	Minecraft.getMinecraft().displayGuiScreen(this.parentScreen);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, this.messageLine1, this.width / 2, 70, 16777215);
        int i = 90;

        for (Object s : this.listLines2) {
            this.drawCenteredString(this.fontRendererObj, (String) s, this.width / 2, i, 16777215);
            i += this.fontRendererObj.FONT_HEIGHT;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
        String s;
	    s = Config.getVersion();
	    String s1;
	    s1 = "HD_U";

	    if (s1.equals("HD")) {
	        s = "Shadow Client HD H8";
	    }

	    if (s1.equals("HD_U")) {
	        s = "Shadow Client HD H8 Ultra";
	    }

	    if (s1.equals("L")) {
	        s = "Shadow Client H8 Light";
	    }

        this.drawString(this.fontRendererObj, s, 2, this.height - 10, 8421504);
        String s2 = "EaglercraftX 1.8.8";
        int i1 = this.fontRendererObj.getStringWidth(s2);
        this.drawString(this.fontRendererObj, s2, this.width - i1 - 2, this.height - 10, 8421504);
    }

    public void setButtonDelay(int p_setButtonDelay_1_) {
        this.ticksUntilEnable = p_setButtonDelay_1_;

        for (GuiButton guibutton : this.buttonList) {
            guibutton.enabled = false;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen() {
        super.updateScreen();

        if (--this.ticksUntilEnable == 0) {
            for (GuiButton guibutton : this.buttonList) {
                guibutton.enabled = true;
            }
        }
    }
    
    public void returnToLastGuiScreen() {
    	Minecraft.getMinecraft().displayGuiScreen(this.parentScreen);
    }
}