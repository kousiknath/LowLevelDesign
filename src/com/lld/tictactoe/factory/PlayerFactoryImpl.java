package com.lld.tictactoe.factory;

import com.lld.tictactoe.model.HumanPlayer;
import com.lld.tictactoe.model.MachinePlayer;
import com.lld.tictactoe.model.Player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PlayerFactoryImpl implements PlayerFactory {
    private Queue<Character> markers;

    public PlayerFactoryImpl() {
        this.markers = new LinkedList<>();

        // just seed some random markers
        this.markers.add('X');
        this.markers.add('O');
        this.markers.add('Z');
        this.markers.add('I');
        this.markers.add('L');
    }

    @Override
    public Player createHumanPlayer() {
        checkIfCanCreatePlayer();

        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("Enter name of the human player: ");
        String name = reader.nextLine();

        return new HumanPlayer(name, this.markers.poll());
    }

    @Override
    public Player createMachinePlayer() {
        checkIfCanCreatePlayer();

        Player machinePlayer = new MachinePlayer(this.markers.poll());
        return machinePlayer;
    }

    private void checkIfCanCreatePlayer() {
        if (this.markers.isEmpty()) {
            throw new RuntimeException("Can not create any more player");
        }
    }
}
