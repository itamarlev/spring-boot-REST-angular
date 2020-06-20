package com.itamar.gamemanager.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.itamar.gamemanager.model.AnswerResult;
import com.itamar.gamemanager.model.PlayerAnswerMove;
import com.itamar.gamemanager.model.UserScore;

@Service
public class DemoGameManager implements GameManager
{
	//game statistics consists of gameId -> questionId -> Set of playerAnswerMove
	private final Map<Long, Map<Long, List<PlayerAnswerMove>>> gamesStatistics = new ConcurrentHashMap<>();

	//the leader board will hold for each game the accumulated score for every player
	private final Map<Long, Map<String, Integer>> leaderBoard = new ConcurrentHashMap<>();

	@Override
	public List<UserScore> getGameLeaderBoard(Long gameId)
	{
		Map<String, Integer> map = leaderBoard.getOrDefault(gameId, null);
		if (map == null) {
			return null;
		}
		List<UserScore> list = convertMapToUserScoreList(map);
		return list;
	}

	private List<UserScore> convertMapToUserScoreList(Map<String, Integer> map)
	{
		ArrayList<UserScore> retVal = new ArrayList<>();
		map.forEach((userName, score) -> {
			retVal.add(new UserScore(userName, score));
		});

		Collections.sort(retVal);
		return retVal;
	}

	/**
	 * updates the in memory map with the latest player move along with a random result.
	 *
	 * @param playerAnswerMove
	 * @return AnswerResult the random result and score
	 */
	@Override
	public AnswerResult processPlayerMove(PlayerAnswerMove playerAnswerMove)
	{
		try {
			AnswerResult answerResult = checkLegalMove(playerAnswerMove);
			if (answerResult != null) {
				return answerResult;
			}

			generateRandomResult(playerAnswerMove);
			updateGameStatistics(playerAnswerMove);
			updateLeaderBoard(playerAnswerMove);
			return playerAnswerMove.getAnswerResult();

		}
		catch (Exception e) {
			System.out.println("failure in updating the players last move" + e);
			return null;
		}
	}

	private AnswerResult checkLegalMove(PlayerAnswerMove playerAnswerMove)
	{
		boolean isNegativeNumbers = playerAnswerMove.getQuestionId() < 0 || playerAnswerMove.getAnswerId() < 0 || playerAnswerMove.getGameNumber() < 0;
		if (isNegativeNumbers) {
			return new AnswerResult(AnswerResult.Status.ILLEGAL, 0, "There should be no negative numbers in your application. correct and resend");
		}

		if (playerAnswerMove.getGameNumber() == 0) {
			return new AnswerResult(AnswerResult.Status.ILLEGAL, 0, "Game numbers starts at 1...");
		}

		if (isDuplicateGameQuestionName(playerAnswerMove)) {
			return new AnswerResult(AnswerResult.Status.ILLEGAL, 0, "Hey don't cheat.. you already answered this question...");
		}
		return null;
	}

	private boolean isDuplicateGameQuestionName(PlayerAnswerMove playerAnswerMove)
	{
		Map<Long, List<PlayerAnswerMove>> gameQuestions = gamesStatistics.get(playerAnswerMove.getGameNumber());
		if (!CollectionUtils.isEmpty(gameQuestions)) {
			List<PlayerAnswerMove> playerAnswerMoves = gameQuestions.get(playerAnswerMove.getQuestionId());
			return !CollectionUtils.isEmpty(playerAnswerMoves) && playerAnswerMoves.contains(playerAnswerMove);
		}
		return false;
	}

	private void updateGameStatistics(PlayerAnswerMove playerAnswerMove)
	{
		Long gameId = playerAnswerMove.getGameNumber();
		Long questionId = playerAnswerMove.getQuestionId();

		Map<Long, List<PlayerAnswerMove>> gameQuestions = gamesStatistics.computeIfAbsent(gameId, key -> new ConcurrentHashMap<>());
		List<PlayerAnswerMove> playerAnswerMoves = gameQuestions.computeIfAbsent(questionId, key -> new CopyOnWriteArrayList<>());
		playerAnswerMoves.add(playerAnswerMove);
	}

	private void updateLeaderBoard(PlayerAnswerMove playerAnswerMove)
	{
		int pointsEarned = playerAnswerMove.getAnswerResult().getPointsEarned();

		Long gameId = playerAnswerMove.getGameNumber();
		String userName = playerAnswerMove.getUserName();

		Map<String, Integer> usersScores = leaderBoard.computeIfAbsent(gameId, key -> new ConcurrentHashMap<>());
		usersScores.merge(userName, pointsEarned, Integer::sum);
	}

	/**
	 * sets a random result which corresponds to the answer.
	 * the score will be either 0 or 1
	 *
	 * @param playerAnswerMove
	 */
	private void generateRandomResult(PlayerAnswerMove playerAnswerMove)
	{
		double score = Math.floor(Math.random() + 0.5);
		AnswerResult.Status status = score < 1L ? AnswerResult.Status.WRONG : AnswerResult.Status.CORRECT;
		playerAnswerMove.setAnswerResult(new AnswerResult(status, (int) score));
	}
}
