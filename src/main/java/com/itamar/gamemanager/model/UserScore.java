package com.itamar.gamemanager.model;

import java.util.Comparator;

public class UserScore implements Comparable<UserScore>
{
	private String userName;
	private int score;

	public UserScore(){
	}

	public UserScore(String userName, int score)
	{
		this.userName = userName;
		this.score = score;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	@Override
	public int compareTo(UserScore otherUserScore)
	{
		return otherUserScore.getScore() - this.getScore();
	}
}
