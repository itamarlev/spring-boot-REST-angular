package com.itamar.gamemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itamar.gamemanager.dao.GameManager;
import com.itamar.gamemanager.model.AnswerResult;
import com.itamar.gamemanager.model.PlayerAnswerMove;
import com.itamar.gamemanager.model.UserScore;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GameManagerController
{
	private final GameManager gameManager;

	@Autowired
	public GameManagerController(GameManager gameManager)
	{
		this.gameManager = gameManager;
	}

	@PostMapping("/answer")
	public AnswerResult answerQuestion(@RequestBody PlayerAnswerMove playerAnswerMove)
	{
		AnswerResult answerResult = gameManager.processPlayerMove(playerAnswerMove);
		return answerResult;
	}

	@GetMapping("/leader-board")
	public List<UserScore> retrieveLeaderBoard(@RequestParam(name = "game", required = true, defaultValue = "0") long gameId)
	{
		List<UserScore> gameLeaderBoard = gameManager.getGameLeaderBoard(gameId);
		return gameLeaderBoard;
	}

}
