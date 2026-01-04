package com.majeed.graphql.service;

import com.majeed.graphql.model.Player;
import com.majeed.graphql.model.Team;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    public List<Player> getAllPlayers();

    public Optional<Player> findPlayerById(Integer id);

    public Player addPlayer(String name, Integer age, Team team) ;

    public Player deletePlayer(Integer id) ;

    public Player updatePlayer(Integer id, String name, Integer age, Team team) ;

    public void initializePlayers() ;

}
