import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class Menu
	{
	public static String[] gameType1     = {""};
	public static String[] gameType2     = {"", "4"};
	public static String[] gameType3     = {"", "30"};
	public static int menuFieldInt       = 0;
	public static int chooseFieldInt     = 0;
	public static int chooseFieldName    = 0;
	public static int chooseWidth        = 10;
	public static int chooseHeight       = 24;
	public static Font mainMenuFontBig   = new Font("Sans Serif", 0, 30);
	public static Font mainMenuFontSmall = new Font("Sans Serif", 0, 20);
	public static Font nameFont          = new Font("Sans Serif", 0, 26);
	public static Font optionsFont       = new Font("Sans Serif", 0, 14);
	public static char[][] alphabet      = {{' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z'}, {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'}};
	public static int isUpperCase        = 0;
	public static int currentLetter      = 0;
	public static int[][] letterCollect  = new int[10][2];
	public static boolean isReturning    = false;

	public static void main(String[] args)
	{
		while (true)
		mainMenu();
	}

	public static void mainMenu()
	{
		if (!isReturning)
		{
			Draw.setCanvasSize(600, 600);

			Draw.setXscale(-1, 1);
			Draw.setYscale(-1, 1);
			
			isReturning = true;
		}

		Draw.clear(Draw.BLACK);

		double downPress  = 0;
		double upPress    = 0;

		Stopwatch pressTimer = new Stopwatch();

		while (true)
		{
			drawTitleHeader();
			
			if ((pressTimer.elapsedTime() - upPress > 0.15) && Draw.isKeyPressed(KeyEvent.VK_UP))
			{
				menuFieldInt--;
				upPress = pressTimer.elapsedTime();
			}
				
			if ((pressTimer.elapsedTime() - downPress > 0.15) && Draw.isKeyPressed(KeyEvent.VK_DOWN))
			{
				menuFieldInt++;
				downPress = pressTimer.elapsedTime();
			}
			menuFieldInt %= 5;
			if (menuFieldInt < 0) menuFieldInt += 5;

			if (Draw.isKeyPressed(KeyEvent.VK_SPACE))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_SPACE))
				{}
				
				if (menuFieldInt == 0) {chooseName(gameType2); return;}
				if (menuFieldInt == 1) {chooseName(gameType1); return;}
				if (menuFieldInt == 2) {chooseName(gameType3); return;}
				if (menuFieldInt == 3) {chooseGame(); 		   return;}
				if (menuFieldInt == 4) {options();			   return;}
			}

			Draw.setFont(mainMenuFontBig);
			Draw.setPenColor(255, 255, 255);
			Draw.text(0, 0.58,  "Narrow");
			Draw.text(0, 0.28,  "Normal");
			Draw.text(0, -0.02, "Wide");
			Draw.text(0, -0.32, "Custom");
			Draw.text(0, -0.62, "Controls");

			if (menuFieldInt == 0)
			Draw.setPenRadius(0.03);
			else
			Draw.setPenRadius(0.01);

			Draw.setPenColor(255, 255, 255);
			drawBoxMain(0.6);

			if (menuFieldInt == 1)
			Draw.setPenRadius(0.03);
			else
			Draw.setPenRadius(0.01);

			Draw.setPenColor(255, 255, 255);
			drawBoxMain(0.3);

			if (menuFieldInt == 2)
			Draw.setPenRadius(0.03);
			else
			Draw.setPenRadius(0.01);

			Draw.setPenColor(255, 255, 255);
			drawBoxMain(0);

			if (menuFieldInt == 3)
			Draw.setPenRadius(0.03);
			else
			Draw.setPenRadius(0.01);

			Draw.setPenColor(255, 255, 255);
			drawBoxMain(-0.3);

			if (menuFieldInt == 4)
			Draw.setPenRadius(0.03);
			else
			Draw.setPenRadius(0.01);

			Draw.setPenColor(255, 255, 255);
			drawBoxMain(-0.6);



			Draw.setFont(mainMenuFontSmall);
			Draw.setPenColor(255, 255, 255);
			if (menuFieldInt == 0) Draw.text(0, -0.9, "Tetris, but the board is only four pieces wide.");
			if (menuFieldInt == 1) Draw.text(0, -0.9, "Tetris like we all know and love.");
			if (menuFieldInt == 2) Draw.text(0, -0.9, "You will need to be patient if you're gonna play this one.");
			if (menuFieldInt == 3) Draw.text(0, -0.9, "Choose your own dimensions.");
			if (menuFieldInt == 4) Draw.text(0, -0.9, "A rundown of the controls.");


			Draw.show(10);

			if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
				{}

				drawBoxExit();
				Draw.show(10);

				while (true)
				{
					if (Draw.isKeyPressed(KeyEvent.VK_Y))
					{
						while (Draw.isKeyPressed(KeyEvent.VK_Y))
						{}
						System.exit(0);
					}

					if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
					{
						while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
						{}
						System.exit(0);
					}

					if (Draw.isKeyPressed(KeyEvent.VK_N))
					{
						while (Draw.isKeyPressed(KeyEvent.VK_N))
						{}
						break;
					}
				}
			}
			Draw.clear(Draw.BLACK);
		}		
	}

	public static void drawBoxExit()
	{
		Draw.setPenRadius();
		Draw.setPenColor();

		double[] x = {-0.5, -0.5, 0.5, 0.5};
		double[] y = {-0.2, 0.2, 0.2, -0.2};

		Draw.setPenColor(0, 0, 0);
		Draw.filledPolygon(x, y);

		Draw.setPenRadius(0.01);
		Draw.setPenColor(255, 255, 255);
		Draw.polygon(x, y);

		Draw.text(0, 0.04, "Are you sure you");
		Draw.text(0, -0.04, "want to exit (Y/N)");
	}

	public static void options()
	{
		boolean pressed = false;
		double degrees  = 0.0;
		while (true)
		{
			Draw.clear(Draw.BLACK);

			if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
			pressed = true;

			if (pressed && !Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
			return;

			Draw.setFont(optionsFont);
			Draw.setPenColor(255, 255, 255);
			Draw.textLeft(-0.7, 0.6,  "Use the Arrow keys, Space key, and Escape");
			Draw.textLeft(-0.7, 0.5,  "key to navigate the menus.");
			Draw.textLeft(-0.7, 0.3,  "When in the game you move the pieces with");
			Draw.textLeft(-0.7, 0.2,  "the Right and Left arrow keys, you use the Up");
			Draw.textLeft(-0.7, 0.1,  "arrow key to rotate the block, soft drop the");
			Draw.textLeft(-0.7, 0,    "block with the Down arrow key, and hard drop");
			Draw.textLeft(-0.7, -0.1, "the block with the Space bar.");
			Draw.textLeft(-0.7, -0.3, "The game can be paused at anytime with the Escape key.");

			Draw.setFont(mainMenuFontSmall);
			Draw.setPenColor(100, 0, 0);
			Draw.text(-0.2, -0.7, "Peace", degrees);
			Draw.text(0, -0.7,    "and", degrees);
			Draw.text(0.16, -0.7,  "love", degrees);

			Draw.show(10);

			degrees += 5;
			if (degrees > 360.0) degrees -= 360;
		}
	}

	public static void chooseGame()
	{
		Draw.setPenRadius(0.01);
		Draw.clear(Draw.BLACK);
		Draw.setPenColor(255, 255, 255);

		double rightPress = 0;
		double leftPress  = 0;
		double downPress  = 0;
		double upPress    = 0;

		Stopwatch pressTimer = new Stopwatch();

		Draw.show(10);

		while (true)
		{
			if (!isReturning)
			{
				Draw.setCanvasSize(600, 600);

				Draw.setXscale(-1.0, 1.0);
				Draw.setYscale(-1.0, 1.0);
				
				isReturning = true;
			}

			if ((pressTimer.elapsedTime() - upPress > 0.2) && Draw.isKeyPressed(KeyEvent.VK_UP))
			{
				chooseFieldInt--;
				upPress = pressTimer.elapsedTime();
			}
				
			if ((pressTimer.elapsedTime() - downPress > 0.2) && Draw.isKeyPressed(KeyEvent.VK_DOWN))
			{
				chooseFieldInt++;
				downPress = pressTimer.elapsedTime();
			}
			chooseFieldInt %= 2;

			if ((pressTimer.elapsedTime() - rightPress > 0.1) && Draw.isKeyPressed(KeyEvent.VK_RIGHT))
			{
				if (chooseFieldInt == 0)                         chooseWidth++;
				if (chooseFieldInt == -1 || chooseFieldInt == 1) chooseHeight++;
				rightPress = pressTimer.elapsedTime();
			}

			if ((pressTimer.elapsedTime() - leftPress > 0.1) && Draw.isKeyPressed(KeyEvent.VK_LEFT))
			{
				if (chooseFieldInt == 0)                         chooseWidth--;
				if (chooseFieldInt == -1 || chooseFieldInt == 1) chooseHeight--;
				leftPress = pressTimer.elapsedTime();
			}

			if (chooseWidth < 4)   chooseWidth = 4;
			if (chooseWidth > 50)  chooseWidth = 50;
			if (chooseHeight < 24) chooseHeight = 24;
			if (chooseHeight > 50) chooseHeight = 50;



			if (Draw.isKeyPressed(KeyEvent.VK_SPACE))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_SPACE))
				{}

				String[] s = {"", "" + chooseWidth, "" + chooseHeight};

				chooseName(s);
			}

			if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
				{}

				return;
			}

			Draw.setPenColor(255, 255, 255);
			if (chooseFieldInt == 0)
			Draw.setPenRadius(0.02);
			else
			Draw.setPenRadius(0.01);

			drawBoxChoose(0.2);

			if (chooseFieldInt == -1 || chooseFieldInt == 1)
			Draw.setPenRadius(0.02);
			else
			Draw.setPenRadius(0.01);

			drawBoxChoose(-0.2);

			Draw.setFont(mainMenuFontBig);
			Draw.text(0.1, 0.18, "" + chooseWidth);
			Draw.text(0.1, -0.22, "" + chooseHeight);

			Draw.text(-0.5, 0.2, "Width:");
			Draw.text(-0.5, -0.2, "Heigth:");

			Draw.setFont(optionsFont);
			if ((1.0*chooseWidth)/chooseHeight > 5.0/3.0) Draw.textLeft(-1, -0.95, "They game may look a little weird if you play these dimensions.");

			Draw.show(10);

			Draw.clear(Draw.BLACK);
		}
	}

	public static void chooseName(String[] args)
	{
		Draw.setFont(nameFont);

		Draw.setPenRadius(0.01);
		Draw.clear(Draw.BLACK);
		Draw.setPenColor(255, 255, 255);

		double downPress   = 0;
		double upPress     = 0;
		boolean spacePress = false;
		double shiftPress  = 0;
		chooseFieldName    = 0;
		currentLetter      = letterCollect[0][0];
		isUpperCase        = letterCollect[0][1];

		Stopwatch pressTimer = new Stopwatch();
		
		while (true)
		{
			Draw.setFont(mainMenuFontBig);
			Draw.text(0, 0.2, "Tell me your name, player...?");
			Draw.text(0, -0.2, "...then hit Enter when you're good.");

			Draw.setFont(optionsFont);
			Draw.text(0, -0.9, "Use Up and Down to choose the letters, the Space bar to got to the next one,");
			Draw.text(0, -0.95, "and the Shift key to go from uppercase to lower case");


			Draw.setFont(nameFont);

			if (!spacePress && Draw.isKeyPressed(KeyEvent.VK_SPACE))
			{
				chooseFieldName++;

				chooseFieldName %= 10;
				if (chooseFieldName < 0) chooseFieldName += 10;

				currentLetter = letterCollect[chooseFieldName][0];
				isUpperCase   = letterCollect[chooseFieldName][1];
				spacePress = true;
			}

			if ((pressTimer.elapsedTime() - upPress > 0.1) && Draw.isKeyPressed(KeyEvent.VK_UP))
			{
				currentLetter++;
				upPress = pressTimer.elapsedTime();
			}

			if ((pressTimer.elapsedTime() - downPress > 0.1) && Draw.isKeyPressed(KeyEvent.VK_DOWN))
			{
				currentLetter--;
				downPress = pressTimer.elapsedTime();
			}

			if ((pressTimer.elapsedTime() - shiftPress > 0.2) && Draw.isKeyPressed(KeyEvent.VK_SHIFT))
			{
				if      (isUpperCase == 0) isUpperCase = 1;
				else if (isUpperCase == 1) isUpperCase = 0;
				shiftPress = pressTimer.elapsedTime();
			}
			chooseFieldName %= 10;
			if (chooseFieldName < 0) chooseFieldName += 10;

			currentLetter %= 26;
			if (currentLetter < 0) currentLetter += 26;


			letterCollect[chooseFieldName][0] = currentLetter;
			letterCollect[chooseFieldName][1] = isUpperCase;

			//Draw.text(0, -0.02, s);


			for (int i = 0; i < 10; i++)
			{
				if (i == chooseFieldName) Draw.setPenRadius(0.015);
				else 					  Draw.setPenRadius(0.01);
				drawBoxName(-0.675 + 0.15*i);

				String s = "" + alphabet[letterCollect[i][1]][letterCollect[i][0]];

				Draw.text(-0.675 + 0.15*i, 0, s);
			}


			String name = "";
			for (int i = 0; i < 10; i++)
			name = name + alphabet[letterCollect[i][1]][letterCollect[i][0]];

			name = name.replaceAll("\\s+$", "");

			args[0] = name;

			Draw.show(10);

			Draw.clear(Draw.BLACK);

			if (!Draw.isKeyPressed(KeyEvent.VK_SPACE))
			spacePress = false;

			if (Draw.isKeyPressed(KeyEvent.VK_ENTER))
			{
				Game.main(args);
				isReturning = false;
				return;
			}


			if    (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
				{}

				return;
			}
		}

	}

	public static void drawBoxName(double c)
	{
		double[] x = {c + 0.07, c + 0.07, c - 0.07, c - 0.07};
		double[] y = {-0.07, 0.07, 0.07, -0.07};

		Draw.polygon(x, y);
	}

	public static void drawBoxChoose(double c)
	{
		double[] x = {0, 0, 0.2, 0.2};
		double[] y = {c - 0.1, c + 0.1, c + 0.1, c - 0.1};

		Draw.polygon(x, y);
	}

	public static void drawBoxMain(double c)
	{
		double[] x = {-0.4, -0.4, 0.4, 0.4};
		double[] y = {c - 0.1, c + 0.1, c + 0.1, c - 0.1};

		Draw.polygon(x, y);
	}

	public static void drawTitleHeader()
	{
		int[][] titleTetris = {
								{1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
								{0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1},
								{0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
								{0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
								{0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1}};

		for (int i = 0; i < titleTetris.length; i++)
		for (int j = 0; j < titleTetris[i].length; j++)
		if (titleTetris[i][j] == 1) {Draw.setPenColor(255, 0, 0); Draw.filledSquare(-0.429 + j*0.033, 0.95 - 0.033*i, 0.02); Draw.setPenColor(0, 0, 0); Draw.filledSquare(-0.429 + j*0.033, 0.95 - 0.033*i, 0.014);}
	}
}