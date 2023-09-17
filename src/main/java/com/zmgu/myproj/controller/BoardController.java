package com.zmgu.myproj.controller;

import com.zmgu.myproj.domain.Board;
import com.zmgu.myproj.repository.BoardRepository;
import com.zmgu.myproj.validator.BoardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model) {

        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindResult) {
        boardValidator.validate(board, bindResult);

        if(bindResult.hasErrors()){
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
