package com.lld.tictactoe.factory;

import com.lld.tictactoe.model.Player;

public interface PlayerFactory {
    Player createHumanPlayer();
    Player createMachinePlayer();
}
