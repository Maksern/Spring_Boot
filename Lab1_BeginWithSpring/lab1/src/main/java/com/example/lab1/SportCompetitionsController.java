package com.example.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SportCompetitionsController {
    Competition[] competions = {
        new Competition("Football World Cup", "Paris, France", "2023-10-05"), 
        new Competition("Basketball Championship", "New York City, USA", "2023-10-10"), 
        new Competition("Tennis Open", "London, UK", "2023-10-15"), 
        new Competition("Soccer League", "Madrid, Spain", "2023-10-20"), 
        new Competition("Olympic Games", "Los Angeles, USA", "2023-10-25"), 
        new Competition("Golf Masters", "Augusta, USA", "2023-10-30"), 
        new Competition("Baseball World Series", "Tokyo, Japan", "2023-11-05"), 
        new Competition("Formula 1 Grand Prix", "Monaco", "2023-11-10"), 
        new Competition("Cricket Tournament", "Mumbai, India", "2023-11-15"), 
        new Competition("Rugby Six Nations", "Dublin, Ireland", "2023-11-20")};

    Result[] results = {
        new Result("Football World Cup", "Brazil", "3-1"), 
        new Result("Basketball Championship", "Team USA", "110-98"), 
        new Result("Tennis Open", "Serena Williams", "6-4, 6-2"), 
        new Result("Soccer League", "Real Madrid", "2-0"), 
        new Result("Olympic Games", "USA", "42 gold, 37 silver, 21 bronze"), 
        new Result("Golf Masters", "Tiger Woods", "-13 (275)"), 
        new Result("Baseball World Series", "New York Yankees", "4-2"), 
        new Result("Formula 1 Grand Prix", "Lewis Hamilton", "1:38:12.618"), 
        new Result("Cricket Tournament", "India", "245/3"), 
        new Result("Rugby Six Nations", "England", "20-14") 
    };


    @GetMapping("/*")
    public String HomePage(Model model){
        model.addAttribute("upcomingCompetitions", competions);
        model.addAttribute("Results", results);
        return "sportCompetitions";
    }
}

