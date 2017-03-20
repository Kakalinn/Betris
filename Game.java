import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;

public class Game
{
	public static Font pauseFont        = new Font("Sans Serif", Font.BOLD, 60);
	public static Font defaultFont      = new Font("Sans Serif", Font.BOLD, 12);
	public static int size              = 600;
	public static int score             = 0;
	public static int level             = 100;
	public static int lines             = 10;
	public static int linesTotal        = 200;
	public static int levelNumber       = 0;
	public static int pieceBagRunner    = 100;
	public static boolean upPress       = false;
	public static boolean downPress     = false;
	public static boolean spacePress    = false;
	public static boolean escPress      = false;
	public static boolean quit          = false;
	public static double leftTime       = 0.0;
	public static double rightTime      = 0.0;
	public static int[] linescores      = {400, 600, 2000, 9000};
	public static int[] frequency       = new int[7];
	public static int[] pieceBag        = {0, 1, 2, 3, 4, 5, 6};
	public static int nextpiece         = choose();
	public static int currentpiece      = choose();
	public static int pieceColor        = currentpiece + 1;
	public static int nextPieceColor    = nextpiece + 1;
	public static Color[] colorWheel    = new Color[7];
	public static boolean tetrisBonus   = false;
	public static Stopwatch fullTime    = new Stopwatch();
	public static int checkLinesLine    = 0;
	public static int[][] gameColor;
	public static int[][] gameShadow;
	public static int[][] game;
	public static int[][][] drawPieces;
	public static int[][][] pieces;
	public static String name;

	public static void main(String[] args)
	{
		colorWheel[0] = new Color(255, 255, 0);
		colorWheel[1] = new Color(255, 0, 255);
		colorWheel[2] = new Color(192, 192, 192);
		colorWheel[3] = new Color(0, 255, 0);
		colorWheel[4] = new Color(0, 0, 128);
		colorWheel[5] = new Color(255, 0, 0);
		colorWheel[6] = new Color(0, 255, 255);
	/*	int[][] game =
		{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5}
		};*/

		int heigth      = 24;
		int width       = 10;

		size        = 600;
		score       = 0;
		level       = 100;
		lines       = 10;
		linesTotal  = 200;
		levelNumber = 0;

		quit = false;

		if (args.length >= 3)
		{
			heigth = Integer.parseInt(args[2]);
			width  = Integer.parseInt(args[1]);
		}

		if (args.length == 2)
		{
			width = Integer.parseInt(args[1]);
			if (width < 4) width = 4;
		}

		name = args[0];
		
		game = new int[heigth + 1][width];
		gameShadow = new int[heigth + 1][width];
		gameColor = new int[heigth + 1][width];
		drawPieces = new int[7][5][5];
		pieces = new int[7][2][4];

		for (int i = 0; i < game[0].length; i++)
		game[game.length - 1][i] = 5;

		int[][] L =
		{
			{1, 3, 1, 0},
			{1, 0, 0, 0}
		};

		int[][] J =
		{
			{1, 3, 1, 0},
			{0, 0, 1, 0}
		};

		int[][] T =
		{
			{1, 3, 1, 0},
			{0, 1, 0, 0}
		};

		int[][] S =
		{
			{1, 3, 0, 0},
			{0, 1, 1, 0}
		};

		int[][] Z =
		{
			{0, 3, 1, 0},
			{1, 1, 0, 0}
		};

		int[][] I =
		{
			{1, 3, 1, 1},
			{0, 0, 0, 0}
		};

		int[][] O =
		{
			{0, 1, 1, 0},
			{0, 1, 1, 0}
		};

		int[][] d1 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 1, 3, 1, 0},
			{0, 1, 0, 0, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d2 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 1, 3, 1, 0},
			{0, 0, 0, 1, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d3 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 1, 3, 1, 0},
			{0, 0, 1, 0, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d4 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 1, 3, 0, 0},
			{0, 0, 1, 1, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d5 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 3, 1, 0},
			{0, 1, 1, 0, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d6 =
		{
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 1, 3, 1, 1},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		};

