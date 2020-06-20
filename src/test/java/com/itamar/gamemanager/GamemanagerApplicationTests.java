package com.itamar.gamemanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.itamar.gamemanager.controller.GameManagerController;
import com.itamar.gamemanager.dao.GameManager;
import com.itamar.gamemanager.model.AnswerResult;
import com.itamar.gamemanager.model.PlayerAnswerMove;
import com.itamar.gamemanager.model.UserScore;

import jdk.dynalink.linker.support.Guards;

@RunWith(SpringRunner.class)
@SpringBootTest
class GamemanagerApplicationTests {

	private GameManager gameManager;

	@Autowired
	public GamemanagerApplicationTests(GameManager gameManager){
		this.gameManager = gameManager;
	}

	@Test
	public void givenNegativeGame_whenSendPostRequestForAnswer_thenIllegalStatus()  {
		AnswerResult answerResult = this.gameManager.processPlayerMove(new PlayerAnswerMove("Itamar", -1L, 1L, 1L));
		assertThat(answerResult.getStatus().equals(AnswerResult.Status.ILLEGAL));
	}

	@Test
	public void givenANewAnsweredQuestion_whenSendPostRequestForAnswer_thenCorrectOrWrongStatus()  {
		AnswerResult answerResult = this.gameManager.processPlayerMove(new PlayerAnswerMove("Itamar", 1L, 1L, 1L));
		assertThat(answerResult.getStatus().equals(AnswerResult.Status.CORRECT) || answerResult.getStatus().equals(AnswerResult.Status.WRONG));
	}

	@Test
	public void givenAlreadyAnsweredQuestion_whenSendPostRequestForAnswer_thenIllegalStatus()  {
		this.gameManager.processPlayerMove(new PlayerAnswerMove("Itamar", 1L, 1L, 1L));
		AnswerResult answerResult = this.gameManager.processPlayerMove(new PlayerAnswerMove("Itamar", 1L, 1L, 1L));
		assertThat(answerResult.getStatus().equals(AnswerResult.Status.ILLEGAL));
	}

	@Test
	public void givenAPlayInAGame_whenSendGetRequestForThatGameLeaderBoard_thenThePlayerWillBeInTheResult()  {
		this.gameManager.processPlayerMove(new PlayerAnswerMove("Itamar", 1L, 1L, 1L));
		List<UserScore> gameLeaderBoard = this.gameManager.getGameLeaderBoard(1L);
		assertThat(gameLeaderBoard.get(0).getUserName().equals("Itamar"));
	}

}
