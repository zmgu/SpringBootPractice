package com.zmgu.myproj.service;

import com.zmgu.myproj.domain.Board;
import com.zmgu.myproj.domain.User;
import com.zmgu.myproj.repository.BoardRepository;
import com.zmgu.myproj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
