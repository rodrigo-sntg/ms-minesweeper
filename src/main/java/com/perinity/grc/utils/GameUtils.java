package com.perinity.grc.utils;

import com.perinity.grc.repository.entity.Game;

public class GameUtils {

	/**
	 *
	 * @param difficulty
	 * @param size
	 * @param numberOfBombs
	 * @return
	 */
	public static int getNumberOfBombs(int difficulty, int size, Integer numberOfBombs) {

			switch (difficulty) {
				case Game.DIFFICULTY_EASY:
					return  (int) (size * 0.1);
				case Game.DIFFICULTY_MEDIUM:
					return  (int) (size * 0.2);
				case Game.DIFFICULTY_HARD:
					return  (int) (size * 0.3);

				case Game.DIFFICULTY_CUSTOM:
					return numberOfBombs;

				default:
					return 0;
		}
	}

}
