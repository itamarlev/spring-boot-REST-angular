package com.itamar.gamemanager.model;

public class PlayerAnswerMove
{
	private String userName;
	private Long gameNumber;
	private Long questionId;
	private Long answerId;
	private AnswerResult answerResult;

	public PlayerAnswerMove()
	{
	}

	public PlayerAnswerMove(String userName, Long gameId, Long questionId, Long answerId)
	{
		this.userName = userName;
		this.gameNumber = gameId;
		this.questionId = questionId;
		this.answerId = answerId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public Long getGameNumber()
	{
		return gameNumber;
	}

	public void setGameNumber(Long gameNumber)
	{
		this.gameNumber = gameNumber;
	}

	public Long getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	public Long getAnswerId()
	{
		return answerId;
	}

	public void setAnswerId(Long answerId)
	{
		this.answerId = answerId;
	}

	public AnswerResult getAnswerResult()
	{
		return answerResult;
	}

	public void setAnswerResult(AnswerResult answerResult)
	{
		this.answerResult = answerResult;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean retVal = false;
		if(obj instanceof PlayerAnswerMove){
			PlayerAnswerMove p = (PlayerAnswerMove) obj;

			return p.getGameNumber().equals(this.gameNumber) && p.getQuestionId().equals(this.questionId) &&
					p.getUserName().equals(this.userName);
		}
		return retVal;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + this.gameNumber.hashCode() + this.userName.hashCode() + this.questionId.hashCode();
		return hash;
	}

}
