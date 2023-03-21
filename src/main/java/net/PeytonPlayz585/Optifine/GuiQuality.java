package net.PeytonPlayz585.Optifine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
public class GuiQuality extends GuiScreen {
	private GuiScreen parentGuiScreen;
    protected String screenTitle = "Quality Settings";
    private GameSettings guiGameSettings;
    public static GuiVideoSettings video;

	private static final GameSettings.Options[] videoOptions = new GameSettings.Options[] {
			//GameSettings.Options.MIPMAP_LEVELS,
			//GameSettings.Options.MIPMAP_TYPE,
			//GameSettings.Options.AF_LEVEL,
			//GameSettings.Options.AA_LEVEL,
			//GameSettings.Options.CLEAR_WATER,
			//GameSettings.Options.RANDOM_MOBS,
			//GameSettings.Options.BETTER_GRASS,
			//GameSettings.Options.BETTER_SNOW
			GameSettings.Options.DYNAMIC_LIGHTS
			//GameSettings.Options.CONNECTED_TEXTURES,
			//GameSettings.Options.CUSTOM_COLORS,
			//GameSettings.Options.SMOOTH_BIOMES,
			//GameSettings.Options.SWAMP_COLORS
	};

	public GuiQuality(GuiScreen parentScreenIn, GameSettings gameSettingsIn) {
        this.parentGuiScreen = parentScreenIn;
        this.guiGameSettings = gameSettingsIn;
    }

	/**+
	 * Adds the buttons (and other controls) to the screen in
	 * question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui() {
		this.screenTitle = I18n.format("Quality Settings", new Object[0]);
        this.buttonList.clear();

        for (int i = 0; i < videoOptions.length; ++i) {
            GameSettings.Options gamesettings$options = videoOptions[i];

            if (gamesettings$options != null) {
                int j = this.width / 2 - 155 + i % 2 * 160;
                int k = this.height / 6 + 21 * (i / 2) - 12;

                if (gamesettings$options.getEnumFloat()) {
                    this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options));
                } else {
                    this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
                }
            }
        }

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format("gui.done", new Object[0])));

	}

	/**+
	 * Called by the controls from the buttonList when activated.
	 * (Mouse pressed for buttons)
	 */
	protected void actionPerformed(GuiButton button) {
        if (button.enabled) {
            int i = this.guiGameSettings.guiScale;

            if (button.id < 200 && button instanceof GuiOptionButton) {
                this.guiGameSettings.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
                button.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }

            if (button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            
            if (button.id == 231) {
            	Config.showGuiMessage("Shaders are not finished!", "DO NOT DM ME ABOUT THIS!");
            }

            if (this.guiGameSettings.guiScale != i) {
                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
                int j = scaledresolution.getScaledWidth();
                int k = scaledresolution.getScaledHeight();
                this.setWorldAndResolution(this.mc, j, k);
            }
            
            this.mc.gameSettings.saveOptions();
        }
        
    }
	
	public static GuiVideoSettings returnVideo() {
		return video;
	}

	/**+
	 * Draws the screen and all the components in it. Args : mouseX,
	 * mouseY, renderPartialTicks
	 */
	 public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	        this.drawDefaultBackground();
	        this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
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
	        int i = this.fontRendererObj.getStringWidth(s2);
	        this.drawString(this.fontRendererObj, s2, this.width - i - 2, this.height - 10, 8421504);
	        super.drawScreen(mouseX, mouseY, partialTicks);
	    }

	
	public static int getButtonWidth(GuiButton guiButtonIn) {
        return guiButtonIn.width;
    }

    public static int getButtonHeight(GuiButton guiButtonIn) {
        return guiButtonIn.height;
    }

    public static void drawGradientRect(GuiScreen guiScreenIn, int parInt1, int parInt2, int parInt3, int parInt4, int parInt5, int parInt6) {
    	guiScreenIn.drawGradientRect(parInt1, parInt2, parInt3, parInt4, parInt5, parInt6);
    }
}