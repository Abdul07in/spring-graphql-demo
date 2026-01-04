package com.majeed.graphql.service.impl;

import com.majeed.graphql.model.Player;
import com.majeed.graphql.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceImplTest {

    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        playerService = new PlayerServiceImpl();
        playerService.initializePlayers();
    }

    @Test
    void getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        assertNotNull(players);
        assertEquals(5, players.size());
    }

    @Test
    void findPlayerById_Exists() {
        Optional<Player> player = playerService.findPlayerById(1);
        assertTrue(player.isPresent());
        assertEquals("MS Dhoni", player.get().name());
    }

    @Test
    void findPlayerById_NotExists() {
        Optional<Player> player = playerService.findPlayerById(99);
        assertFalse(player.isPresent());
    }

    @Test
    void addPlayer() {
        Player player = playerService.addPlayer("Hardik Pandya", 28, Team.MI);
        assertNotNull(player);
        assertNotNull(player.id());
        assertEquals("Hardik Pandya", player.name());

        Optional<Player> found = playerService.findPlayerById(player.id());
        assertTrue(found.isPresent());
    }

    @Test
    void deletePlayer_Exists() {
        Player deleted = playerService.deletePlayer(1);
        assertNotNull(deleted);
        assertEquals("MS Dhoni", deleted.name());

        Optional<Player> found = playerService.findPlayerById(1);
        assertFalse(found.isPresent());
    }

    @Test
    void deletePlayer_NotExists() {
        Player deleted = playerService.deletePlayer(99);
        assertNull(deleted);
    }

    @Test
    void updatePlayer_Exists() {
        Player updated = playerService.updatePlayer(1, "MS Dhoni Updated", 41, Team.CSK);
        assertNotNull(updated);
        assertEquals("MS Dhoni Updated", updated.name());
    }

    @Test
    void updatePlayer_CheckUpdatedValue() {
        playerService.updatePlayer(1, "MS Dhoni Updated", 41, Team.CSK);
        Optional<Player> found = playerService.findPlayerById(1);
        assertTrue(found.isPresent());
        assertEquals("MS Dhoni Updated", found.get().name());
        assertEquals(41, found.get().age());
    }

    @Test
    void updatePlayer_NotExists() {
        Player updated = playerService.updatePlayer(99, "No One", 0, Team.CSK);
        assertNull(updated);
    }
}
