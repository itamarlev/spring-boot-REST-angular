package com.itamar.gamemanager.model;

public class AnswerResult implements Comparable<AnswerResult>
{
	private Status status;
	private int pointsEarned;
	private String errorMessage;

	public AnswerResult()
	{}

	public AnswerResult(Status status, int pointsEarned, String errorMessage)
	{
		this.status = status;
		this.pointsEarned = pointsEarned;
		this.errorMessage = errorMessage;
	}

	public AnswerResult(Status status, int pointsEarned)
	{
		this.status = status;
		this.pointsEarned = pointsEarned;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public int getPointsEarned()
	{
		return pointsEarned;
	}

	public void setPointsEarned(int pointsEarned)
	{
		this.pointsEarned = pointsEarned;
	}

	@Override
	public int compareTo(AnswerResult o)
	{
		return this.pointsEarned - o.pointsEarned;
	}

	public enum Status
	{
		CORRECT, WRONG, ILLEGAL
	}

}
