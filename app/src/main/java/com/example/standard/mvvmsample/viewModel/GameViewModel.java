package com.example.standard.mvvmsample.viewModel;

import android.arch.lifecycle.*;
import android.databinding.ObservableArrayMap;

import com.example.standard.mvvmsample.model.*;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game;

    public void init(String player1, String player2) {
        game = new Game();
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int col){
        if(game.cells[row][col] == null){
            game.cells[row][col] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row,col),game.currentPlayer.value);
            if(game.hasGameEnded()){
                game.reset();
            } else {
                game.switchPlayer();
            }
        }
    }

    public LiveData<Player> getWinner() {
        return game.winner;
    }

    public static String stringFromNumbers(int... numbers) {
        StringBuilder sNumbers = new StringBuilder();
        for (int number : numbers)
            sNumbers.append(number);
        return sNumbers.toString();
    }
}
