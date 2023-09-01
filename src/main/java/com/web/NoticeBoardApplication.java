package com.web;

import com.web.domain.Board;
import com.web.domain.Users;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UsersRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class NoticeBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeBoardApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(UsersRepository usersRepository, BoardRepository boardRepository) throws Exception {
        return (args) -> {
            Users users = usersRepository.save(Users.builder()
                    .name("havi")
                    .password("test")
                    .email("havi@gmail.com")
                    .createdDate(LocalDateTime.now())
                    .build());

			IntStream.rangeClosed(1, 200).forEach(index ->
            boardRepository.save(Board.builder()
                    .title("게시글" + index)
                    .subTitle("순서" + index)
                    .content("콘텐츠")
                    .boardType(BoardType.FREE)
                    .createdDate(LocalDateTime.now())
                    .updatedDate(LocalDateTime.now())
                    .build())
			);
        };
    }

}