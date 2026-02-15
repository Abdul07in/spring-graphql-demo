package com.majeed.graphql.controller;

import com.majeed.graphql.model.Player;
import com.majeed.graphql.model.Team;
import com.majeed.graphql.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAllPlayers() {
        return playerService.getAllPlayers();
    }

    @QueryMapping
    public Optional<Player> findPlayerById(@Argument Integer id) {
        return playerService.findPlayerById(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name, @Argument Integer age, @Argument String team) {
        return playerService.addPlayer(name, age, Enum.valueOf(Team.class, team));
    }

    @MutationMapping
    public Player updatePlayer(@Argument Integer id, @Argument String name, @Argument Integer age, @Argument String team) {
        return playerService.updatePlayer(id, name, age, Enum.valueOf(Team.class, team));
    }

    @MutationMapping
    public Player deletePlayer(@Argument Integer id) {
        return playerService.deletePlayer(id);
    }

    @MutationMapping
    public Player transferPlayer(@Argument Integer id, @Argument String newTeam) {
        return playerService.transferPlayer(id, Enum.valueOf(Team.class, newTeam));
    }
}