		int[][] d7 =
		{
			{0, 0, 0, 0, 0},
			{0, 1, 3, 0, 0},
			{0, 1, 1, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		};

		drawPieces[0] = d1;
		drawPieces[1] = d2;
		drawPieces[2] = d3;
		drawPieces[3] = d4;
		drawPieces[4] = d5;
		drawPieces[5] = d6;
		drawPieces[6] = d7;

		pieces[0] = L;
		pieces[1] = J;
		pieces[2] = T;
		pieces[3] = S;
		pieces[4] = Z;
		pieces[5] = I;
		pieces[6] = O;

		Draw.setCanvasSize((size*(game[0].length + 20))/(game.length), size);
		Draw.setXscale(-26, 2*game[0].length + 14);
		Draw.setYscale(0, 2*game.length);
		//L, J, T, S, Z, I, O
		play();

		if (quit) return;

		fullTime.pause();

		//System.out.println("#Búið!");

		String[] tits = {};
		Draw.setPenColor(255, 0, 0);
		Draw.setFont(pauseFont);
		double air  = 0.0;
		double flow = 0.0;

		while (-5 + air < game.length)
		{
			Draw.clear();
			Draw.setFont(defaultFont);
			drawAll();
			Draw.setFont(pauseFont);
			
			if (levelNumber < 20)
			{
				Draw.setPenColor(255, 0, 0);
				Draw.text(game[0].length, -5 + air, "Game Over");
			}

			else
			{
				Draw.setPenColor(255, 255, 0);
				Draw.text(game[0].length, -5 + air, "You Win!");
			}
			

			Draw.show(10);

			air += 0.05;

			if (Draw.isKeyPressed(KeyEvent.VK_ENTER))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ENTER))
				{}

