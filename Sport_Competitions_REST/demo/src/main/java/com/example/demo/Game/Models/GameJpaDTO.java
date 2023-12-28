// package com.example.demo.Game.Models;




// import java.sql.Timestamp;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class GameJpaDTO {
//     private Long id;
//     private String sportType;
    
//     private Timestamp gameTime;
//     private String gamePlace;

//     private String result;

//     public static GameJpaDTO fromEntity(Game game){
//         return new GameJpaDTO(game.getGameid(), game.getSportType(), 
//                             game.getGameTime(), game.getGamePlace(), 
//                             getResult(game.getGuestTeamScore(), game.getHomeTeamScore()));
//     }

//     private static String getResult(int guestTeamScore, int homeTeamScore){
//         if(guestTeamScore > homeTeamScore){
//             return "Lose";
//         } else if(homeTeamScore > guestTeamScore){
//             return "Win";
//         } else{
//             return "Draw";
//         }
//     }
// }