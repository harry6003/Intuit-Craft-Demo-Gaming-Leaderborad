package com.craftDemo.gamingLeaderboard.model;

public class leaderBoard {
    String  boardID;
    Integer leaderBoardSize;

    public leaderBoard() {
        super();
    }
    public leaderBoard(String boardID, Integer leaderBoardSize) {
        super();
        this.boardID = boardID;
        this.leaderBoardSize = leaderBoardSize;
    }
    public String getBoardID() {
        return boardID;
    }
    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }
    public Integer getLeaderBoardSize() {
        return leaderBoardSize;
    }
    public void setLeaderBoardSize(Integer leaderBoardSize) {
        this.leaderBoardSize = leaderBoardSize;
    }
}
