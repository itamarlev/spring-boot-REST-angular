package com.itamar.gamemanager.dao;

import java.util.List;
import java.util.Map;

import com.itamar.gamemanager.model.AnswerResult;
import com.itamar.gamemanager.model.PlayerAnswerMove;
import com.itamar.gamemanager.model.UserScore;

public interface GameManager
{
	List<UserScore> getGameLeaderBoard(Long gameId);
	AnswerResult processPlayerMove(PlayerAnswerMove answer);
}
