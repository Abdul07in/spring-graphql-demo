package com.majeed.graphql.controller;

import com.majeed.graphql.model.Player;
import com.majeed.graphql.model.Team;
import com.majeed.graphql.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@GraphQlTest(PlayerController.class)
class PlayerControllerTest {

  @Autowired
  private GraphQlTester graphQlTester;

  @TestConfiguration
  static class TestConfig {
    @Bean
    PlayerService playerService() {
      return mock(PlayerService.class);
    }
  }

  @Autowired
  private PlayerService playerService;

  @Test
  void findAllPlayers() {
    when(playerService.getAllPlayers()).thenReturn(List.of(
        new Player(1, "MS Dhoni", 40, Team.CSK)));

    String query = """
        query {
          findAllPlayers {
            id
            name
            age
            team
          }
        }
        """;

    graphQlTester.document(query)
        .execute()
        .path("findAllPlayers")
        .entityList(Player.class)
        .hasSize(1);
  }

  @Test
  void findPlayerById() {
    when(playerService.findPlayerById(anyInt())).thenReturn(Optional.of(
        new Player(1, "MS Dhoni", 40, Team.CSK)));

    String query = """
        query($id: ID!) {
          findPlayerById(id: $id) {
            id
            name
          }
        }
        """;

    graphQlTester.document(query)
        .variable("id", 1)
        .execute()
        .path("findPlayerById.name")
        .entity(String.class)
        .isEqualTo("MS Dhoni");
  }

  @Test
  void createPlayer() {
    when(playerService.addPlayer(anyString(), anyInt(), any(Team.class))).thenReturn(
        new Player(1, "New Player", 25, Team.MI));

    String mutation = """
        mutation($name: String!, $age: Int!, $team: Team!) {
          createPlayer(name: $name, age: $age, team: $team) {
            id
            name
          }
        }
        """;

    graphQlTester.document(mutation)
        .variable("name", "New Player")
        .variable("age", 25)
        .variable("team", "MI")
        .execute()
        .path("createPlayer.name")
        .entity(String.class)
        .isEqualTo("New Player");
  }

  @Test
  void updatePlayer() {
    when(playerService.updatePlayer(anyInt(), anyString(), anyInt(), any(Team.class))).thenReturn(
        new Player(1, "Updated Player", 26, Team.RCB));

    String mutation = """
        mutation($id: ID!, $name: String, $age: Int, $team: Team) {
          updatePlayer(id: $id, name: $name, age: $age, team: $team) {
            id
            name
          }
        }
        """;

    graphQlTester.document(mutation)
        .variable("id", 1)
        .variable("name", "Updated Player")
        .variable("age", 26)
        .variable("team", "RCB")
        .execute()
        .path("updatePlayer.name")
        .entity(String.class)
        .isEqualTo("Updated Player");
  }

  @Test
  void deletePlayer() {
    when(playerService.deletePlayer(anyInt())).thenReturn(
        new Player(1, "Deleted Player", 30, Team.RR));

    String mutation = """
        mutation($id: ID!) {
          deletePlayer(id: $id) {
            id
            name
          }
        }
        """;

    graphQlTester.document(mutation)
        .variable("id", 1)
        .execute()
        .path("deletePlayer.name")
        .entity(String.class)
        .isEqualTo("Deleted Player");
  }

  @Test
  void handleException() {
    String mutation = """
        mutation($name: String!, $age: Int!, $team: Team!) {
          createPlayer(name: $name, age: $age, team: $team) {
            id
          }
        }
        """;

    graphQlTester.document(mutation)
        .variable("name", "Invalid Team Player")
        .variable("age", 25)
        .variable("team", "INVALID_TEAM")
        .execute()
        .errors()
        .expect(error -> error.getMessage().contains("INVALID_TEAM"));
  }
}
