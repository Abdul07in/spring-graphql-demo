package com.majeed.graphql.service;

import com.majeed.graphql.model.Player;
import com.majeed.graphql.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();
    AtomicInteger idCounter = new AtomicInteger(0);

    public List<Player> getAllPlayers() {
        return players;
    }

    public Optional<Player> findPlayerById(Integer id) {
        return players.stream()
                .filter(player -> player.id().equals(id))
                .findFirst();
    }

    public Player addPlayer(String name, Integer age, Team team) {
        Integer id = idCounter.incrementAndGet();
        Player newPlayer = new Player(id, name, age, team);
        players.add(newPlayer);
        return newPlayer;
    }

    public Player deletePlayer(Integer id) {
        Optional<Player> playerOpt = findPlayerById(id);
        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            players.remove(player);
            return player;
        }
        return null;
    }

    public Player updatePlayer(Integer id, String name, Integer age, Team team) {
        Optional<Player> playerOpt = findPlayerById(id);
        if (playerOpt.isPresent()) {
            Player updatedPlayer = new Player(id, name, age, team);
            players.remove(playerOpt.get());
            players.add(updatedPlayer);
            return updatedPlayer;
        }
        return null;
    }

    @PostConstruct
    public void initializePlayers() {
        addPlayer("MS Dhoni", 40, Team.CSK);
        addPlayer("Rohit Sharma", 34, Team.MI);
        addPlayer("Virat Kohli", 33, Team.RCB);
        addPlayer("Jaydev Unadkat", 30, Team.RR);
        addPlayer("Shikhar Dhawan", 36, Team.DC);
    }


}