				return;
			}
		}

		Draw.setFont(defaultFont);
		Draw.setPenColor(255, 255, 255);
		Draw.text(game[0].length, game.length - 2, "Press Enter to go back to the main menu");
		Draw.show(10);

		while (flow < 6 && levelNumber < 20)
		{
			Draw.setPenColor(255, 0, 0);
			Draw.point(game[0].length + 13.2, 0.7 + game.length - flow);
			Draw.show(10);

			flow += 0.01;

			if (Draw.isKeyPressed(KeyEvent.VK_ENTER))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ENTER))
				{}

				return;
			}
		}

		while (true)
		{
			if (Draw.isKeyPressed(KeyEvent.VK_ENTER))
			{
				while (Draw.isKeyPressed(KeyEvent.VK_ENTER))
				{}

				return;
			}
		}
	}

	public static void play()
	{
		leftTime  = 0.0;
		rightTime = 0.0;

		Draw.show(10);
		countDown();

		for (int i = 0; i < game[0].length; i++)
		gameColor[game.length - 1][i] = 5;

		Draw.setFont(defaultFont);
		
		fullTime.reset();
		fullTime.start();
		while (!gameOver() && levelNumber < 20)
		{
			int time = (int)fullTime.elapsedTime();
			int cc = 0;

			if (checkBottom()) {currentpiece = nextpiece; nextpiece = choose(); fillColor();
				pieceColor = currentpiece + 1; nextPieceColor = nextpiece + 1; fill(); if (gameOver()) break;}
			else				   moveDown();

			for (int i = 0; i < 4; i++)
			if (checkLines() != -1)
			{
				if (tetrisBonus) score += 12000*levelNumber;
				checkLinesLine = checkLines();
				removeLines();
				removeLinesColor();
				score += linescores[i]*levelNumber;
				if (i == 3) tetrisBonus = true;
				else        tetrisBonus = false;
				linesTotal--;
				if (linesTotal%10 > lines || linesTotal%10 == 0) {levelNumber++; level -= 5;}
				lines = linesTotal%10;
				if (lines == 0) lines = 10;
				if (lines > 0 && levelNumber == 20) lines = 0;
			}


			if (!checkOnes()) {frequency[currentpiece]++; insert(pieces[currentpiece]); if (gameOver()) break;}

			//printArr(game);
			//printArrColor(gameColor);
			//System.out.println(pieceColor);
			//System.out.println(nextPieceColor);
			//System.out.println("Score: " + score/10);
			//printArrColor(gameShadow);
			//System.out.println("- - -");

			while (cc < level)
			{
				if (levelNumber > 19) break;

				clearShadow();
				drawAll();
				Draw.show(10);

				if (!spacePress && Draw.isKeyPressed(KeyEvent.VK_SPACE))
				{
					while(!checkBottom())
					{
						moveDown();
						score += 8*levelNumber;
					}
					spacePress = true;
					break;
				}

				if ((fullTime.elapsedTime() - leftTime > 0.1) && Draw.isKeyPressed(KeyEvent.VK_LEFT) && !Draw.isKeyPressed(KeyEvent.VK_RIGHT))
				{
					leftTime = fullTime.elapsedTime();
					moveLeft();
				}

				if ((fullTime.elapsedTime() - rightTime > 0.1) && Draw.isKeyPressed(KeyEvent.VK_RIGHT) && !Draw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					rightTime = fullTime.elapsedTime();
					moveRight();
				}

				if (!upPress && Draw.isKeyPressed(KeyEvent.VK_UP))
				{
					rotate();
					upPress = true;
				}

				if (Draw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					cc += 15;
					score += levelNumber;
				}

				if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
				{
					fullTime.pause();
					
					Draw.setFont(pauseFont);

					double[] x = {0, 0, 2*game[0].length, 2*game[0].length};
					double[] y = {2, 2*game.length, 2*game.length, 2};

					Draw.setPenColor(0, 0, 0);
					Draw.filledPolygon(x, y);

					Draw.setPenColor(255, 255, 0);
					Draw.text(game[0].length, game.length, "Paused!");
					Draw.setFont(defaultFont);
					Draw.text(game[0].length, game.length - 5, "Press Enter to quit.");
					Draw.show(10);

					while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
					{}
					
					pauseLoop:
					while (true)
					{
						if (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
						{
							while (Draw.isKeyPressed(KeyEvent.VK_ESCAPE))
							{}

							break pauseLoop;
						}

						if (Draw.isKeyPressed(KeyEvent.VK_ENTER))
						{
							while (Draw.isKeyPressed(KeyEvent.VK_ENTER))
							{}

							quit = true;
							return;
						}

					}


					fullTime.start();
				}

				Draw.clear();
				cc++;
				if (!Draw.isKeyPressed(KeyEvent.VK_SPACE))
				spacePress = false;
				if (!Draw.isKeyPressed(KeyEvent.VK_DOWN))
				downPress  = false;
				if (!Draw.isKeyPressed(KeyEvent.VK_UP))
				upPress    = false;
			}
		}
		if (levelNumber > 19) {levelNumber = 20; lines = 0;}
		insert(pieces[currentpiece]);
		fillColor();
		clearShadow();
		//printArr(game);
		//printArrColor(game);
		drawAll();
		//System.out.println("Score: " + score/10);

		Draw.show(10);
	}

	public static void countDown()
	{
		Draw.setFont(pauseFont);

		Draw.setPenColor(255, 0, 0);
		Draw.clear(Draw.BLACK);
		Draw.text(-6 + game[0].length, game.length, "3");
		Draw.show(1000);
		Draw.clear(Draw.BLACK);
		Draw.text(-6 + game[0].length, game.length, "2");
		Draw.show(1000);
		Draw.clear(Draw.BLACK);
		Draw.text(-6 + game[0].length, game.length, "1");
		Draw.show(1000);
		Draw.clear(Draw.BLACK);

		Draw.setFont(defaultFont);
	}

	public static void clearShadow()
	{
		for (int i = 0; i < gameShadow.length; i++)
		for (int j = 0; j < gameShadow[i].length; j++)
		gameShadow[i][j] = 0;
	}

	public static void removeLinesColor()
	{
		for (int i = 0; i < game[0].length; i++)
		gameColor[checkLinesLine][i] = 0;

		for (int i = checkLinesLine; i > 0; i--)
		for (int j = 0; j < game[i].length; j++)
		gameColor[i][j] = gameColor[i - 1][j];
	}

	public static void fillColor()
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 1 || game[i][j] == 3)
		gameColor[i][j] = pieceColor;
	}

	public static int find3x(int[][] game)
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 3) return j;

		return -1;
	}

	public static int find3y(int[][] game)
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 3) return i;

		return -1;
	}

	public static int findlimitx0(int[][] game)
	{
		for (int j = 0; j < game[0].length; j++)
		for (int i = 0; i < game.length; i++)
		if  (game[i][j] == 1) return j;

		return -1;
	}

	public static int findlimitx1(int[][] game)
	{
		for (int j = game[0].length - 1; j >= 0; j--)
		for (int i = game.length - 1; i >= 0; i--)
		if  (game[i][j] == 1) return j;

		return -1;
	}

	public static int findlimity0(int[][] game)
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 1) return i;

		return -1;
	}

	public static int findlimity1(int[][] game)
	{
		for (int i = game.length - 1; i >= 0; i--)
		for (int j = game[i].length - 1; j >= 0; j--)
		if  (game[i][j] == 1) return i;

		return -1;
	}

	public static void rotate()
	{
		int move        = 0;
		int gamepieceX0 = findlimitx0(game);
		int gamepieceY0 = findlimity0(game);
		int gamepieceX1 = findlimitx1(game);
		int gamepieceY1 = findlimity1(game);

		int pieceWidth  = gamepieceX1 - gamepieceX0 + 1;
		int pieceHeight = gamepieceY1 - gamepieceY0 + 1;

		int[][] piece = new int[pieceHeight][pieceWidth];
		int[][] temp  = new int[piece[0].length][piece.length];

		for (int i = gamepieceY0; i <= gamepieceY1; i++)
		for (int j = gamepieceX0; j <= gamepieceX1; j++)
		if  (game[i][j] == 1 || game[i][j] == 3)
		piece[i - gamepieceY0][j - gamepieceX0] = game[i][j];

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		temp[i][j] = piece[piece.length - 1 - j][i];
		
		int game3x = find3x(game);
		int game3y = find3y(game);
		int temp3x = find3x(temp);
		int temp3y = find3y(temp);

		if (pieceWidth == 2 && pieceHeight == 2) return;

		while (game3y < temp3y)
		{
			int gamey1 = game3y;
			int tempy1 = temp3y;

			moveDown();

			game3y = find3y(game);
			temp3y = find3y(temp);

			if (game3y - temp3y == gamey1 - tempy1) return;
		}

		while (game3y + pieceWidth - temp3y > game.length - 1)
		{
			int gamey1 = game3y;
			int tempy1 = temp3y;

			moveUp();

			game3y = find3y(game);
			temp3y = find3y(temp);

			if (game3y == gamey1 && temp3y == tempy1) return;
		}

		if (pieceWidth == 1)
		{
			if (game3y < game.length/2)
			{
				if      (game3x == 0                  && game[game3y + 2][game3x] == 1) {move += 2; moveRight(); moveRight();}
				else if (game3x == 0                  && game[game3y + 2][game3x] != 1) {move += 1; moveRight();}
				else if (game3x == 1                  && game[game3y + 2][game3x] == 1) {move += 1; moveRight();}
				else if (game3x == game[0].length - 1 && game[game3y + 2][game3x] != 1) {move -= 2; moveLeft(); moveLeft();}
				else if (game3x == game[0].length - 1 && game[game3y + 2][game3x] == 1) {move -= 1; moveLeft();}
				else if (game3x == game[0].length - 2 && game[game3y + 2][game3x] != 1) {move -= 1; moveLeft();}
			}
			else
			{ 
				if      (game3x == 0                  && game[game3y - 2][game3x] != 1) {move += 2; moveRight(); moveRight();}
				else if (game3x == 0                  && game[game3y - 2][game3x] == 1) {move += 1; moveRight();}
				else if (game3x == 1                  && game[game3y - 2][game3x] != 1) {move += 1; moveRight();}
				else if (game3x == game[0].length - 1 && game[game3y - 2][game3x] == 1) {move -= 2; moveLeft(); moveLeft();}
				else if (game3x == game[0].length - 1 && game[game3y - 2][game3x] != 1) {move -= 1; moveLeft();}
				else if (game3x == game[0].length - 2 && game[game3y - 2][game3x] == 1) {move -= 1; moveLeft();}
			}
		}
		else
		{
			while (game3x < temp3x)
			{
				int gamex1 = game3x;
				int tempx1 = temp3x;

				moveRight();
				if (game3x == find3x(game)) moveLeft();

				game3x = find3x(game);
				temp3x = find3x(temp);

				if (game3x == gamex1) return;
			}

			while (game3x + pieceHeight - temp3x > game[0].length)
			{
				int gamex1 = game3x;
				int tempx1 = temp3x;

				moveLeft();
				if (game3x == find3x(game)) moveRight();

				game3x = find3x(game);
				temp3x = find3x(temp);

				if (game3x == gamex1) return;
			}
		}

		int game3xnew = find3x(game);
		int game3ynew = find3y(game);

		if (game3ynew - temp3y < 0 || game3ynew - temp3y > game.length - 1 || game3xnew - temp3x < 0 || game3xnew - temp3x + temp[0].length > game[0].length)
		{
			while (game3x != find3x(game))
			{
				if (move <  0) moveRight();
				if (move  > 0) moveLeft();
				if (move == 0) break;
			}
			return;
		}

		for (int i = game3ynew - temp3y; i < game3ynew - temp3y + temp.length; i++)
		for (int j = game3xnew - temp3x; j < game3xnew - temp3x + temp[0].length; j++)
		if  ((game[i][j] == 2 || game[i][j] == 5) && (temp[i - game3ynew + temp3y][j - game3xnew + temp3x] == 3 || temp[i - game3ynew + temp3y][j - game3xnew + temp3x] == 1))
		return;

		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 1 || game[i][j] == 3) game[i][j] = 0;

		for (int i = game3ynew - temp3y; i < game3ynew - temp3y + temp.length; i++)
		for (int j = game3xnew - temp3x; j < game3xnew - temp3x + temp[0].length; j++)
		if  (temp[i - game3ynew + temp3y][j - game3xnew + temp3x] == 1 || temp[i - game3ynew + temp3y][j - game3xnew + temp3x] == 3)
		game[i][j] = temp[i - game3ynew + temp3y][j - game3xnew + temp3x];
	}

	public static void moveLeft()
	{
		for (int i = 0; i < game.length; i++)
		if  (game[i][0] == 1 || game[i][0] == 3) return;

		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		{
			if  (game[i][j] == 1 && game[i][j - 1] == 2) return;
			if  (game[i][j] == 3 && game[i][j - 1] == 2) return;
		}

		int[][] temp = new int[game.length][game[0].length];

		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		{
			if  (game[i][j] == 1) temp[i][j - 1] = 1;
			if  (game[i][j] == 3) temp[i][j - 1] = 3;
		}

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		if (game[i][j] == 1 || game[i][j] == 3) game[i][j] = 0;

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (temp[i][j] == 1) game[i][j] = 1;
			if  (temp[i][j] == 3) game[i][j] = 3;
		}
	}

	public static void moveRight()
	{
		for (int i = 0; i < game.length; i++)
		if  (game[i][game[i].length-1] == 1 || game[i][game[i].length-1] == 3) return;

		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length - 1; j++)
		{
			if  (game[i][j] == 1 && game[i][j + 1] == 2) return;
			if  (game[i][j] == 3 && game[i][j + 1] == 2) return;
		}

		int[][] temp = new int[game.length][game[0].length];

		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		{
			if  (game[i][j] == 1) temp[i][j + 1] = 1;
			if  (game[i][j] == 3) temp[i][j + 1] = 3;
		}

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		if (game[i][j] == 1 || game[i][j] == 3) game[i][j] = 0;

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (temp[i][j] == 1) game[i][j] = 1;
			if  (temp[i][j] == 3) game[i][j] = 3;
		}
	}

	public static void moveDown()
	{
		int[][] temp = new int[game.length][game[0].length];

		if (checkBottom()) return;

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (game[i][j] == 1) temp[i + 1][j] = 1;
			if  (game[i][j] == 3) temp[i + 1][j] = 3;
		}

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		if  (game[i][j] == 1 || game[i][j] == 3) game[i][j] = 0;

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (temp[i][j] == 1) game[i][j] = 1;
			if  (temp[i][j] == 3) game[i][j] = 3;
		}
	}

	public static void moveUp()
	{
		int[][] temp = new int[game.length][game[0].length];

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (game[i][j] == 1) temp[i - 1][j] = 1;
			if  (game[i][j] == 3) temp[i - 1][j] = 3;
		}

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		if  (game[i][j] == 1 || game[i][j] == 3) game[i][j] = 0;

		for (int i = 0; i < temp.length; i++)
		for (int j = 0; j < temp[i].length; j++)
		{
			if  (temp[i][j] == 1) game[i][j] = 1;
			if  (temp[i][j] == 3) game[i][j] = 3;
		}
	}

	public static void insert(int[][] piece)
	{
		int s = (game[0].length - 4)/2;

		for (int i = 0; i < piece.length; i++)
		for (int j = 0; j < piece[i].length; j++)
		{
			if ((piece[i][j] == 1 || piece[i][j] == 3) && game[i][j + s] == 2) game[game.length - 1][0] = 9;
			if  (piece[i][j] == 1 || piece[i][j] == 3) game[i][j + s] = piece[i][j];
		}
	}

	public static void fisherYates(int[] a)
	{
		for (int i = 0; i < a.length - 1; i++)
		{
			int t = (int)(Math.random()*(a.length - i)) + i;

			int tmp = a[i];
			a[i] = a[t];
			a[t] = tmp;
		}
	}

	public static int choose()
	{
		if (pieceBagRunner > 6)
		{
			fisherYates(pieceBag);
			pieceBagRunner = 0;
		}

		int t = pieceBag[pieceBagRunner];
		pieceBagRunner++;

		return t;
	}

	public static void findShadow()
	{
		int gamepieceX0 = findlimitx0(game);
		int gamepieceY0 = findlimity0(game);
		int gamepieceX1 = findlimitx1(game);
		int gamepieceY1 = findlimity1(game);

		int pieceWidth  = gamepieceX1 - gamepieceX0 + 1;
		int pieceHeight = gamepieceY1 - gamepieceY0 + 1;

		if (gamepieceX0 == -1) return;
		if (gamepieceX1 == -1) return;
		if (gamepieceY0 == -1) return;
		if (gamepieceY1 == -1) return;

		int[][] piece = new int[pieceHeight][pieceWidth];

		for (int i = gamepieceY0; i <= gamepieceY1; i++)
		for (int j = gamepieceX0; j <= gamepieceX1; j++)
		if  (game[i][j] == 1 || game[i][j] == 3)
		piece[i - gamepieceY0][j - gamepieceX0] = game[i][j];

		int[] lengths = new int[piece[0].length];
		for (int i = 0; i < piece.length; i++)
		for (int j = 0; j < piece[0].length; j++)
		if  (piece[i][j] == 1 || piece[i][j] == 3)
		lengths[j] = findLengthDown(gamepieceY0 + i, gamepieceX0 + j);

		int min = lengths[0];

		for (int i = 1; i < lengths.length; i++)
		if  (lengths[i] < min) min = lengths[i];

		for (int i = 0; i < piece.length; i++)
		for (int j = 0; j < piece[i].length; j++)
		if  (piece[i][j] == 1 || piece[i][j] == 3)
		gameShadow[gamepieceY0 + min + i][gamepieceX0 + j] = 1;
	}

	public static int findLengthDown(int y, int x)
	{
		int sum = 0;
		int i   = 1;

		while (game[y + i][x] != 2 && game[y + i][x] != 5)
		{
			  i++;
			sum++;
		}

		return sum;
	}

	public static void removeLines()
	{
		for (int i = 0; i < game[0].length; i++)
		game[checkLinesLine][i] = 0;

		for (int i = checkLinesLine; i > 0; i--)
		for (int j = 0; j < game[i].length; j++)
		game[i][j] = game[i - 1][j];
		
		for (int i = 0; i < game[0].length; i++)
		game[0][i] = 0;
	}

	public static int checkLines()
	{
		for (int i = 0; i < game.length; i++)
		{
			boolean test = true;

			for (int j = 0; j < game[i].length; j++)
			test = test && game[i][j] == 2;

			if  (test) return i;
		}

		return -1;
	}

	public static boolean checkBottom()
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		{
			if  (game[i][j] == 1 && game[i + 1][j] == 2) return true;
			if  (game[i][j] == 1 && game[i + 1][j] == 5) return true;
			if  (game[i][j] == 3 && game[i + 1][j] == 2) return true;
			if  (game[i][j] == 3 && game[i + 1][j] == 5) return true;
		}
		return false;
	}

	public static boolean checkOnes()
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 1) return true;
		return false;
	}

	public static void fill()
	{
		for (int i = 0; i < game.length; i++)
		for (int j = 0; j < game[i].length; j++)
		if  (game[i][j] == 1 || game[i][j] == 3)
		game[i][j] = 2;
	}

	public static void drawAll()
	{
		drawBoard();
		drawPieces();
		drawFrequency();
		drawScores();
	}

	public static void drawBoard()
	{
		Draw.clear(Draw.BLACK);
		Draw.setPenColor(100, 100, 100);

		double[] borderx1 = {0, -26, -26, 0};
		double[] bordery1 = {0, 0, 2*game.length, 2*game.length};

		Draw.filledPolygon(borderx1, bordery1);

		double[] borderx2 = {2*game[0].length, 2*game[0].length + 15, 2*game[0].length + 15, 2*game[0].length};
		double[] bordery2 = {0, 0, 2*game.length, 2*game.length};

		Draw.filledPolygon(borderx2, bordery2);

		double[] borderx3 = {0, 2*game[0].length, 2*game[0].length, 0};
		double[] bordery3 = {0, 0, 2, 2};

		Draw.filledPolygon(borderx3, bordery3);

		double[] pieceborderx = {2*game[0].length + 2, 2*game[0].length + 12, 2*game[0].length + 12, 2*game[0].length + 2};
		double[] piecebordery = {2*game.length - 12, 2*game.length - 12, 2*game.length - 2, 2*game.length - 2};

		Draw.setPenColor(0, 0, 0);
		Draw.filledPolygon(pieceborderx, piecebordery);
	}

	public static void drawPieces()
	{
		findShadow();

		Draw.setPenColor(255, 0, 0);
		int cx3 = -1;
		int cy3 = -1;
		for (int i = 0; i < pieces[nextpiece].length; i++)
		for (int j = 0; j < pieces[nextpiece][i].length; j++)
		if  (pieces[nextpiece][i][j] == 3)
		{
			cx3 = i;
			cy3 = j;
		}
		if (cx3 == -1 || cy3 == -1) pieces[nextpiece][0][1] = 3;

		for (int i = 0; i < pieces[nextpiece].length; i++)
		for (int j = 0; j < pieces[nextpiece][0].length; j++)
		if  (pieces[nextpiece][i][j] == 1 || pieces[nextpiece][i][j] == 3)
		{
			Draw.setPenColor(colorWheel[nextPieceColor - 1]);
			Draw.filledSquare(2*game[0].length + 7 - 2*cy3 + 2*j, 2*game.length - 7 + 2*cx3 - 2*i, 1);
			Draw.setPenColor(255, 255, 255);
			Draw.filledSquare(2*game[0].length + 7 - 2*cy3 + 2*j, 2*game.length - 7 + 2*cx3 - 2*i, 0.6);
		}

		if (cx3 == -1) cx3 = 5;
		if (cy3 == -1) cy3 = 0;


		for (int i = 0; i < game.length - 1; i++)
		for (int j = 0; j < game[i].length; j++)
		{
			if (gameShadow[i][j] == 1)
			{
				Draw.setPenColor(20, 20, 20);
				Draw.filledSquare(2*j + 1, 2*game.length - 2*i - 1, 1);
			}

			if (game[i][j] == 1 || game[i][j] == 3)
			{
				Draw.setPenColor(colorWheel[pieceColor - 1]);
				Draw.filledSquare(2*j + 1, 2*game.length - 2*i - 1, 1);
				Draw.setPenColor(255, 255, 255);
				Draw.filledSquare(2*j + 1, 2*game.length - 2*i - 1, 0.6);
			}

			if (game[i][j] == 2)
			{
				Draw.setPenColor(colorWheel[gameColor[i][j] - 1]);
				Draw.filledSquare(2*j + 1, 2*game.length - 2*i - 1, 1);
				Draw.setPenColor(100, 100, 100);
				Draw.filledSquare(2*j + 1, 2*game.length - 2*i - 1, 0.6);
			}
		}
	}

	public static void drawFrequency()
	{
		Draw.setPenColor(0, 0, 0);
		for (int i = 1; i < 3; i++)
		for (int j = 1; j < 4; j++)
		Draw.filledSquare(-i*12 + 5, 2*game.length - j*12 + 5, 5);

		Draw.filledSquare(-1.5*12 + 5, 2*game.length - 4*12 + 5, 5);

		int shapeNumber = 0;
		int[] numbers = {6, 0, 1, 2, 3, 4, 5};
		for (int j = 0; j < 3; j++)
		for (int i = 0; i < 2; i++)
		{
			for (int k = 0; k < 5; k++)
			for (int l = 0; l < 5; l++)
			{
				Draw.setPenColor(colorWheel[numbers[shapeNumber + 1]]);
				if  (drawPieces[shapeNumber][l][k] == 1 || drawPieces[shapeNumber][l][k] == 3)
				{
					Draw.filledSquare(-23 + 12*i + 2*k, 2*game.length - 3 - 12*j - 2*l, 1);
					Draw.setPenColor(255, 255, 255);
					Draw.filledSquare(-23 + 12*i + 2*k, 2*game.length - 3 - 12*j - 2*l, 0.6);
				}
			}
			shapeNumber++;
		}

		for (int i = 0; i < 5; i++)
		for (int j = 0; j < 5; j++)
		if  (drawPieces[shapeNumber][i][j] == 1 || drawPieces[shapeNumber][i][j] == 3)
		{
			Draw.setPenColor(colorWheel[numbers[(shapeNumber + 1)%colorWheel.length]]);
			Draw.filledSquare(2*i - 15, 2*game.length - 39 - 2*j, 1);
			Draw.setPenColor(255, 255, 255);
			Draw.filledSquare(2*i - 15, 2*game.length - 39 - 2*j, 0.6);
		}

		Draw.setPenColor(255, 255, 255);
		int frequencyNumber = 0;
		String freq = "";
		for (int j = 0; j < 3; j++)
		for (int i = 0; i < 2; i++)
		{
			freq = "" + frequency[frequencyNumber];
			Draw.text(-19 + 12*i, 2*game.length - 11.5 - 12*j, freq);
			frequencyNumber++;
		}
		for (int i = 0; i < 7; i++)

		freq = "" + frequency[frequencyNumber];
		Draw.text(-13, 2*game.length - 47.5, freq);
	}

	public static void drawScores()
	{
		Draw.setPenColor(0, 0, 0);

		double[] namefillx = {2*game[0].length + 3.5, 2*game[0].length + 11.5, 2*game[0].length + 11.5, 2*game[0].length + 3.5};
		double[] namefilly = {game.length + 7.3, game.length + 7.3, game.length + 8.8, game.length + 8.8};

		Draw.filledPolygon(namefillx, namefilly);

		double[] scorefillx = {2*game[0].length + 3.5, 2*game[0].length + 11.5, 2*game[0].length + 11.5, 2*game[0].length + 3.5};
		double[] scorefilly = {game.length + 3.3, game.length + 3.3, game.length + 4.8, game.length + 4.8};

		Draw.filledPolygon(scorefillx, scorefilly);

		double[] linesfillx = {2*game[0].length + 3.5, 2*game[0].length + 11.5, 2*game[0].length + 11.5, 2*game[0].length + 3.5};
		double[] linesfilly = {game.length - 0.7, game.length - 0.7, game.length + 0.8, game.length + 0.8};

		Draw.filledPolygon(linesfillx, linesfilly);

		double[] levelNumberfillx = {2*game[0].length + 3.5, 2*game[0].length + 11.5, 2*game[0].length + 11.5, 2*game[0].length + 3.5};
		double[] levelNumberfilly = {game.length - 4.7, game.length - 4.7, game.length - 3.2, game.length - 3.2};

		Draw.filledPolygon(levelNumberfillx, levelNumberfilly);

		double[] timefillx = {2*game[0].length + 3.5, 2*game[0].length + 11.5, 2*game[0].length + 11.5, 2*game[0].length + 3.5};
		double[] timefilly = {game.length - 8.7, game.length - 8.7, game.length - 7.2, game.length - 7.2};

		Draw.filledPolygon(timefillx, timefilly);

		Draw.setPenColor(255, 255, 255);

		Draw.text(2*game[0].length + 7.5, game.length + 9.5, "Name");
		Draw.text(2*game[0].length + 7.5, game.length + 8.0, name);

		Draw.text(2*game[0].length + 7.5, game.length + 5.5, "Lines");
		Draw.text(2*game[0].length + 7.5, game.length + 4.0, "" + lines);

		Draw.text(2*game[0].length + 7.5, game.length + 1.5, "Score");
		Draw.text(2*game[0].length + 7.5, game.length + 0.0, "" + score/10);

		Draw.text(2*game[0].length + 7.5, game.length + -2.5, "Level");
		Draw.text(2*game[0].length + 7.5, game.length + -4.0, "" + levelNumber);

		int time = (int)fullTime.elapsedTime();
		int sek  = time%60;
		int min  = time/60;
		min     %= 60;
		int klst = time/3600;
		String seks;
		String mins;
		String klsts = "" + klst;

		if (sek < 10)
		seks = "0" + sek;
		else
		seks = "" + sek;

		if (min < 10)
		mins = "0" + min;
		else
		mins = "" + min;

		Draw.text(2*game[0].length + 7.5, game.length + -6.5, "Time");
		Draw.text(2*game[0].length + 7.5, game.length + -8.0, klsts + ":" + mins + ":" + seks);
	}

	public static void printArr(int[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				if       (a[i][j] == 0)
				System.out.print("  ");
				else if  (a[i][j] == 5)
				System.out.print("- ");
				else if  (a[i][j] == 2)
				System.out.print("X ");
				else
				System.out.print("8 ");
			}
			System.out.println();
		}
	}

	public static void printArrColor(int[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				if (a[i][j] == 5)
				System.out.print("- ");
				else
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static boolean gameOver()
	{
		for (int i = (game[0].length - 4)/2; i < (game[0].length - 4)/2 + 4 ; i++)
		if (game[0][i] == 2) return true;

		if (game[game.length - 1][0] == 9) return true;
		
		return false;
	}
}